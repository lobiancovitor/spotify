package br.com.ibmec.cloud.spotify.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AzureSearchIndex {
    private String id;
    private String banda;

    @JsonProperty(value = "nome_musica")
    private String nomeMusica;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBanda() {
        return banda;
    }

    public void setBanda(String banda) {
        this.banda = banda;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }
}
