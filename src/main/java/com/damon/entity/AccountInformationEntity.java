package com.damon.entity;

import com.damon.dto.TransactionType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
/**
 *  account information entity
 *
 * @author Damon Chen
 * @date 2019/07/27
 */
@Entity
@Table(name = "account_information")
@Data
@EntityListeners({AuditingEntityListener.class})
public class AccountInformationEntity {

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

    @NotBlank
    @Column(name = "amount")
    private String amount;

    @Column(name = "username")
    private String username;

    @Column(name = "description")
    private String description;

    @NotBlank
    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @CreatedDate
    @Column(name = "create_date")
    private Date createDate;

}
