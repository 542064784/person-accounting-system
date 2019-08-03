package com.damon.service;

import com.damon.dto.AccountInformationDto;
import com.damon.dto.MonthAccountInformationDto;
import com.damon.dto.TotalInformationDto;
import com.damon.entity.AccountInformationEntity;
import com.damon.entity.BalanceInformationEntity;
import com.damon.entity.MonthAccountInformationEntity;
import com.damon.repository.AccountInformationRepository;
import com.damon.repository.BalanceInformationRepository;
import com.damon.repository.MonthAccountInformationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * person account service
 *
 * @author Damon Chen
 * @date 2019/6/10
 */
@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
public class AccountInformationService {

    @Autowired
    private AccountInformationRepository accountInformationRepository;
    @Autowired
    private MonthAccountInformationRepository monthAccountInformationRepository;
    @Autowired
    private BalanceInformationRepository balanceInformationRepository;

    public TotalInformationDto findAccountInformation(final TotalInformationDto totalInformationDto){
        final AccountInformationEntity accountInformation = new AccountInformationEntity();
        BeanUtils.copyProperties(totalInformationDto.getAccountInformationDto(), accountInformation);
        PageRequest pageRequest = PageRequest.of(totalInformationDto.getCurrentPage(), totalInformationDto.getPageSize(),
                Sort.Direction.DESC, "year", "month", "dayOfMonth");
        Page<AccountInformationEntity> page = accountInformationRepository.findAll(Example.of(accountInformation), pageRequest);

        totalInformationDto.setTotalPages(page.getTotalPages());
        totalInformationDto.setTotalElements(page.getTotalElements());

        List<MonthAccountInformationEntity> monthAccountInformationEntityList = monthAccountInformationRepository.
                findByUsernameAndYearAndMonth(accountInformation.getUsername(), accountInformation.getYear(), accountInformation.getMonth());

        List<MonthAccountInformationDto> monthAccountInformationDtoList = new ArrayList<>();
        monthAccountInformationEntityList.forEach(monthAccountInformationEntity -> {
            if (null != monthAccountInformationEntity){
                MonthAccountInformationDto monthAccountInformationDto = new MonthAccountInformationDto();
                BeanUtils.copyProperties(monthAccountInformationEntity, monthAccountInformationDto);
                monthAccountInformationDtoList.add(monthAccountInformationDto);
            }
        });

        totalInformationDto.setMonthAccountInformationDtoList(monthAccountInformationDtoList);

        List<AccountInformationEntity> accountInformationEntityList = page.getContent();
        List<AccountInformationDto> accountInformationDtoList = new ArrayList<>();
        accountInformationEntityList.forEach(accountInformationEntity -> {
            if (null != accountInformationEntity){
                AccountInformationDto accountInformationDto = new AccountInformationDto();
                BeanUtils.copyProperties(accountInformationEntity, accountInformationDto);
                accountInformationDtoList.add(accountInformationDto);
            }
        });
        totalInformationDto.setAccountInformationDtoList(accountInformationDtoList);

        BalanceInformationEntity balanceInformation = balanceInformationRepository.findFirstByUsernameOrderByYearDescMonthDescDayOfMonthDesc(accountInformation.getUsername());
        if (null == balanceInformation){
            totalInformationDto.setBalance(BigDecimal.ZERO);
        }else {
            totalInformationDto.setBalance(balanceInformation.getBalance());
        }

        return totalInformationDto;
    }

    /**
     *  process account information
     *
     * @param accountInformationDto accountInformationDto
     * @return  AccountInformationEntity
     */
    public AccountInformationEntity processAccountInformation(final AccountInformationDto accountInformationDto){
        final AccountInformationEntity accountInformation = saveAccountInformation(accountInformationDto);
        log.info("save account information success");
        saveMonthAccountInformation(accountInformation);
        log.info("save month account information success");
        saveBalanceInformation(accountInformation);
        log.info("save balance information success");
        return accountInformation;
    }

    /**
     *  save account information
     *
     * @param accountInformationDto  accountInformationDto
     * @return   AccountInformationEntity
     */
    private AccountInformationEntity saveAccountInformation(final AccountInformationDto accountInformationDto){
        final AccountInformationEntity accountInformationEntity = new AccountInformationEntity();
        BeanUtils.copyProperties(accountInformationDto, accountInformationEntity);
        setDateFiled(accountInformationEntity);
        return accountInformationRepository.saveAndFlush(accountInformationEntity);
    }

    /**
     *  set accountInformation date filed
     *
     * @param accountInformation  accountInformation
     */
    private void setDateFiled(final AccountInformationEntity accountInformation) {
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
    private void saveMonthAccountInformation(final AccountInformationEntity accountInformation){
        final MonthAccountInformationEntity monthAccountInformationEntity = findByExample(accountInformation);
        final BigDecimal amount = new BigDecimal(accountInformation.getAmount());
        monthAccountInformationEntity.setMoney(monthAccountInformationEntity.getMoney().add(amount));
        monthAccountInformationRepository.saveAndFlush(monthAccountInformationEntity);
    }

    /**
     *  find MonthAccountInformationEntity by example
     *  if not isPresent return new
     *
     * @param accountInformation  saved accountInformation
     * @return   MonthAccountInformationEntity
     */
    private MonthAccountInformationEntity findByExample(final AccountInformationEntity accountInformation) {
        final MonthAccountInformationEntity monthAccountInformation = new MonthAccountInformationEntity();
        monthAccountInformation.setYear(accountInformation.getYear());
        monthAccountInformation.setMonth(accountInformation.getMonth());
        monthAccountInformation.setUsername(accountInformation.getUsername());
        monthAccountInformation.setTransactionType(accountInformation.getTransactionType());
        return monthAccountInformationRepository.findOne(Example.of(monthAccountInformation)).orElse(monthAccountInformation);
    }

    /**
     *  save balance information
     *
     * @param accountInformation saved accountInformation
     */
    private void saveBalanceInformation(final AccountInformationEntity accountInformation){
        final BalanceInformationEntity lastBalanceInformation = balanceInformationRepository.
                findFirstByUsernameOrderByYearDescMonthDescDayOfMonthDesc(accountInformation.getUsername());
        final BalanceInformationEntity balanceInformation = new BalanceInformationEntity();
        balanceInformation.setYear(accountInformation.getYear());
        balanceInformation.setMonth(accountInformation.getMonth());
        balanceInformation.setDayOfMonth(accountInformation.getDayOfMonth());
        balanceInformation.setUsername(accountInformation.getUsername());
        balanceInformation.setDescription(accountInformation.getDescription());
        balanceInformation.setTransactionType(accountInformation.getTransactionType());
        balanceInformation.setAmount(accountInformation.getAmount());
        BigDecimal amount = new BigDecimal(accountInformation.getAmount());
        if (null == lastBalanceInformation){
            balanceInformation.setBalance(amount);
        }else {
            balanceInformation.setBalance(balanceInformation.getBalance().add(amount));
        }
        balanceInformationRepository.saveAndFlush(balanceInformation);
    }
}
