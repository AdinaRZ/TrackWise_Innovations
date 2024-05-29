package com.example.IP_Project.DTO;

import com.example.IP_Project.Entity.PrezentaUtilizatori;
import com.example.IP_Project.Entity.Utilizatori;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class UtilizatoriDTO {
    private Long id;
    private Long cnp;
    private String nume;
    private String prenume;
    private String nrInmatriculare;
    private String departament;
    private int etaj;
    private int birou;
    private int pozitieBirou;
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    private String role = "client";
    private List<PrezentaUtilizatoriDTO> prezentaUtilizatori;

    public UtilizatoriDTO() {
    }
    public UtilizatoriDTO(Long id, Long cnp, String nume, String prenume, String nrInmatriculare, String departament, int etaj, int birou, int pozitieBirou, String email, String password, String role, List<PrezentaUtilizatoriDTO> prezentaUtilizatori) {
        this.id = id;
        this.cnp = cnp;
        this.nume = nume;
        this.prenume = prenume;
        this.nrInmatriculare = nrInmatriculare;
        this.departament = departament;
        this.etaj = etaj;
        this.birou = birou;
        this.pozitieBirou = pozitieBirou;
        this.email = email;
        this.password = password;
        this.role = role;
        this.prezentaUtilizatori = prezentaUtilizatori;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCnp() {
        return cnp;
    }

    public void setCnp(Long cnp) {
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNrInmatriculare() {
        return nrInmatriculare;
    }

    public void setNrInmatriculare(String nrInmatriculare) {
        this.nrInmatriculare = nrInmatriculare;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<PrezentaUtilizatoriDTO> getPrezentaUtilizatori() {
        return prezentaUtilizatori;
    }

    public void setPrezentaUtilizatori(List<PrezentaUtilizatoriDTO> prezentaUtilizatori) {
        this.prezentaUtilizatori = prezentaUtilizatori;
    }

    @Override
    public String toString() {
        return "Utilizatori{" +
                "id=" + id +
                ", cnp=" + cnp +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", nrInmatriculare='" + nrInmatriculare + '\'' +
                ", departament='" + departament + '\'' +
                ", etaj=" + etaj +
                ", birou=" + birou +
                ", pozitieBirou=" + pozitieBirou +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", prezentaUtilizator=" + prezentaUtilizatori +
                '}';
    }
}
