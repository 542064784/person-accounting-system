package com.damon.repository;

import com.damon.entity.AccountInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *  account information repository
 *
 * @author Damon Chen
 * @date 2019/07/27
 */
@Repository
public interface AccountInformationRepository extends JpaRepository<AccountInformationEntity,String> {
}
