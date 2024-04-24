package br.com.ibmec.cloud.spotify.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Plan plan;

    @ManyToOne
    @JsonIgnore
    private User user;

    @Column
    private boolean active;
}
