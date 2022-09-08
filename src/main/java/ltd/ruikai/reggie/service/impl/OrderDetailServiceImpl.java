package ltd.ruikai.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.ruikai.reggie.entity.OrderDetail;
import ltd.ruikai.reggie.mapper.OrderDetailMapper;
import ltd.ruikai.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @ author  tanruikai
 * @ date  2022/9/7 21:05
 * @ version 1.0
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
