package ltd.ruikai.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.ruikai.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ author  tanruikai
 * @ date  2022/9/3 20:16
 * @ version 1.0
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
