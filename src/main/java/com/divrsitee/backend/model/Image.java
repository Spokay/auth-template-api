package com.divrsitee.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link")
    private String link;

    @Column(name = "alt")
    private String alt;

    @ManyToOne
    @JoinColumn(name = "serious_game_id")
    private SeriousGame seriousGame;
}
