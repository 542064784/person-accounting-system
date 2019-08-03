package com.damon.controller;

import com.damon.dto.AccountInformationDto;
import com.damon.dto.TotalInformationDto;
import com.damon.entity.AccountInformationEntity;
import com.damon.service.AccountInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 *  personal account controller
 *
 * @author Damon Chen
 * @date 2019/6/10
 */
@RestController
@Slf4j
@RequestMapping("/account")
public class PersonalAccountController {

    @Autowired
    private AccountInformationService accountInformationService;

    @GetMapping("/find-current")
    public TotalInformationDto findCurrentInformation(@RequestBody final TotalInformationDto totalInformationDto){
        return accountInformationService.findAccountInformation(totalInformationDto);
    }

    /**
     *  add account information record
     *
     * @param accountInformationDto  accountInformationDto
     * @return    success  or  error message
     */
    @PostMapping("/account-information")
    public String addAccountInformation(@RequestBody final AccountInformationDto accountInformationDto){
        try {
            AccountInformationEntity accountInformation = accountInformationService.processAccountInformation(accountInformationDto);
            return "redirect:account/find-current?username="+accountInformation.getUsername()+"&year="+accountInformation.getYear()+"&month="+accountInformation.getMonth();
        } catch (RuntimeException e) {
            log.error(e.getMessage(),e);
            return e.getMessage();
        }
    }

}
