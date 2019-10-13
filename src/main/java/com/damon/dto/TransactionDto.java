package com.damon.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 *  total information dto
 *
 * @author Damon Chen
 * @date 2019/08/03
 */
@Data
public class TransactionDto {

    private Integer year;

    private Integer month;

    private String username;

    private BigDecimal balance = BigDecimal.ZERO;

    private BigDecimal monthConsumption = BigDecimal.ZERO;

    private BigDecimal monthIncome = BigDecimal.ZERO;

    private TransactionType[] transactionTypes = TransactionType.values();

    private List<TransactionInformationDto> transactionInformationDtoList = new ArrayList<>();

    private List<MonthTotalDto> monthTotalDtoList = new ArrayList<>();

    public TransactionDto(){}

    public TransactionDto(Integer year, Integer month, String username) {
        this.year = year;
        this.month = month;
        this.username = username;
    }
}
