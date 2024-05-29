package com.example.IP_Project.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prezenta_utilizatori")
public class PrezentaUtilizatori {

    @Id
    @Column(name = "id_prezenta")
    private String idPrezenta;

    @ManyToOne
    @JoinColumn(name = "utilizator_id")
    private Utilizatori utilizator;

    @Column(name = "data_intrare")
    private LocalDate dataIntrare;

    @Column(name = "ora_intrare")
    private String oraIntrare;

    @Column(name = "data_iesire")
    private LocalDate dataIesire;

    @Column(name = "ora_iesire")
    private String oraIesire;

    public PrezentaUtilizatori() {
    }

    public PrezentaUtilizatori(String idPrezenta, Utilizatori utilizator, LocalDate dataIntrare, String oraIntrare, LocalDate dataIesire, String oraIesire) {
        this.idPrezenta = idPrezenta;
        this.utilizator = utilizator;
        this.dataIntrare = dataIntrare;
        this.oraIntrare = oraIntrare;
        this.dataIesire = dataIesire;
        this.oraIesire = oraIesire;

    }

    public String getIdPrezenta() {
        return idPrezenta;
    }

    public void setIdPrezenta(String idPrezenta) {
        this.idPrezenta = idPrezenta;
    }

    public Utilizatori getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizatori utilizator) {
        this.utilizator = utilizator;
    }

    public LocalDate getDataIntrare() {
        return dataIntrare;
    }

    public void setDataIntrare(LocalDate dataIntrare) {
        this.dataIntrare = dataIntrare;
    }

    public String getOraIntrare() {
        return oraIntrare;
    }

    public void setOraIntrare(String oraIntrare) {
        this.oraIntrare = oraIntrare;
    }

    public LocalDate getDataIesire() {
        return dataIesire;
    }

    public void setDataIesire(LocalDate dataIesire) {
        this.dataIesire = dataIesire;
    }

    public String getOraIesire() {
        return oraIesire;
    }

    public void setOraIesire(String oraIesire) {
        this.oraIesire = oraIesire;
    }

    @Override
    public String toString() {
        return "PrezentaUtilizatori{" +
                "idPrezenta='" + idPrezenta + '\'' +
                ", dataIntrare=" + dataIntrare +
                ", oraIntrare='" + oraIntrare + '\'' +
                ", dataIesire=" + dataIesire +
                ", oraIesire='" + oraIesire + '\'' +
                '}';
    }

}
