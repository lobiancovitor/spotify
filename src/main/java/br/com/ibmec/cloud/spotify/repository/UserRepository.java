package br.com.ibmec.cloud.spotify.repository;

import br.com.ibmec.cloud.spotify.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByEmailAndPassword(String email, String password);
}
