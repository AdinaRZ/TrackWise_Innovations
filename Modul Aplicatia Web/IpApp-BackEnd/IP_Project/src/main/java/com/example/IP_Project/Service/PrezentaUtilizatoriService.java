package com.example.IP_Project.Service;

import com.example.IP_Project.DTO.PrezentaUtilizatoriDTO;
import com.example.IP_Project.Entity.PrezentaUtilizatori;
import com.example.IP_Project.Service.IMPL.PrezentaUtilizatoriIMPL;

import java.time.LocalDate;
import java.util.List;

public interface PrezentaUtilizatoriService {
    void stergePrezenta(String idPrezenta);

    PrezentaUtilizatoriDTO obtinePrezentaById(String idPrezenta);

    List<PrezentaUtilizatoriDTO> obtineToatePrezentele();

    List<PrezentaUtilizatoriDTO> getPrezentaByUserId(Long userId);
}
