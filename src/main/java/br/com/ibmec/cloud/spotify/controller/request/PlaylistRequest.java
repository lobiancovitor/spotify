package br.com.ibmec.cloud.spotify.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlaylistRequest {
    @NotBlank(message = "Nome da playlist é obrigatório")
    private String name;
}
