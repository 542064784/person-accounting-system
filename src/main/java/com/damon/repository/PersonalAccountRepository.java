package com.damon.repository;

import com.damon.entity.PersonalAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Damon Chen
 * @date 2019/6/10
 */
@Repository
public interface PersonalAccountRepository extends JpaRepository<PersonalAccountEntity,Long> {
}
