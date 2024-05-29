package com.example.IP_Project.Service.IMPL;

import com.example.IP_Project.DTO.PrezentaUtilizatoriDTO;
import com.example.IP_Project.Entity.PrezentaUtilizatori;
import com.example.IP_Project.Repo.PrezentaUtilizatoriRepo;
import com.example.IP_Project.Repo.UtilizatoriRepo;
import com.example.IP_Project.Service.PrezentaUtilizatoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrezentaUtilizatoriIMPL implements PrezentaUtilizatoriService {

    @Autowired
    private PrezentaUtilizatoriRepo prezentaUtilizatoriRepo;
    @Autowired
    private UtilizatoriRepo utilizatoriRepo;

    @Override
    public void stergePrezenta(String idPrezenta) {
        prezentaUtilizatoriRepo.deleteById(idPrezenta);
    }

    @Override
    public PrezentaUtilizatoriDTO obtinePrezentaById(String idPrezenta) {
        Optional<PrezentaUtilizatori> prezentaOptional = prezentaUtilizatoriRepo.findById(idPrezenta);
        if (prezentaOptional.isPresent()) {
            PrezentaUtilizatori prezentaUtilizatori = prezentaOptional.get();
            return new PrezentaUtilizatoriDTO(
                    prezentaUtilizatori.getIdPrezenta(),
                    prezentaUtilizatori.getUtilizator().getId(),
                    prezentaUtilizatori.getDataIntrare(),
                    prezentaUtilizatori.getOraIntrare(),
                    prezentaUtilizatori.getDataIesire(),
                    prezentaUtilizatori.getOraIesire()

            );
        }
        return null;
    }

    @Override
    public List<PrezentaUtilizatoriDTO> obtineToatePrezentele() {
        return prezentaUtilizatoriRepo.findAll().stream()
                .map(prezenta -> new PrezentaUtilizatoriDTO(
                        prezenta.getIdPrezenta(),
                        prezenta.getUtilizator().getId(),
                        prezenta.getDataIntrare(),
                        prezenta.getOraIntrare(),
                        prezenta.getDataIesire(),
                        prezenta.getOraIesire()))

                .collect(Collectors.toList());
    }


       @Override
        public List<PrezentaUtilizatoriDTO> getPrezentaByUserId(Long userId) {
            List<PrezentaUtilizatori> prezente = prezentaUtilizatoriRepo.findByUtilizatorId(userId);
           return prezente.stream()
                   .map(prezenta -> new PrezentaUtilizatoriDTO(
                           prezenta.getIdPrezenta(),
                           prezenta.getUtilizator().getId(),
                           prezenta.getDataIntrare(),
                           prezenta.getOraIntrare(),
                           prezenta.getDataIesire(),
                           prezenta.getOraIesire()


                   ))
                   .collect(Collectors.toList());
        }


}
