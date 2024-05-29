package com.example.IP_Project.Repo;

import com.example.IP_Project.Entity.PrezentaUtilizatori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface PrezentaUtilizatoriRepo extends JpaRepository<PrezentaUtilizatori, String> {
    List<PrezentaUtilizatori> findByUtilizatorId(Long utilizatorId);

}
