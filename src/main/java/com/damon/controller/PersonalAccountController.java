package com.damon.controller;

import com.damon.dto.DailyAccountInformationDto;
import com.damon.service.PersonalAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
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
    public String addPersonAccount(final DailyAccountInformationDto dailyAccountInformationDto){
        personalAccountService.addPersonalAccount(dailyAccountInformationDto);
        return "success";
    }

    @GetMapping("/find-daily-account-information")
    public List<DailyAccountInformationDto> findDailyAccountInformation(final Integer year,final Integer month,final Integer dayOfMonth){
        return personalAccountService.findDailyAccountInformation(year, month, dayOfMonth);
    }

}
