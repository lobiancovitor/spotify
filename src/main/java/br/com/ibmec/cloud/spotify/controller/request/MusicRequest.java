package br.com.ibmec.cloud.spotify.controller.request;

import br.com.ibmec.cloud.spotify.models.Band;
import lombok.Data;

import java.util.UUID;

@Data
public class MusicRequest {
    private UUID id;
    private String name;
    private String description;
    private String image;
    private Band band;
}
