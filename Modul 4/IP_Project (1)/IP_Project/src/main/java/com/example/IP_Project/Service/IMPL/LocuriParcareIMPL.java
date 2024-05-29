package com.example.IP_Project.Service.IMPL;

import com.example.IP_Project.DTO.LocuriParcareDTO;
import com.example.IP_Project.Entity.LocuriParcare;
import com.example.IP_Project.Repo.LocuriParcareRepo;
import com.example.IP_Project.Service.LocuriParcareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocuriParcareIMPL implements LocuriParcareService {

    @Autowired
    private LocuriParcareRepo locuriParcareRepository;

    @Override
    public LocuriParcare setNumarLocuri(LocuriParcareDTO locuriParcareDTO) {
        LocuriParcare locuriParcare = locuriParcareRepository.findFirstByOrderByIdLocParcareAsc()
                .orElse(new LocuriParcare());
        locuriParcare.setNumarLocuri(locuriParcareDTO.getNumarLocuri());
        return locuriParcareRepository.save(locuriParcare);
    }
}
