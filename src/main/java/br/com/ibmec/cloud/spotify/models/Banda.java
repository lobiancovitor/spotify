package br.com.ibmec.cloud.spotify.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.UUID;

@Data
@Entity
public class Banda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private String imagem;

    @OneToMany
    @JoinColumn(name = "banda_id", referencedColumnName = "musica_id")
    private ArrayList<Musica> musicas;
}
