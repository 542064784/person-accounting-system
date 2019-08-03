package com.damon.repository;

import com.damon.entity.BalanceInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *  balance information repository
 *
 * @author Damon Chen
 * @date 2019/08/03
 */
@Repository
public interface BalanceInformationRepository extends JpaRepository<BalanceInformationEntity,String> {

    /**
     *  find last record by username
     *
     * @param username  username
     * @return    BalanceInformationEntity
     */
    BalanceInformationEntity findFirstByUsernameOrderByYearDescMonthDescDayOfMonthDesc(String username);


}
