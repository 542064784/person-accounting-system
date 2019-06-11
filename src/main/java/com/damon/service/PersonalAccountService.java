package com.damon.service;

import com.damon.dto.DailyAccountInformationDto;
import com.damon.entity.DailyAccountInformationEntity;
import com.damon.repository.DailyAccountInformationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private DailyAccountInformationRepository dailyAccountInformationRepository;

    public void addPersonalAccount(final DailyAccountInformationDto dailyAccountInformationDto) {
        DailyAccountInformationEntity dailyAccountInformationEntity = new DailyAccountInformationEntity();
        BeanUtils.copyProperties(dailyAccountInformationDto, dailyAccountInformationEntity);
        setDateFiled(dailyAccountInformationEntity);
        dailyAccountInformationRepository.saveAndFlush(dailyAccountInformationEntity);
    }

    private void setDateFiled(final DailyAccountInformationEntity dailyAccountInformationEntity) {
        LocalDate localDate = LocalDate.now();
        if (Objects.isNull(dailyAccountInformationEntity.getYear())) {
            dailyAccountInformationEntity.setYear(localDate.getYear());
        }
        if (Objects.isNull(dailyAccountInformationEntity.getMonth())) {
            dailyAccountInformationEntity.setMonth(localDate.getMonthValue());
        }
        if (Objects.isNull(dailyAccountInformationEntity.getDayOfMonth())) {
            dailyAccountInformationEntity.setDayOfMonth(localDate.getDayOfMonth());
        }
    }


    public List<DailyAccountInformationDto> findDailyAccountInformation(final Integer year,final Integer month,final Integer dayOfMonth){
        List<DailyAccountInformationDto> dailyAccountInformationDtos = new ArrayList<>();
        List<DailyAccountInformationEntity> dailyAccountInformationEntityList = dailyAccountInformationRepository.findByYearAndMonthAndDayOfMonthAndDeleteFlagIsFalse(year, month, dayOfMonth);
        dailyAccountInformationEntityList.forEach(dailyAccountInformationEntity -> {
            DailyAccountInformationDto dailyAccountInformationDto = new DailyAccountInformationDto();
            BeanUtils.copyProperties(dailyAccountInformationEntity, dailyAccountInformationDto);
            dailyAccountInformationDtos.add(dailyAccountInformationDto);
        });
        return dailyAccountInformationDtos;
    }


}
