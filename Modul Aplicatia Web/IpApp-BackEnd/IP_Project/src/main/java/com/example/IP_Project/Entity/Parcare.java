package com.example.IP_Project.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Parcare")
public class Parcare {

    @Id
    private Long id;

    private String numarInmatriculare;
    private LocalDate dataIntrare;
    private LocalTime oraIntrare;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumarInmatriculare() {
        return numarInmatriculare;
    }

    public void setNumarInmatriculare(String numarInmatriculare) {
        this.numarInmatriculare = numarInmatriculare;
    }

    public LocalDate getDataIntrare() {
        return dataIntrare;
    }

    public void setDataIntrare(LocalDate dataIntrare) {
        this.dataIntrare = dataIntrare;
    }

    public LocalTime getOraIntrare() {
        return oraIntrare;
    }

    public void setOraIntrare(LocalTime oraIntrare) {
        this.oraIntrare = oraIntrare;
    }
}
