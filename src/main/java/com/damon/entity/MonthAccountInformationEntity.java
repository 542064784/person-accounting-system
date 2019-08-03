package com.damon.entity;

import com.damon.dto.TransactionType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
/**
 *  Month Account Information Entity
 *
 * @author Damon Chen
 * @date 2019/6/11
 */
@Entity
@Table(name = "month_account_information")
@EntityListeners({AuditingEntityListener.class})
@Data
public class MonthAccountInformationEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "year")
    private Integer year;
    @Column(name = "month")
    private Integer month;
    @Column(name = "money")
    private BigDecimal money;
    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @CreatedDate
    @Column(name = "create_date")
    private Date createDate;
    @LastModifiedDate
    @Column(name = "modified_date")
    private Date modifiedDate;
}
