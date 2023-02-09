package com.trialtask.repository;

import com.trialtask.domain.Quote;
import com.trialtask.domain.User;
import com.trialtask.domain.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VoteRepository extends PagingAndSortingRepository<Vote, Long> {
    Page<Vote> findAllByUser(User user, Pageable pageable);

    List<Vote> findByQuoteOrderByDateTime(Quote quote);
}