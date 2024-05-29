package com.example.IP_Project.DTO;

public class CladireDTO {
    private Long idCladire;
    private int etaj;
    private int birou;
    private int pozitieBirou;

    public CladireDTO() {
    }

    public CladireDTO(Long idCladire, int etaj, int birou, int pozitieBirou) {
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
        return "CladireDTO{" +
                "idCladire=" + idCladire +
                ", etaj=" + etaj +
                ", birou=" + birou +
                ", pozitieBirou=" + pozitieBirou +
                '}';
    }
}
