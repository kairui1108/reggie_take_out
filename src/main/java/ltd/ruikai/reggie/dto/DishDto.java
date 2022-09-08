package ltd.ruikai.reggie.dto;


import lombok.Data;
import ltd.ruikai.reggie.entity.Dish;
import ltd.ruikai.reggie.entity.DishFlavor;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
