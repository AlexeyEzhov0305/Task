package com.trialtask.controllers;

import com.trialtask.domain.Quote;
import com.trialtask.domain.Vote;
import com.trialtask.dto.QuoteDto;
import com.trialtask.services.QuoteService;
import com.trialtask.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quotes")
public class QuoteController {
    @Autowired
    QuoteService quoteService;

    @PostMapping("/create")
    public Response create(@RequestParam String text,
                           @RequestParam String username) throws Exception {
        quoteService.createQuote(text, username);
        return new Response("OK");
    }

    @DeleteMapping("/{quoteId}")
    public Response delete(@PathVariable Long quoteId) {
        quoteService.deleteQuote(quoteId);
        return new Response("OK");
    }

    @GetMapping("/{quoteId}")
    public EntityModel<Quote> getById(@PathVariable Long quoteId) throws Exception {
        return EntityModel.of(quoteService.findById(quoteId));
    }

    @PutMapping
    public Response update(@RequestBody QuoteDto quoteDto) throws Exception {
        quoteService.update(quoteDto);
        return new Response("OK");
    }

    @GetMapping("/top-quotes")
    public CollectionModel<Quote> getTopQuotes() {
        return CollectionModel.of(quoteService.getTopQuotes());
    }

    @GetMapping("/flop-quotes")
    public CollectionModel<Quote> getFlopQuotes() {
        return CollectionModel.of(quoteService.getFlopQuotes());
    }

    @GetMapping("/votes/{quoteId}")
    public CollectionModel<Vote> getVotesForQuote(@PathVariable Long quoteId) throws Exception {
        return CollectionModel.of(quoteService.getVotes(quoteId));
    }

    @GetMapping("/random")
    public EntityModel<Quote> getRandomQuote() {
        return EntityModel.of(quoteService.getRandom());
    }
}
