package com.damon.repository;

import com.damon.entity.BalanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  balance information repository
 *
 * @author Damon Chen
 * @date 2019/08/03
 */
@Repository
public interface BalanceLogRepository extends JpaRepository<BalanceLog,String> {

    /**
     *  find last record by username
     *
     * @param username  username
     * @return    BalanceLog
     */
    BalanceLog findFirstByUsernameOrderByYearDescMonthDesc(String username);


    /**
     *  find by year month username
     *
     * @param username   username
     * @param year        year
     * @param month      month
     * @return            balanceLog
     */
    BalanceLog findByUsernameAndYearAndMonth(String username,Integer year,Integer month);


    /**
     *  find all by >= year >= month and username
     *
     * @param username   username
     * @param year        year
     * @param month       month
     * @return             balanceLog collection
     */
    List<BalanceLog> findByUsernameAndYearGreaterThanEqualAndAndMonthGreaterThanEqual(String username,Integer year,Integer month);


}
