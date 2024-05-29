package com.example.IP_Project.Controller;

import com.example.IP_Project.DTO.PrezentaUtilizatoriDTO;
import com.example.IP_Project.Service.PrezentaUtilizatoriService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/prezenta_utilizatori")
public class PrezentaUtilizatoriController {

    private final PrezentaUtilizatoriService prezentaUtilizatoriService;

    @Autowired
    public PrezentaUtilizatoriController(PrezentaUtilizatoriService prezentaUtilizatoriService) {
        this.prezentaUtilizatoriService = prezentaUtilizatoriService;
    }

    @DeleteMapping("/sterge/{id}")
    public ResponseEntity<Void> stergePrezenta(@PathVariable String id) {
        prezentaUtilizatoriService.stergePrezenta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idPrezenta}")
    public PrezentaUtilizatoriDTO obtinePrezentaById(@PathVariable String idPrezenta) {
        return prezentaUtilizatoriService.obtinePrezentaById(idPrezenta);
    }

    @GetMapping("/toate")
    public List<PrezentaUtilizatoriDTO> obtineToatePrezentele() {
        return prezentaUtilizatoriService.obtineToatePrezentele();
    }

    @GetMapping("/utilizator/{userId}")
    public ResponseEntity<List<PrezentaUtilizatoriDTO>> getPrezentaByUserId(@PathVariable Long userId) {
        List<PrezentaUtilizatoriDTO> prezentaList = prezentaUtilizatoriService.getPrezentaByUserId(userId);
        return ResponseEntity.ok(prezentaList);
    }
}
