package ltd.ruikai.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.ruikai.reggie.entity.User;
import ltd.ruikai.reggie.mapper.UserMapper;
import ltd.ruikai.reggie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @ author  tanruikai
 * @ date  2022/9/6 21:37
 * @ version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getUserById(Long id) {
        return this.getById(id);
    }
}
