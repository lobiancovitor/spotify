package br.com.ibmec.cloud.spotify.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Entity
public class User {

    public User() {
        this.playlist = new ArrayList<>();
        this.subscriptions = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

    @Column
    @NotBlank(message = "Campo email é obrigatório")
    @Email(message = "Campo email não está em um formato correto")
    private String email;

    @Column
    @NotBlank(message = "Campo senha é obrigatório")
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Playlist> playlist;

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Subscription> subscriptions;
}
