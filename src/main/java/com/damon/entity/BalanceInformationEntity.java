package com.damon.entity;

import com.damon.dto.TransactionType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
/**
 *  balance information entity
 *
 * @author Damon Chen
 * @date 2019/08/03
 */
@Entity
@Table(name = "balance_information")
@EntityListeners({AuditingEntityListener.class})
@Data
public class BalanceInformationEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "month")
    private Integer month;

    @Column(name = "day_of_month")
    private Integer dayOfMonth;

    @Column(name = "amount")
    private String amount;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "username")
    private String username;

    @Column(name = "description")
    private String description;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @CreatedDate
    @Column(name = "create_date")
    private Date createDate;

}
