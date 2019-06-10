package com.damon.service;

import com.damon.dto.PersonalAccountDto;
import com.damon.entity.PersonalAccountEntity;
import com.damon.repository.PersonalAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * person account service
 *
 * @author Damon Chen
 * @date 2019/6/10
 */
@Service
@Slf4j
public class PersonalAccountService {

    @Autowired
    private PersonalAccountRepository personalAccountRepository;

    public void addPersonalAccount(final PersonalAccountDto personalAccountDto) {
        PersonalAccountEntity personalAccountEntity = new PersonalAccountEntity();
        BeanUtils.copyProperties(personalAccountDto, personalAccountEntity);
        setDateFiled(personalAccountEntity);
        setAmountFiled(personalAccountEntity);
        personalAccountRepository.saveAndFlush(personalAccountEntity);
    }

    private void setAmountFiled(final PersonalAccountEntity personalAccountEntity){
        String amount = personalAccountEntity.getAmount();
        if (amount.startsWith("-")){
            personalAccountEntity.setExpense(new BigDecimal(amount));
        }else {
            personalAccountEntity.setIncome(new BigDecimal(amount));
        }
    }

    private void setDateFiled(final PersonalAccountEntity personalAccountEntity) {
        LocalDate localDate = LocalDate.now();
        if (Objects.isNull(personalAccountEntity.getYear())) {
            personalAccountEntity.setYear(localDate.getYear());
        }
        if (Objects.isNull(personalAccountEntity.getMonth())) {
            personalAccountEntity.setMonth(localDate.getMonthValue());
        }
        if (Objects.isNull(personalAccountEntity.getDayOfMonth())) {
            personalAccountEntity.setDayOfMonth(localDate.getDayOfMonth());
        }
    }


}
