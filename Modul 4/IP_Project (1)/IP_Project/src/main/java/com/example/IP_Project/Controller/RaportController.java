package com.example.IP_Project.Controller;

import com.example.IP_Project.Service.RaportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/raport")
public class RaportController {

    @Autowired
    private RaportService reportService;

    @GetMapping("/generare/{cnp}")
    public ResponseEntity<byte[]> generateReport(@PathVariable Long cnp) {
        try {
            ByteArrayInputStream bis = reportService.generateReport(cnp);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=report.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(bis.readAllBytes());
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}

