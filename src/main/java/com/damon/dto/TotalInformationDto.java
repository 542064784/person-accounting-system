package com.damon.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *  total information dto
 *
 * @author Damon Chen
 * @date 2019/08/03
 */
public class TotalInformationDto {

    private Integer currentPage = 1;

    private Integer pageSize = 10;

    private Integer totalPages;

    private long totalElements;

    private AccountInformationDto accountInformationDto;

    private BigDecimal balance;

    private List<AccountInformationDto> accountInformationDtoList  = new ArrayList<>();

    private List<MonthAccountInformationDto> monthAccountInformationDtoList = new ArrayList<>();

    public List<AccountInformationDto> getAccountInformationDtoList() {
        return Collections.unmodifiableList(accountInformationDtoList);
    }

    public void setAccountInformationDtoList(List<AccountInformationDto> accountInformationDtoList) {
        if (null != accountInformationDtoList){
            this.accountInformationDtoList = Collections.unmodifiableList(accountInformationDtoList);
        }
    }

    public List<MonthAccountInformationDto> getMonthAccountInformationDtoList() {
        return Collections.unmodifiableList(monthAccountInformationDtoList);
    }

    public void setMonthAccountInformationDtoList(List<MonthAccountInformationDto> monthAccountInformationDtoList) {
        if (null != accountInformationDtoList){
            this.monthAccountInformationDtoList = Collections.unmodifiableList(monthAccountInformationDtoList);
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public AccountInformationDto getAccountInformationDto() {
        return accountInformationDto;
    }

    public void setAccountInformationDto(AccountInformationDto accountInformationDto) {
        this.accountInformationDto = accountInformationDto;
    }
}
