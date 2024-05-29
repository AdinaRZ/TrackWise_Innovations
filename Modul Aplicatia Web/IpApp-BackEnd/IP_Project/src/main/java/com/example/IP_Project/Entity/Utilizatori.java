package com.example.IP_Project.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@Entity
@Table(name = "utilizatori")
public class Utilizatori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 245)
    private Long id;

    @Column(name = "cnp", length = 245)
    private Long cnp;

    @Column(name = "nume", length = 255)
    private String nume;

    @Column(name = "prenume", length = 255)
    private String prenume;

    @Column(name = "nr_inmatriculare", length = 255)
    private String nrInmatriculare;

    @Column(name = "departament", length = 255)
    private String departament;

    @Column(name = "etaj", length = 255)
    private int etaj;

    @Column(name = "birou", length = 255)
    private int birou;

    @Column(name = "pozitie_birou", length = 255)
    private int pozitieBirou;

    @Column(name = "email", length = 255)
    @Email(message = "Invalid email address")
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "role", length = 255)
    private String role = "client";

    @OneToMany(mappedBy = "utilizator", cascade = CascadeType.ALL)
    private List<PrezentaUtilizatori> prezentaUtilizatori;


    public Utilizatori() {
    }
    public Utilizatori(Long id, Long cnp, String nume, String prenume, String nrInmatriculare, String departament, int etaj, int birou, int pozitieBirou, String email, String password, String role, List<PrezentaUtilizatori> prezentaUtilizatori) {
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

    public List<PrezentaUtilizatori> getPrezentaUtilizatori() {
        return prezentaUtilizatori;
    }

    public void setPrezentaUtilizatori(List<PrezentaUtilizatori> prezentaUtilizatori) {
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

