package com.trialtask.repository;

import com.trialtask.domain.Quote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends PagingAndSortingRepository<Quote, Long> {
    @Query(value = "Select * from Quotes order by rand() limit 1", nativeQuery = true)
    Quote getRandom();
}