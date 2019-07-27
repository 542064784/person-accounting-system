package com.damon.entity;

import com.damon.dto.TransactionType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
/**
 *  日账单 entity
 *
 * @author Damon Chen
 * @date 2019/6/11
 */
@Entity
@Table(name = "daily_account_information")
public class DailyAccountInformationEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "daily_id")
    private String dailyId;
    @Column(name = "username")
    private String username;
    @Column(name = "year")
    private Integer year;
    @Column(name = "month")
    private Integer month;
    @Column(name = "day_of_month")
    private Integer dayOfMonth;
    //  金额  '-' 为消费  否则为收入
    @Column(name = "account")
    private String account;
    @Column(name = "comment")
    private String comment;
    @Column(name = "create_date")
    private Date createDate;
    //  交易类型
    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    //  删除标记
    @Column(name = "deleted")
    @Type(type = "yes_no")
    private Boolean deleted = Boolean.FALSE;

    public String getDailyId() {
        return dailyId;
    }

    public void setDailyId(String dailyId) {
        this.dailyId = dailyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateDate() {
        if (null == createDate){
            return null;
        }else {
            return new Date(createDate.getTime());
        }
    }

    public void setCreateDate(Date createDate) {
        if (null == createDate) {
            this.createDate = new Date();
        }else {
            this.createDate = new Date(createDate.getTime());
        }
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
