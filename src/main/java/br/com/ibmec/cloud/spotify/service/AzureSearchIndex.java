package br.com.ibmec.cloud.spotify.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AzureSearchIndex {
    private String id;
    private String banda;

    @JsonProperty(value = "nome_musicas")
    private String nomeMusica;
}
