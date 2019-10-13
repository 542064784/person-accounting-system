package com.damon.entity;

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
@Table(name = "balance_log")
@EntityListeners({AuditingEntityListener.class})
@Data
public class BalanceLog {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "month")
    private Integer month;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "username")
    private String username;

    @CreatedDate
    @Column(name = "create_date")
    private Date createDate;

}
