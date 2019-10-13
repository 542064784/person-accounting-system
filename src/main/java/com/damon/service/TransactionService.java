package com.damon.service;

import com.damon.dto.MonthTotalDto;
import com.damon.dto.TransactionDto;
import com.damon.dto.TransactionInformationDto;
import com.damon.entity.BalanceLog;
import com.damon.entity.MonthTotal;
import com.damon.entity.TransactionInformation;
import com.damon.repository.BalanceLogRepository;
import com.damon.repository.MonthTotalRepository;
import com.damon.repository.TransactionInformationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * person account service
 *
 * @author Damon Chen
 * @date 2019/6/10
 */
@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
public class TransactionService {

    @Autowired
    private TransactionInformationRepository transactionInformationRepository;
    @Autowired
    private MonthTotalRepository monthTotalRepository;
    @Autowired
    private BalanceLogRepository balanceLogRepository;

    public TransactionDto findTransactionInformation(final Integer year, final Integer month, String username){
        final TransactionDto transactionDto = new TransactionDto(year,month,username);
        List<TransactionInformation> transactionInformationList = transactionInformationRepository.findTransactionInformation(
                year, month, username);
        List<TransactionInformationDto> transactionInformationDtoList = new ArrayList<>();
        transactionInformationList.forEach(transactionInformation -> {
            if (null != transactionInformation){
                TransactionInformationDto transactionInformationDto = new TransactionInformationDto();
                BeanUtils.copyProperties(transactionInformation, transactionInformationDto);
                transactionInformationDtoList.add(transactionInformationDto);
            }
        });
        transactionDto.setTransactionInformationDtoList(transactionInformationDtoList);

        List<MonthTotal> monthTotalList = monthTotalRepository.findByUsernameAndYearAndMonth(username, year, month);

        List<MonthTotalDto> monthTotalDtoList = new ArrayList<>();
        monthTotalList.forEach(monthTotal -> {
            if (null != monthTotal){
                MonthTotalDto monthTotalDto = new MonthTotalDto();
                BeanUtils.copyProperties(monthTotal, monthTotalDto);
                if (monthTotal.getMoney().compareTo(BigDecimal.ZERO) < 0){
                    transactionDto.setMonthConsumption(transactionDto.getMonthConsumption().add(monthTotal.getMoney()));
                }else {
                    transactionDto.setMonthIncome(transactionDto.getMonthIncome().add(monthTotal.getMoney()));
                }
                monthTotalDtoList.add(monthTotalDto);
            }
        });

        transactionDto.setMonthTotalDtoList(monthTotalDtoList);

        BalanceLog balanceInformation = balanceLogRepository.findByUsernameAndYearAndMonth(username,year,month);
        if (null == balanceInformation){
            transactionDto.setBalance(BigDecimal.ZERO);
        }else {
            transactionDto.setBalance(balanceInformation.getBalance());
        }
        return transactionDto;
    }

    public void deleteTransaction(final String id) throws Throwable {
        TransactionInformation transactionInformation = transactionInformationRepository.findById(id).orElseThrow(
                (Supplier<Throwable>) () -> new RuntimeException("not found record,id is " + id));

        BigDecimal money = new BigDecimal(transactionInformation.getMoney());
        MonthTotal monthTotal = findByExample(transactionInformation);
        if (null == monthTotal.getMoney()){
            return;
        }
        monthTotal.setMoney(monthTotal.getMoney().subtract(money));
        List<BalanceLog> balanceLogs = balanceLogRepository.findByUsernameAndYearGreaterThanEqualAndAndMonthGreaterThanEqual(
                transactionInformation.getUsername(), transactionInformation.getYear(), transactionInformation.getMonth());
        if (!CollectionUtils.isEmpty(balanceLogs)){
            balanceLogs.forEach(balanceLog -> balanceLog.setBalance(balanceLog.getBalance().subtract(money)));
            balanceLogRepository.saveAll(balanceLogs);
        }
        monthTotalRepository.saveAndFlush(monthTotal);
        transactionInformationRepository.delete(transactionInformation);

    }

    /**
     *  process account information
     *
     * @param transactionInformationDto transactionInformationDto
     */
    public void processTransaction(final TransactionInformationDto transactionInformationDto){
        final TransactionInformation accountInformation = saveAccountInformation(transactionInformationDto);
        log.info("save account information success");
        saveMonthAccountInformation(accountInformation);
        log.info("save month account information success");
        saveBalanceInformation(accountInformation);
        log.info("save balance information success");
    }

