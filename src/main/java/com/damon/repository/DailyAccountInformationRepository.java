package com.damon.repository;

import com.damon.entity.DailyAccountInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  日账单 repository
 *
 * @author Damon Chen
 * @date 2019/6/10
 */
@Repository
public interface DailyAccountInformationRepository extends JpaRepository<DailyAccountInformationEntity,Long> {

    /**
     *   find dailyAccountInformationEntity collection by day of month and year and month
     *
     * @param year         year
     * @param month        month
     * @param dayOfMonth   day of month
     * @return             dailyAccountInformationEntity collection
     */
    List<DailyAccountInformationEntity> findByYearAndMonthAndDayOfMonthAndDeleteFlagIsFalse(final Integer year,final Integer month,final Integer dayOfMonth);

}
