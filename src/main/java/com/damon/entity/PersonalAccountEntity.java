package com.damon.entity;


import com.damon.dto.TransactionType;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 *  person account entity
 *
 * @author Damon Chen
 * @date 2019/6/9
 */
@Entity
@Table(name = "personal_account")
@Data
public class PersonalAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //  年
    @Column(name = "year")
    private Integer year;
    //  月
    @Column(name = "month")
    private Integer month;
    //  日
    @Column(name = "day_of_month")
    private Integer dayOfMonth;
    //  金额
    @Column(name = "amount")
    private String amount;
    //  用户名
    @Column(name = "username")
    private String username;
    //  支出
    @Column(name = "expense")
    private BigDecimal expense;
    //  收入
    @Column(name = "income")
    private BigDecimal income;
    //  金额详情
    @Column(name = "comment")
    private String comment;
    //  交易类型
    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    //  删除标记
    @Column(name = "delete_flag")
    @Type(type = "yes_no")
    private boolean deleteFlag = Boolean.FALSE;

}
