package ltd.ruikai.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.ruikai.reggie.dto.SetmealDto;
import ltd.ruikai.reggie.entity.Setmeal;

import java.util.List;

/**
 * @ author  tanruikai
 * @ date  2022/9/4 21:58
 * @ version 1.0
 */
public interface SetmealService extends IService<Setmeal> {

    public void saveWithDish(SetmealDto setmealDto);

    public void removeWithDish(List<Long> ids);

}
