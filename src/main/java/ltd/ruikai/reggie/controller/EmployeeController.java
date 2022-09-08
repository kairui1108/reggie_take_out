package ltd.ruikai.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import ltd.ruikai.reggie.common.R;
import ltd.ruikai.reggie.entity.Employee;
import ltd.ruikai.reggie.service.EmployeeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @ author  tanruikai
 * @ date  2022/9/3 20:19
 * @ version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){

        //md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        //根据用户名查数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //没有查到，返回登录失败
        if(emp == null){
            return R.error("登录失败，用户不存在");
        }

        //比对密码
        if(!emp.getPassword().equals(password)){
            return R.error("登录失败，密码错误");
        }

        //检查员工状态
        if(emp.getStatus() == 0){
            return R.error("账号状态异常");
        }

        //登录成功
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee){
        log.info("新增员工，员工信息：{}", employee.toString());

        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8)));

        //employee.setCreateTime(LocalDateTime.now());
        //employee.setUpdateTime(LocalDateTime.now());
        //由元数据处理器处理公共字段
        //Long empId = (Long) request.getSession().getAttribute("employee");
        //employee.setCreateUser(empId);
        //employee.setUpdateUser(empId);

        employeeService.save(employee);

        return R.success("新增成功");
    }

    @GetMapping("/page")
    public R<Page> page(Integer page, Integer pageSize, String name){
        log.info("page = {}, pageSize = {}, name = {}", page, pageSize, name);


        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //过滤
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        //排序
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        employeeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 根据ID 修改员工信息
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Employee employee, HttpServletRequest request){
        log.info(employee.toString());
        //employee.setUpdateTime(LocalDateTime.now());
        //employee.setUpdateUser((long)request.getSession().getAttribute("employee"));
        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }

    @GetMapping("/{employeeId}")
    public R<Employee> select(@PathVariable("employeeId") long empId){
        Employee emp = employeeService.getById(empId);
        if(emp == null){
            return R.error("没有查到员工信息");
        }
        log.info("查询到员工信息： {}", emp.toString());
        return R.success(emp);
    }
}
