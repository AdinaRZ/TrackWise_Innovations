package com.example.IP_Project.DTO;

import jakarta.persistence.Column;

public class LocuriParcareDTO {
    private Long idLocParcare;
    private int numarLocuri;

    public LocuriParcareDTO() {
    }
    public LocuriParcareDTO(Long idLocParcare, int numarLocuri) {
        this.idLocParcare = idLocParcare;
        this.numarLocuri = numarLocuri;
    }

    public Long getIdLocParcare() {
        return idLocParcare;
    }

    public void setIdLocParcare(Long idLocParcare) {
        this.idLocParcare = idLocParcare;
    }

    public int getNumarLocuri() {
        return numarLocuri;
    }

    public void setNumarLocuri(int numarLocuri) {
        this.numarLocuri = numarLocuri;
    }

    @Override
    public String toString() {
        return "LocuriParcare{" +
                "idLocParcare=" + idLocParcare +
                ", numarLocuri=" + numarLocuri +
                '}';
    }
}
