package br.com.ibmec.cloud.spotify.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Plan {

    public Plan() {
    }

    public Plan(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private Boolean active;

    @OneToMany
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private List<Subscription> subscriptions;
}
