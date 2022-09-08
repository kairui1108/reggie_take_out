package ltd.ruikai.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.ruikai.reggie.entity.Orders;


/**
 * @ author  tanruikai
 * @ date  2022/9/7 21:04
 * @ version 1.0
 */
public interface OrderService extends IService<Orders> {

    public void submit(Orders orders);
}
