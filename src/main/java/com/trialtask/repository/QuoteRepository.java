package com.trialtask.repository;

import com.trialtask.domain.Quote;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends PagingAndSortingRepository<Quote, Long> {
}