package com.damon.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;

/**
 * @author Damon Chen
 * @date 2019/6/10
 */
@Data
public class PersonalAccountDto {

    //  年
    private Integer year;
    //  月
    private Integer month;
    //  日
    private Integer dayOfMonth;
    //  金额
    private String amount;
    //  用户名
    private String username;
    //  支出
    private BigDecimal expense;
    //  收入
    private BigDecimal income;
    //  金额详情
    private String comment;
    //  交易类型
    private TransactionType transactionType;
    //  删除标记
    private boolean deleteFlag = Boolean.FALSE;
}
