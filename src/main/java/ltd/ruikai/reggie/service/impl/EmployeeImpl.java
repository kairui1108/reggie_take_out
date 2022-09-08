package ltd.ruikai.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.ruikai.reggie.entity.Employee;
import ltd.ruikai.reggie.mapper.EmployeeMapper;
import ltd.ruikai.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @ author  tanruikai
 * @ date  2022/9/3 20:18
 * @ version 1.0
 */
@Service
public class EmployeeImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
