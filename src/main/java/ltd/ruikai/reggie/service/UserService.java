package ltd.ruikai.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.ruikai.reggie.entity.User;

/**
 * @ author  tanruikai
 * @ date  2022/9/6 21:36
 * @ version 1.0
 */
public interface UserService extends IService<User> {

    User getUserById(Long id);
}
