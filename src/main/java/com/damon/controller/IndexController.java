package com.damon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

/**
 * @author Damon Chen
 * @date 2019/10/12
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        LocalDate localDate = LocalDate.now();
//        return "forward:account/find-current?username=542064784&" +
//                "year="+localDate.getYear()+"&month="+localDate.getMonthValue();
        return "index";
    }

}
