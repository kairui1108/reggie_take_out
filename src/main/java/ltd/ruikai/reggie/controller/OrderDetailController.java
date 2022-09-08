package ltd.ruikai.reggie.controller;

import lombok.extern.slf4j.Slf4j;
import ltd.ruikai.reggie.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ author  tanruikai
 * @ date  2022/9/7 21:09
 * @ version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;


}
