package ltd.ruikai.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.ruikai.reggie.dto.DishDto;
import ltd.ruikai.reggie.entity.Dish;

/**
 * @ author  tanruikai
 * @ date  2022/9/4 21:55
 * @ version 1.0
 */
public interface DishService extends IService<Dish> {

    //新增菜品，同时插入对应的口味数据
    public void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息
    public DishDto getByIdWithFlavor(Long id);

    //根据id更新
    public  void updateWithFlavor(DishDto dishDto);
}
