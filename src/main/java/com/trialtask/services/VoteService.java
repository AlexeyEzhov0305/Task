package com.trialtask.services;

import com.trialtask.domain.Quote;
import com.trialtask.domain.User;
import com.trialtask.domain.Vote;
import com.trialtask.repository.QuoteRepository;
import com.trialtask.repository.UserRepository;
import com.trialtask.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class VoteService {
    QuoteRepository quoteRepository;
    UserRepository userRepository;
    VoteRepository voteRepository;

    @Autowired
    public VoteService(QuoteRepository quoteRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    public Page<Vote> getLastVotes(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username %s", username)));

        return voteRepository.findAllByUser(user, PageRequest.of(0, 5, Sort.by("dateTime").descending()));
    }

    public void vote(Long quoteId, String username, boolean up) throws Exception {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username %s", username)));
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new Exception(String.format("Quote with id %s not found", quoteId)));

        Long newScore = up ? quote.getScore() + 1 : quote.getScore() - 1;
        quote.setScore(newScore);
        quoteRepository.save(quote);

        voteRepository.save(new Vote(user, quote, newScore));
    }
}
