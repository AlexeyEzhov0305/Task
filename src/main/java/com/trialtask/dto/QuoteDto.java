package com.trialtask.dto;

import com.trialtask.domain.User;
import lombok.Getter;

@Getter
public class QuoteDto {
    private Long id;
    private User author;
    private Long score;
    private String text;

    public QuoteDto(Long id, User author, Long score, String text) {
        this.id = id;
        this.author = author;
        this.score = score;
        this.text = text;
    }
}
