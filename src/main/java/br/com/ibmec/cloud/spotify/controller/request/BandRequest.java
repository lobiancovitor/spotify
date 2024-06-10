package br.com.ibmec.cloud.spotify.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BandRequest {

    private UUID id;

    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

    private List<MusicRequest> musics;
}
