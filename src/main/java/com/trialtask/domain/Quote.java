package com.trialtask.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "Quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "author_ID")
    private User author;

    @Column(name = "score")
    private Long score;

    @NonNull
    @Column(name = "text")
    private String text;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "votes")
    private List<Vote> votes;
}
