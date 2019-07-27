package com.damon.dto;

import lombok.Data;

/**
 * @author Damon Chen
 * @date 2019/07/27
 */
@Data
public class AccountInformationDto {

    private String id;
    private Integer year;
    private Integer month;
    private Integer dayOfMonth;
    /**
     *  金额
     */
    private String amount;
    /**
     *  用户名
     */
    private String username;
    /**
     *  金额详情
     */
    private String comment;
    /**
     *  交易类型
     */
    private TransactionType transactionType;
    /**
     *  是否删除
     */
    private boolean deleted = Boolean.FALSE;

}
