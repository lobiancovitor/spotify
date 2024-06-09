package br.com.ibmec.cloud.spotify.controller.request;

import br.com.ibmec.cloud.spotify.models.Band;
import lombok.Data;

import java.util.UUID;

@Data
public class MusicRequest {
    private UUID id;
    private String name;
    private Integer duration;
    private Band band;
}
