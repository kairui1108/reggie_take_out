package ltd.ruikai.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.ruikai.reggie.entity.Category;

/**
 * @ author  tanruikai
 * @ date  2022/9/4 21:25
 * @ version 1.0
 */
public interface CategoryService extends IService<Category> {

    public void remove(Long id);

}
