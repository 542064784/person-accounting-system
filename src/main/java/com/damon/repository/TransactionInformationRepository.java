package com.damon.repository;

import com.damon.entity.TransactionInformation;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  account information repository
 *
 * @author Damon Chen
 * @date 2019/07/27
 */
@Repository
public interface TransactionInformationRepository extends JpaRepository<TransactionInformation,String> {

    /**
     *  find accountInformationEntity collection by year month username
     *  order by year month dayOfMonth  desc
     *
     * @param year      year
     * @param month     month
     * @param username  username
     * @return           accountInformationEntity collection
     */
    default List<TransactionInformation> findTransactionInformation(Integer year, Integer month, String username){
        Sort sort = Sort.by(Sort.Direction.DESC, "year", "month", "dayOfMonth");
        TransactionInformation transactionInformation = new TransactionInformation();
        transactionInformation.setYear(year);
        transactionInformation.setMonth(month);
        transactionInformation.setUsername(username);
        return findAll(Example.of(transactionInformation),sort);
    }

}
