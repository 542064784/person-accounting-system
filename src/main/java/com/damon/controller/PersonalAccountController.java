package com.damon.controller;

import com.damon.dto.AccountInformationDto;
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
@RequestMapping("/person")
public class PersonalAccountController {

    @Autowired
    private PersonalAccountService personalAccountService;

    /**
     *  add account information record
     *
     * @param accountInformationDto  accountInformationDto
     * @return    success  or  error message
     */
    @PostMapping("/account-information")
    public String addAccountInformation(final AccountInformationDto accountInformationDto){
        try {
            personalAccountService.saveAccountInformation(accountInformationDto);
            return "success";
        } catch (RuntimeException e) {
            log.error(e.getMessage(),e);
            return e.getMessage();
        }
    }

}
