package com.example.IP_Project.Repo;

import com.example.IP_Project.Entity.Cladire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CladireRepo extends JpaRepository<Cladire, Long> {
    Optional<Cladire> findFirstByOrderByIdCladireAsc();
}
