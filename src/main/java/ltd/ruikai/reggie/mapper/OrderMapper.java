package ltd.ruikai.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.ruikai.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;


/**
 * @ author  tanruikai
 * @ date  2022/9/7 21:03
 * @ version 1.0
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
