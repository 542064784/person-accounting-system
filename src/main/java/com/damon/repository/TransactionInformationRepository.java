package com.damon.repository;

import com.damon.entity.TransactionInformation;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
     * @param currentPage  currentPage
     * @param pageSize     pageSize
     * @return           accountInformationEntity collection
     */
    default Page<TransactionInformation> findTransactionInformation(final Integer year, final Integer month,
                                                                    final String username, final Integer currentPage,
                                                                    final Integer pageSize){
        final Sort sort = Sort.by(Sort.Direction.DESC, "year", "month", "dayOfMonth");
        final Pageable pageable = PageRequest.of(currentPage, pageSize, sort);
        TransactionInformation transactionInformation = new TransactionInformation();
        transactionInformation.setYear(year);
        transactionInformation.setMonth(month);
        transactionInformation.setUsername(username);
        return findAll(Example.of(transactionInformation),pageable);
    }

}
