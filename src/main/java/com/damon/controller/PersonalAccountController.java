package com.damon.controller;

import com.damon.dto.PersonalAccountDto;
import com.damon.service.PersonalAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *  personal account controller
 *
 * @author Damon Chen
 * @date 2019/6/10
 */
@RestController
@Slf4j
@RequestMapping("/personal")
public class PersonalAccountController {

    @Autowired
    private PersonalAccountService personalAccountService;

    @PostMapping("/add-account")
    public String addPersonAccount(final PersonalAccountDto personalAccountDto){
        personalAccountService.addPersonalAccount(personalAccountDto);
        return "success";
    }

}
