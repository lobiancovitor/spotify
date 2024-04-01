package br.com.ibmec.cloud.spotify.controller.request;

import lombok.Data;

import java.util.UUID;

@Data
public class MusicRequest {
    private UUID id;
    private String name;
    private String description;
    private Integer duration;
}
