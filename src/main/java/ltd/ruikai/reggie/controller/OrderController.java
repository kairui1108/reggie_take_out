package ltd.ruikai.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import ltd.ruikai.reggie.common.BaseContext;
import ltd.ruikai.reggie.common.R;
import ltd.ruikai.reggie.entity.OrderDetail;
import ltd.ruikai.reggie.entity.Orders;
import ltd.ruikai.reggie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
