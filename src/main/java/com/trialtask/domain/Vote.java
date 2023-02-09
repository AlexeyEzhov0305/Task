package com.trialtask.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Votes")
@Setter
@Getter
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "quote_id")
    private Quote quote;

    @Column(name = "score")
    private Long score;

    public Vote(User user, Quote quote, Long score) {
        this.user = user;
        this.dateTime = LocalDateTime.now();
        this.quote = quote;
        this.score = score;
    }
}
