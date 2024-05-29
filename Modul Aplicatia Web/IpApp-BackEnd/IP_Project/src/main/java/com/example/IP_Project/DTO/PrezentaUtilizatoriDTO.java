package com.example.IP_Project.DTO;

import java.time.LocalDate;

public class PrezentaUtilizatoriDTO {
    private String idPrezenta;
    private Long utilizatorId;
    private LocalDate dataIntrare;
    private String oraIntrare;
    private LocalDate dataIesire;
    private String oraIesire;

    public PrezentaUtilizatoriDTO() {
    }

    public PrezentaUtilizatoriDTO(String idPrezenta, Long utilizatorId, LocalDate dataIntrare, String oraIntrare, LocalDate dataIesire, String oraIesire) {
        this.idPrezenta = idPrezenta;
        this.utilizatorId = utilizatorId;
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

    public Long getUtilizatorId() {
        return utilizatorId;
    }

    public void setUtilizatorId(Long utilizatorId) {
        this.utilizatorId = utilizatorId;
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
        return "PrezentaUtilizatoriDTO{" +
                "idPrezenta=" + idPrezenta +
                ", utilizatorId=" + utilizatorId +
                ", dataIntrare=" + dataIntrare +
                ", oraIntrare=" + oraIntrare +
                ", dataIesire=" + dataIesire +
                ", oraIesire=" + oraIesire +
                '}';
    }
}
