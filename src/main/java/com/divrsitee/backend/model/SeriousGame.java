package com.divrsitee.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
@Table(name = "serious_game")
public class SeriousGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "slogan")
    private String slogan;

    @Column(name = "completion_time")
    private String completionTime;

    @OneToMany(mappedBy = "seriousGame", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AppUser> appUsers;

    @OneToMany(mappedBy = "seriousGame", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Image> images;
}
