package com.damon.entity;

import com.damon.dto.TransactionType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
/**
 *  account information entity
 *
 * @author Damon Chen
 * @date 2019/07/27
 */
@Entity
@Table(name = "account_information")
@Data
public class AccountInformationEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
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
    //  金额详情
    @Column(name = "comment")
    private String comment;
    //  交易类型
    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    //  删除标记
    @Column(name = "deleted")
    @Type(type = "yes_no")
    private boolean deleted = Boolean.FALSE;

}
