package com.example.IP_Project.Controller;

import com.example.IP_Project.DTO.CladireDTO;
import com.example.IP_Project.DTO.UtilizatoriDTO;
import com.example.IP_Project.Entity.Cladire;
import com.example.IP_Project.Service.CladireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cladire")
@CrossOrigin(origins = "http://localhost:3000")
public class CladireController {

    @Autowired
    private CladireService cladireService;

    @PostMapping("/set")
    public ResponseEntity<Cladire> setCladire(@RequestBody CladireDTO cladireDTO) {
        Cladire cladire = cladireService.setCladire(cladireDTO);
        return ResponseEntity.ok(cladire);
    }

    @GetMapping("/info")
    public ResponseEntity<List<CladireDTO>> getAllCladire() {
        List<CladireDTO> cladiri = cladireService.getAllCladire();
        return ResponseEntity.ok(cladiri);
    }

    @PostMapping("/allocate")
    public ResponseEntity<UtilizatoriDTO> allocatePosition(@RequestParam Long userId, @RequestParam int birou, @RequestParam int pozitieBirou) {
        UtilizatoriDTO allocated = cladireService.allocatePosition(userId, birou, pozitieBirou);
        return ResponseEntity.ok(allocated);
    }

}
