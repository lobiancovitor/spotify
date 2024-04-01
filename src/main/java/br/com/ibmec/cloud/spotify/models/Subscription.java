package br.com.ibmec.cloud.spotify.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Plan plan;

    @ManyToOne
    private User user;

    @Column
    private boolean active;
}
