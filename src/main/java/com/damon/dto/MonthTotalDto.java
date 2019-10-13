package com.damon.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 *  Month Account Information dto
 *
 * @author Damon Chen
 * @date 2019/6/11
 */
@Data
public class MonthTotalDto {

    private String username;

    private BigDecimal money;

    private TransactionType transactionType;

}
