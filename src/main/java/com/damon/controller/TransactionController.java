package com.damon.controller;

import com.damon.dto.TransactionDto;
import com.damon.dto.TransactionInformationDto;
import com.damon.service.TransactionService;
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
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/find-information")
    public TransactionDto findCurrentInformation(final Integer year,final Integer month,final String username){
        return transactionService.findTransactionInformation(year, month, username);
    }

    /**
     *  add account information record
     *
     * @param transactionInformationDto  transactionInformationDto
     * @return    success  or  error message
     */
    @PostMapping("/save-transaction")
    public void addAccountInformation(@RequestBody final TransactionInformationDto transactionInformationDto){
        transactionService.processAccountInformation(transactionInformationDto);
    }

}
