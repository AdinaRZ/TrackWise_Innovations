package com.example.IP_Project.Controller;

import com.example.IP_Project.DTO.LoginDTO;
import com.example.IP_Project.DTO.UtilizatoriDTO;
import com.example.IP_Project.Response.LoginResponse;
import com.example.IP_Project.Service.UtilizatoriService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/utilizatori")
public class UtilizatoriController {

    private final UtilizatoriService utilizatoriService;

    public UtilizatoriController(UtilizatoriService utilizatoriService) {
        this.utilizatoriService = utilizatoriService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUtilizator(@RequestBody UtilizatoriDTO utilizatoriDTO) {
        String responseMessage = utilizatoriService.addUtilizator(utilizatoriDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = utilizatoriService.loginUtilizator(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUtilizator(@RequestBody UtilizatoriDTO utilizatoriDTO) {
        utilizatoriService.updateUtilizator(utilizatoriDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilizatoriDTO> getUtilizatorById(@PathVariable Long id) {
        UtilizatoriDTO utilizatoriDTO = utilizatoriService.getUtilizatorById(id);
        return ResponseEntity.ok(utilizatoriDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUtilizator(@PathVariable Long id) {
        utilizatoriService.deleteUtilizator(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<UtilizatoriDTO>> getAllUtilizatori() {
        List<UtilizatoriDTO> utilizatoriDTOList = utilizatoriService.getAllUtilizatori();
        return ResponseEntity.ok(utilizatoriDTOList);
    }
}