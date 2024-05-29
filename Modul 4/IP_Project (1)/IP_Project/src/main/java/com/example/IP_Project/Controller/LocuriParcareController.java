package com.example.IP_Project.Controller;

import com.example.IP_Project.DTO.LocuriParcareDTO;
import com.example.IP_Project.Entity.LocuriParcare;

import com.example.IP_Project.Service.LocuriParcareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/locuri_parcare")
@CrossOrigin(origins = "http://localhost:3000")
public class LocuriParcareController {

    @Autowired
    private LocuriParcareService locuriParcareService;

    @PostMapping("/set")
    public ResponseEntity<LocuriParcare> setLocuriParcare(@RequestBody LocuriParcareDTO locuriParcareDTO) {
        LocuriParcare locuriParcare = locuriParcareService.setNumarLocuri(locuriParcareDTO);
        return ResponseEntity.ok(locuriParcare);
    }
}
