package com.example.IP_Project.Service;

import com.example.IP_Project.DTO.CladireDTO;
import com.example.IP_Project.DTO.UtilizatoriDTO;
import com.example.IP_Project.Entity.Cladire;

import java.util.List;

public interface CladireService {
    Cladire setCladire(CladireDTO cladireDTO);

    UtilizatoriDTO allocatePosition(Long userId, int birou, int pozitieBirou);

    List<CladireDTO> getAllCladire();
}
