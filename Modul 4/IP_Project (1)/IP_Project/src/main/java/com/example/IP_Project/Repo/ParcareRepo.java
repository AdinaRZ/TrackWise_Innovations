package com.example.IP_Project.Repo;

import com.example.IP_Project.Entity.Parcare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ParcareRepo extends JpaRepository<Parcare, String> {
    List<Parcare> findByNumarInmatriculareAndDataIntrare(String numarInmatriculare, LocalDate dataIntrare);
}
