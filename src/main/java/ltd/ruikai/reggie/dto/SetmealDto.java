package ltd.ruikai.reggie.dto;


import lombok.Data;
import ltd.ruikai.reggie.entity.Setmeal;
import ltd.ruikai.reggie.entity.SetmealDish;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