    /**
     *  save account information
     *
     * @param transactionInformationDto  transactionInformationDto
     * @return   TransactionInformation
     */
    private TransactionInformation saveAccountInformation(final TransactionInformationDto transactionInformationDto){
        final TransactionInformation transactionInformation = new TransactionInformation();
        BeanUtils.copyProperties(transactionInformationDto, transactionInformation);
        setDateFiled(transactionInformation);
        transactionInformation.setUsername("542064784");
        return transactionInformationRepository.saveAndFlush(transactionInformation);
    }

    /**
     *  set accountInformation date filed
     *
     * @param accountInformation  accountInformation
     */
    private void setDateFiled(final TransactionInformation accountInformation) {
        final LocalDate localDate = LocalDate.now();
        if (null == accountInformation.getYear()) {
            accountInformation.setYear(localDate.getYear());
        }
        if (null == accountInformation.getMonth()) {
            accountInformation.setMonth(localDate.getMonthValue());
        }
        if (null == accountInformation.getDayOfMonth()) {
            accountInformation.setDayOfMonth(localDate.getDayOfMonth());
        }
    }

    /**
     *  save month account information
     *
     * @param accountInformation  saved accountInformation
     */
    private void saveMonthAccountInformation(final TransactionInformation accountInformation){
        final MonthTotal monthTotal = findByExample(accountInformation);
        final BigDecimal money = new BigDecimal(accountInformation.getMoney());
        if (null == monthTotal.getMoney()){
            monthTotal.setMoney(money);
        }else {
            monthTotal.setMoney(monthTotal.getMoney().add(money));
        }
        monthTotalRepository.saveAndFlush(monthTotal);
    }

    /**
     *  find MonthTotal by example
     *  if not isPresent return new
     *
     * @param accountInformation  saved accountInformation
     * @return   MonthTotal
     */
    private MonthTotal findByExample(final TransactionInformation accountInformation) {
        final MonthTotal monthAccountInformation = new MonthTotal();
        monthAccountInformation.setYear(accountInformation.getYear());
        monthAccountInformation.setMonth(accountInformation.getMonth());
        monthAccountInformation.setUsername(accountInformation.getUsername());
        monthAccountInformation.setTransactionType(accountInformation.getTransactionType());
        return monthTotalRepository.findOne(Example.of(monthAccountInformation)).orElse(monthAccountInformation);
    }

    /**
     *  save balance information
     *
     * @param accountInformation saved accountInformation
     */
    private void saveBalanceInformation(final TransactionInformation accountInformation){
        List<BalanceLog> balanceLogs = balanceLogRepository.findByUsernameAndYearGreaterThanEqualAndAndMonthGreaterThanEqual(
                accountInformation.getUsername(), accountInformation.getYear(), accountInformation.getMonth());
        BigDecimal money = new BigDecimal(accountInformation.getMoney());
        if (CollectionUtils.isEmpty(balanceLogs)){
            BalanceLog lastBalanceLog = balanceLogRepository.findFirstByUsernameOrderByYearDescMonthDesc(accountInformation.getUsername());
            if (null == lastBalanceLog){
                lastBalanceLog = createBalanceLog(accountInformation);
                lastBalanceLog.setBalance(money);
                balanceLogRepository.saveAndFlush(lastBalanceLog);
            }else {
                BalanceLog balanceLog = createBalanceLog(accountInformation);
                balanceLog.setBalance(lastBalanceLog.getBalance().add(money));
                balanceLogRepository.saveAndFlush(balanceLog);
            }
        }else {
            balanceLogs.forEach(balanceLog -> balanceLog.setBalance(balanceLog.getBalance().add(money)));
            balanceLogRepository.saveAll(balanceLogs);
        }
    }

    private BalanceLog createBalanceLog(TransactionInformation accountInformation) {
        final BalanceLog balanceLog = new BalanceLog();
        balanceLog.setYear(accountInformation.getYear());
        balanceLog.setMonth(accountInformation.getMonth());
        balanceLog.setUsername(accountInformation.getUsername());
        return balanceLog;
    }
}
