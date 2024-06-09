package br.com.ibmec.cloud.spotify.controller.request;

import br.com.ibmec.cloud.spotify.models.Music;
import br.com.ibmec.cloud.spotify.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class PlaylistRequest {
    private UUID id;

    @NotBlank(message = "Nome da playlist é obrigatório")
    private String name;

    private User user;

    private List<Music> musics = new ArrayList<>();


}
