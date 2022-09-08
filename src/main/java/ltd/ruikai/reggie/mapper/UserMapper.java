package ltd.ruikai.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.ruikai.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ author  tanruikai
 * @ date  2022/9/6 21:36
 * @ version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
