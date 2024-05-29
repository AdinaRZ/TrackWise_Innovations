package com.example.IP_Project.Repo;

import com.example.IP_Project.Entity.Utilizatori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UtilizatoriRepo extends JpaRepository<Utilizatori, Long> {
    Optional<Utilizatori> findOneByEmailAndPassword(String email, String password);
    Utilizatori findByEmail(String email);
    Optional<Utilizatori> findByCnp(Long cnp);
}
