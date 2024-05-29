package com.example.IP_Project.Repo;

import com.example.IP_Project.Entity.LocuriParcare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocuriParcareRepo extends JpaRepository<LocuriParcare, Long> {
    Optional<LocuriParcare> findFirstByOrderByIdLocParcareAsc();
}
