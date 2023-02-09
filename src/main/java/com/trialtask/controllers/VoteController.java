package com.trialtask.controllers;

import com.trialtask.domain.Vote;
import com.trialtask.services.VoteService;
import com.trialtask.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    VoteService voteService;

    @PutMapping("/upvote/{quoteId}")
    public Response upvote(@PathVariable Long quoteId, @RequestParam String username) throws Exception {
        voteService.vote(quoteId, username, true);
        return new Response("OK");
    }

    @PutMapping("/downvote/{quoteId}")
    public Response downvote(@PathVariable Long quoteId, @RequestParam String username) throws Exception {
        voteService.vote(quoteId, username, false);
        return new Response("OK");
    }

    @GetMapping("/last-votes")
    public CollectionModel<Vote> getLastVotes(@RequestParam String username) {
        return CollectionModel.of(voteService.getLastVotes(username));
    }
}
