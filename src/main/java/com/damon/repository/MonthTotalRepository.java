package com.damon.repository;

import com.damon.entity.MonthTotal;
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
public interface MonthTotalRepository extends JpaRepository<MonthTotal,String> {


    /**
     *  find MonthTotal by username year month
     *
     * @param username   username
     * @param year       year
     * @param month      month
     * @return           MonthTotal
     */
    List<MonthTotal> findByUsernameAndYearAndMonth(final String username, final Integer year, final Integer month);

}
