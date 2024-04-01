package br.com.ibmec.cloud.spotify.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany
    private List<Music> musics;
}
