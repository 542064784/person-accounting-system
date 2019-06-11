package com.damon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 *  日账单 dto
 *
 * @author Damon Chen
 * @date 2019/6/11
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DailyAccountInformationDto {

    private String username;
    private Integer year;
    private Integer month;
    private Integer dayOfMonth;
    //  金额  '-' 为消费  否则为收入
    private String account;
    private String comment;
    //  交易类型
    private TransactionType transactionType;



}
