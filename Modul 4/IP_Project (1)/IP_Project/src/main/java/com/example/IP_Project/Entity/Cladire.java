package com.example.IP_Project.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cladire")
public class Cladire {

    @Id
    @Column(name = "id_cladire", length = 245)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCladire;

    @Column(name = "etaje", length = 245)
    private int etaj;

    @Column(name = "birouri", length = 245)
    private int birou;

    @Column(name = "pozitii_birouri", length = 245)
    private int pozitieBirou;

    public Cladire() {
    }

    public Cladire(Long idCladire, int etaj, int birou, int pozitieBirou) {
        this.idCladire = idCladire;
        this.etaj = etaj;
        this.birou = birou;
        this.pozitieBirou = pozitieBirou;
    }

    public Long getIdCladire() {
        return idCladire;
    }

    public void setIdCladire(Long idCladire) {
        this.idCladire = idCladire;
    }

    public int getEtaj() {
        return etaj;
    }

    public void setEtaj(int etaj) {
        this.etaj = etaj;
    }

    public int getBirou() {
        return birou;
    }

    public void setBirou(int birou) {
        this.birou = birou;
    }

    public int getPozitieBirou() {
        return pozitieBirou;
    }

    public void setPozitieBirou(int pozitieBirou) {
        this.pozitieBirou = pozitieBirou;
    }

    @Override
    public String toString() {
        return "Cladire{" +
                "idCladire=" + idCladire +
                ", etaj=" + etaj +
                ", birou='" + birou + '\'' +
                ", pozitieBirou='" + pozitieBirou + '\'' +
                '}';
    }
}
