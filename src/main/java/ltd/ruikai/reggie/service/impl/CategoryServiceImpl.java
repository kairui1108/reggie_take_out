package ltd.ruikai.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.ruikai.reggie.entity.Category;
import ltd.ruikai.reggie.entity.Dish;
import ltd.ruikai.reggie.entity.Setmeal;
import ltd.ruikai.reggie.exception.CustomException;
import ltd.ruikai.reggie.mapper.CategoryMapper;
import ltd.ruikai.reggie.service.CategoryService;
import ltd.ruikai.reggie.service.DishService;
import ltd.ruikai.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ author  tanruikai
 * @ date  2022/9/4 21:26
 * @ version 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;
    /**
     * 根据ID 删除分类
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();

        //查询是否关联了菜品、套餐
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count = dishService.count(dishLambdaQueryWrapper);
        if(count > 0){
            //throw err
            throw new CustomException("当前分类下有关联菜品，不能删除！");
        }

        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count1 = setmealService.count(setmealLambdaQueryWrapper);
        if(count1 > 0){
            //throw err
            throw new CustomException("当前分类下由关联套餐，不能删除！");
        }

        super.removeById(id);
    }
}
