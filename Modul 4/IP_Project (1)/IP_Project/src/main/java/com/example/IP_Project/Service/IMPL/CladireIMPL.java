package com.example.IP_Project.Service.IMPL;

import com.example.IP_Project.DTO.CladireDTO;
import com.example.IP_Project.DTO.UtilizatoriDTO;
import com.example.IP_Project.Entity.Cladire;
import com.example.IP_Project.Entity.Utilizatori;
import com.example.IP_Project.Repo.CladireRepo;
import com.example.IP_Project.Repo.UtilizatoriRepo;
import com.example.IP_Project.Service.CladireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CladireIMPL implements CladireService {

    @Autowired
    private CladireRepo cladireRepo;

    @Autowired
    private UtilizatoriRepo utilizatoriRepo;

    @Override
    public Cladire setCladire(CladireDTO cladireDTO) {
        Cladire cladire = cladireRepo.findFirstByOrderByIdCladireAsc()
                .orElse(new Cladire());
        cladire.setEtaj(cladireDTO.getEtaj());
        cladire.setBirou(cladireDTO.getBirou());
        cladire.setPozitieBirou(cladireDTO.getPozitieBirou());
        return cladireRepo.save(cladire);
    }

    @Override
    public UtilizatoriDTO allocatePosition(Long userId, int birou, int pozitieBirou) {

        Utilizatori utilizator = utilizatoriRepo.findById(userId).orElse(null);
        if (utilizator == null) {

            return null;
        }

        utilizator.setBirou(birou);
        utilizator.setPozitieBirou(pozitieBirou);
        Utilizatori savedUser = utilizatoriRepo.save(utilizator);

        return new UtilizatoriDTO(
                savedUser.getId(),
                savedUser.getCnp(),
                savedUser.getNume(),
                savedUser.getPrenume(),
                savedUser.getNrInmatriculare(),
                savedUser.getDepartament(),
                savedUser.getEtaj(),
                savedUser.getBirou(),
                savedUser.getPozitieBirou(),
                savedUser.getEmail(),
                savedUser.getPassword(),
                savedUser.getRole(),
                null
        );
    }

    @Override
    public List<CladireDTO> getAllCladire() {
        List<Cladire> cladiri = cladireRepo.findAll();
        List<CladireDTO> cladireDTOList = new ArrayList<>();

        for (Cladire cladire : cladiri) {
            CladireDTO cladireDTO = new CladireDTO();
            cladireDTO.setIdCladire(cladire.getIdCladire());
            cladireDTO.setEtaj(cladire.getEtaj());
            cladireDTO.setBirou(cladire.getBirou());
            cladireDTO.setPozitieBirou(cladire.getPozitieBirou());
            cladireDTOList.add(cladireDTO);
        }

        return cladireDTOList;
}


}
