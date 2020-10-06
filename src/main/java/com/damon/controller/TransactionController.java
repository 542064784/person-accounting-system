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

    /**
     *  found information
     *
     * @param year        year
     * @param month       month
     * @param username   userName
     * @param currentPate  current page
     * @param pageSize    pateSize
     * @return            TransactionDto
     */
    @GetMapping("/find-information")
    public TransactionDto findCurrentInformation(final Integer year,
                                                  final Integer month,
                                                  final String username,
                                                  @RequestParam(defaultValue = "0") final Integer currentPate,
                                                  @RequestParam(defaultValue = "50") final Integer pageSize){
        return transactionService.findTransactionInformation(year, month, username, currentPate, pageSize);
    }

    /**
     *  add account information record
     *
     * @param transactionInformationDto  transactionInformationDto
     */
    @PostMapping("/save-transaction")
    public void addAccountInformation(@RequestBody final TransactionInformationDto transactionInformationDto){
        transactionService.processTransaction(transactionInformationDto);
    }

    /**
     *  delete transaction
     *
     * @param id     id
     */
    @GetMapping("/delete-transaction")
    public void deleteTransaction(String id) {
        transactionService.deleteTransaction(id);
    }

}
