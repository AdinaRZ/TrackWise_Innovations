package com.example.IP_Project.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "locuri_parcare")
public class LocuriParcare {

    @Id
    @Column(name = "id_loc_parcare", length = 245)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocParcare;
    @Column(name = "numar_locuri", length = 245)
    private int numarLocuri;

    public LocuriParcare() {
    }
    public LocuriParcare(Long idLocParcare, int numarLocuri) {
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
