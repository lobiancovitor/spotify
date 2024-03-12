package br.com.ibmec.cloud.spotify.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ManyToAny;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String nome;

    @ManyToOne
    private Usuario usuario;

    @OneToMany
    private List<Musica> musicas;


}
