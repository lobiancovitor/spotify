package br.com.ibmec.cloud.spotify.repository;

import br.com.ibmec.cloud.spotify.models.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, UUID> {
}
