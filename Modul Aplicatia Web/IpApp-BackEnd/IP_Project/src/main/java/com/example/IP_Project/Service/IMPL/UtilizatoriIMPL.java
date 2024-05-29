package com.example.IP_Project.Service.IMPL;

import com.example.IP_Project.DTO.LoginDTO;
import com.example.IP_Project.DTO.UtilizatoriDTO;
import com.example.IP_Project.Entity.PrezentaUtilizatori;
import com.example.IP_Project.Entity.Utilizatori;
import com.example.IP_Project.Repo.UtilizatoriRepo;
import com.example.IP_Project.Response.LoginResponse;
import com.example.IP_Project.Service.UtilizatoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UtilizatoriIMPL implements UtilizatoriService {

    @Autowired
    private UtilizatoriRepo utilizatoriRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUtilizator(UtilizatoriDTO utilizatoriDTO) {
        Utilizatori utilizatori = new Utilizatori(
                utilizatoriDTO.getId(),
                utilizatoriDTO.getCnp(),
                utilizatoriDTO.getNume(),
                utilizatoriDTO.getPrenume(),
                utilizatoriDTO.getNrInmatriculare(),
                utilizatoriDTO.getDepartament(),
                utilizatoriDTO.getEtaj(),
                utilizatoriDTO.getBirou(),
                utilizatoriDTO.getPozitieBirou(),
                utilizatoriDTO.getEmail(),
                this.passwordEncoder.encode(utilizatoriDTO.getPassword()),
                utilizatoriDTO.getRole(),
                null // Inițializăm cu null deoarece nu avem prezențe la adăugarea utilizatorului
        );
        utilizatoriRepo.save(utilizatori);
        return utilizatori.getEmail();
    }

    @Override
    public LoginResponse loginUtilizator(LoginDTO loginDTO) {
        Utilizatori utilizatori = utilizatoriRepo.findByEmail(loginDTO.getEmail());
        if (utilizatori != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = utilizatori.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                return new LoginResponse("Login Success", true);
            } else {
                return new LoginResponse("Password Not Match", false);
            }
        } else {
            return new LoginResponse("Email not exists", false);
        }
    }

    @Override
    public void updateUtilizator(UtilizatoriDTO utilizatoriDTO) {
        Optional<Utilizatori> utilizatorOptional = utilizatoriRepo.findById(utilizatoriDTO.getId());
        if (utilizatorOptional.isPresent()) {
            Utilizatori utilizatori = utilizatorOptional.get();
            utilizatori.setCnp(utilizatoriDTO.getCnp());
            utilizatori.setNume(utilizatoriDTO.getNume());
            utilizatori.setPrenume(utilizatoriDTO.getPrenume());
            utilizatori.setNrInmatriculare(utilizatoriDTO.getNrInmatriculare());
            utilizatori.setDepartament(utilizatoriDTO.getDepartament());
            utilizatori.setEtaj(utilizatoriDTO.getEtaj());
            utilizatori.setBirou(utilizatoriDTO.getBirou());
            utilizatori.setPozitieBirou(utilizatoriDTO.getPozitieBirou());
            utilizatori.setEmail(utilizatoriDTO.getEmail());
            if (utilizatoriDTO.getPassword() != null && !utilizatoriDTO.getPassword().isEmpty()) {
                utilizatori.setPassword(this.passwordEncoder.encode(utilizatoriDTO.getPassword()));
            }
            utilizatori.setRole(utilizatoriDTO.getRole());
            utilizatoriRepo.save(utilizatori);
        }
    }

    @Override
    public UtilizatoriDTO getUtilizatorById(Long id) {
        Optional<Utilizatori> utilizatorOptional = utilizatoriRepo.findById(id);
        if (utilizatorOptional.isPresent()) {
            Utilizatori utilizatori = utilizatorOptional.get();
            return new UtilizatoriDTO(
                    utilizatori.getId(),
                    utilizatori.getCnp(),
                    utilizatori.getNume(),
                    utilizatori.getPrenume(),
                    utilizatori.getNrInmatriculare(),
                    utilizatori.getDepartament(),
                    utilizatori.getEtaj(),
                    utilizatori.getBirou(),
                    utilizatori.getPozitieBirou(),
                    utilizatori.getEmail(),
                    utilizatori.getPassword(),
                    utilizatori.getRole(),
                    null // Inițializăm cu null deoarece nu populăm prezențele la obținerea utilizatorului
            );
        }
        return null;
    }

    @Override
    public void deleteUtilizator(Long id) {
        utilizatoriRepo.deleteById(id);
    }

    @Override
    public List<UtilizatoriDTO> getAllUtilizatori() {
        List<Utilizatori> utilizatoriList = utilizatoriRepo.findAll();
        return utilizatoriList.stream()
                .map(utilizatori -> new UtilizatoriDTO(
                        utilizatori.getId(),
                        utilizatori.getCnp(),
                        utilizatori.getNume(),
                        utilizatori.getPrenume(),
                        utilizatori.getNrInmatriculare(),
                        utilizatori.getDepartament(),
                        utilizatori.getEtaj(),
                        utilizatori.getBirou(),
                        utilizatori.getPozitieBirou(),
                        utilizatori.getEmail(),
                        utilizatori.getPassword(),
                        utilizatori.getRole(),
                        null // Inițializăm cu null deoarece nu populăm prezențele în lista de DTO-uri
                ))
                .collect(Collectors.toList());
    }
}
