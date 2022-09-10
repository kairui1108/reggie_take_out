package ltd.ruikai.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import ltd.ruikai.reggie.common.BaseContext;
import ltd.ruikai.reggie.common.R;
import ltd.ruikai.reggie.entity.OrderDetail;
import ltd.ruikai.reggie.entity.Orders;
import ltd.ruikai.reggie.entity.User;
import ltd.ruikai.reggie.service.OrderService;
import ltd.ruikai.reggie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ author  tanruikai
 * @ date  2022/9/7 21:08
 * @ version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        orderService.submit(orders);
        return R.success("支付成功");
    }

    @GetMapping("/userPage")
    public R<Page> userPage(int page, int pageSize){
        Page<Orders> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUserId, BaseContext.getCurrentId());
        queryWrapper.orderByAsc(Orders::getOrderTime);

        orderService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);

    }

    @GetMapping("/page")
    public R<Page> page(Integer page, Integer pageSize, Integer number, String beginTime, String endTime){
        log.info("page:{}, pageSize:{}, number:{}, beginTime:{}, endTime:{}", page, pageSize, number, beginTime, endTime);
        Page<Orders> pageInfo = new Page<>();
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();

        if(number != null){
            queryWrapper.like(Orders::getNumber, number);
        }
        if(beginTime != null && endTime != null){
            queryWrapper.between(Orders::getOrderTime, beginTime, endTime);
        }
        Page<Orders> pageRes = orderService.page(pageInfo, queryWrapper);
        List<Orders> orders = pageRes.getRecords();
        for (Orders order : orders) {
            Long userId = order.getUserId();
            User user = userService.getUserById(userId);
            order.setUserName(user.getName());
        }
        //todo: 用stream优化
        return R.success(pageRes);
    }

    @PutMapping
    public R<String> deliver(@RequestBody Orders order){
        log.info("id: {}, status: {}", order.getId(), order.getStatus());
        UpdateWrapper<Orders> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", order.getId());
        updateWrapper.set("status", order.getStatus());
        orderService.update(updateWrapper);
        return R.success("操作成功");
    }
}
