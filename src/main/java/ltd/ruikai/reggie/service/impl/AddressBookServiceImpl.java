package ltd.ruikai.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ltd.ruikai.reggie.entity.AddressBook;
import ltd.ruikai.reggie.mapper.AddressBookMapper;
import ltd.ruikai.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * @ author  tanruikai
 * @ date  2022/9/7 18:50
 * @ version 1.0
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
