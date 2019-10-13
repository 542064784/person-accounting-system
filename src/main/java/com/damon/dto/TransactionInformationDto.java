package com.damon.dto;

import lombok.Data;
import java.util.Date;
/**
 *  account information dto
 *
 * @author Damon Chen
 * @date 2019/07/27
 */
@Data
public class TransactionInformationDto {

    private String id;

    private Integer year;

    private Integer month;

    private Integer dayOfMonth;

    private String money;

    private String username;

    private String description;

    private TransactionType transactionType;

    private Date createDate;

}
