package com.example.IP_Project.Service;

import com.example.IP_Project.DTO.LoginDTO;
import com.example.IP_Project.DTO.UtilizatoriDTO;
import com.example.IP_Project.Response.LoginResponse;
import java.util.List;

public interface UtilizatoriService {

    String addUtilizator(UtilizatoriDTO utilizatoriDTO);
    LoginResponse loginUtilizator(LoginDTO loginDTO);
    void updateUtilizator(UtilizatoriDTO utilizatoriDTO);
    UtilizatoriDTO getUtilizatorById(Long id);
    void deleteUtilizator(Long id);
    List<UtilizatoriDTO> getAllUtilizatori();
}
