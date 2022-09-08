package ltd.ruikai.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.ruikai.reggie.entity.ShoppingCart;
import ltd.ruikai.reggie.mapper.ShoppingCartMapper;
import ltd.ruikai.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @ author  tanruikai
 * @ date  2022/9/7 20:05
 * @ version 1.0
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
