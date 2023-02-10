package com.trialtask.services;

import com.trialtask.domain.Quote;
import com.trialtask.domain.User;
import com.trialtask.domain.Vote;
import com.trialtask.dto.QuoteDto;
import com.trialtask.repository.QuoteRepository;
import com.trialtask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class QuoteService {
    QuoteRepository quoteRepository;
    UserRepository userRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository, UserRepository userRepository) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
    }

    public void createQuote(String text, String username) throws Exception {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new Exception(String.format("User with %s not exist", username)));

        Quote quote = new Quote().setAuthor(user).setText(text).setScore(0L);
        quoteRepository.save(quote);
    }

    public Quote findById(Long quoteId) throws Exception {
        return quoteRepository.findById(quoteId).orElseThrow(() -> new Exception(String.format("Quote with id %s not found", quoteId)));
    }

    public void deleteQuote(Long quoteId) {
        quoteRepository.deleteById(quoteId);
    }

    public void update(QuoteDto quoteDto) throws Exception {
        Quote quote = quoteRepository.findById(quoteDto.getId())
                .orElseThrow(() -> new Exception(String.format("Quote %s does not exist", quoteDto.getText())));
        quote.setText(quoteDto.getText());
        quote.setAuthor(quoteDto.getAuthor());
        quote.setScore(quoteDto.getScore());
        quoteRepository.save(quote);
    }

    public Page<Quote> getTopQuotes() {
        return quoteRepository.findAll(PageRequest.of(0, 10, Sort.by("score").descending()));
    }

    public Page<Quote> getFlopQuotes() {
        return quoteRepository.findAll(PageRequest.of(0, 10, Sort.by("score")));
    }

    public List<Vote> getVotes(Long quoteId) throws Exception {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new Exception(String.format("Quote %s does not exist", quoteId)));
        return quote.getVotes();
    }

    public Quote getRandom() {
        return quoteRepository.getRandom();
    }
}
