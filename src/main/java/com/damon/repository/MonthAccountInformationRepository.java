package com.damon.repository;

import com.damon.entity.MonthAccountInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  month account information repository
 *
 * @author Damon Chen
 * @date 2019/08/03
 */
@Repository
public interface MonthAccountInformationRepository extends JpaRepository<MonthAccountInformationEntity,String> {


    /**
     *  find MonthAccountInformationEntity by username year month
     *
     * @param username   username
     * @param year       year
     * @param month      month
     * @return           MonthAccountInformationEntity
     */
    List<MonthAccountInformationEntity> findByUsernameAndYearAndMonth(final String username,final Integer year,final Integer month);

}
