package br.com.ibmec.cloud.spotify.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @Column
    private double price;

    @OneToMany
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private List<Subscription> subscriptions;
}
