package com.damon.service;

import com.damon.dto.AccountInformationDto;
import com.damon.entity.AccountInformationEntity;
import com.damon.repository.AccountInformationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Objects;
/**
 * person account service
 *
 * @author Damon Chen
 * @date 2019/6/10
 */
@Service
@Transactional(rollbackOn = Exception.class)
@Slf4j
public class PersonalAccountService {

    @Autowired
    private AccountInformationRepository accountInformationRepository;

    /**
     *  save account information
     *
     * @param accountInformationDto  accountInformationDto
     */
    public void saveAccountInformation(final AccountInformationDto accountInformationDto){
        AccountInformationEntity accountInformationEntity = new AccountInformationEntity();
        BeanUtils.copyProperties(accountInformationDto, accountInformationEntity);
        setDateFiled(accountInformationEntity);
        accountInformationRepository.saveAndFlush(accountInformationEntity);
    }

    /**
     *  set accountInformationEntity date filed
     *
     * @param accountInformationEntity  accountInformationEntity
     */
    private void setDateFiled(AccountInformationEntity accountInformationEntity) {
        LocalDate localDate = LocalDate.now();
        if (Objects.isNull(accountInformationEntity.getYear())) {
            accountInformationEntity.setYear(localDate.getYear());
        }
        if (Objects.isNull(accountInformationEntity.getMonth())) {
            accountInformationEntity.setMonth(localDate.getMonthValue());
        }
        if (Objects.isNull(accountInformationEntity.getDayOfMonth())) {
            accountInformationEntity.setDayOfMonth(localDate.getDayOfMonth());
        }
    }


}
