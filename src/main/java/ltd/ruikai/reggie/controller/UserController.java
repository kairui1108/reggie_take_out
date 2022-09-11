package ltd.ruikai.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import ltd.ruikai.reggie.common.R;
import ltd.ruikai.reggie.entity.User;
import ltd.ruikai.reggie.service.UserService;
import ltd.ruikai.reggie.utils.ValidateCodeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ author  tanruikai
 * @ date  2022/9/6 21:37
 * @ version 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/sendMsg")
    public R<Map> sendMsg(@RequestBody User user, HttpSession session){

        //获取手机号
        String phone = user.getPhone();
        if(StringUtils.isNotEmpty(phone)){
            //生成随机验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            //api发送短信
            log.info("phone: {}; code: {}", phone, code);
            //将验证码保存到Session，以便核验
            //session.setAttribute(phone, code);

            //将验证码保存到redis
            redisTemplate.opsForValue().set(phone,code, 5, TimeUnit.MINUTES);

            HashMap<String, String> map = new HashMap<>();
            map.put("phone", phone);
            map.put("code", code);
            return R.success(map); //todo:自动填写code
        }

        return R.error("发送失败");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session){

        log.info(map.toString());

        String phone = map.get("phone").toString();
        String code = map.get("code").toString();

        //Object codeInSession = session.getAttribute(phone);
        Object codeInCache = redisTemplate.opsForValue().get(phone);

        if(codeInCache != null && codeInCache.equals(code)){
            //判断是否为新用户
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);
            if(user == null){
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user", user.getId());

            redisTemplate.delete(phone);
            return R.success(user);
        }

        return R.error("登录失败");
    }

    @PostMapping("/loginout")
    public R<String> logout(HttpSession session){
        session.removeAttribute("user");
        return R.success("退出登录");
    }

}
