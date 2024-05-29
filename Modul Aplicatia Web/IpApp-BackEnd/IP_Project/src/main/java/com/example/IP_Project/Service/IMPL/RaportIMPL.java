package com.example.IP_Project.Service.IMPL;

import com.example.IP_Project.Entity.Parcare;
import com.example.IP_Project.Entity.PrezentaUtilizatori;
import com.example.IP_Project.Entity.Utilizatori;
import com.example.IP_Project.Repo.PrezentaUtilizatoriRepo;
import com.example.IP_Project.Repo.ParcareRepo;
import com.example.IP_Project.Repo.UtilizatoriRepo;
import com.example.IP_Project.Service.RaportService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class RaportIMPL implements RaportService {

    @Autowired
    private PrezentaUtilizatoriRepo prezentaUtilizatoriRepo;

    @Autowired
    private ParcareRepo parcareRepo;

    @Autowired
    private UtilizatoriRepo utilizatoriRepo;

    @Override
    public ByteArrayInputStream generateReport(Long utilizatorId) throws IOException {
        List<PrezentaUtilizatori> prezente = prezentaUtilizatoriRepo.findByUtilizatorId(utilizatorId);
        Optional<Utilizatori> optionalUtilizator = utilizatoriRepo.findById(utilizatorId);

        if (!optionalUtilizator.isPresent()) {
            throw new RuntimeException("Utilizatorul cu ID-ul " + utilizatorId + " nu a fost găsit.");
        }

        Utilizatori utilizator = optionalUtilizator.get();
        String nrInmatriculare = utilizator.getNrInmatriculare();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(new PDRectangle(600, 400));
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            float yPosition = 380;
            contentStream.newLineAtOffset(50, yPosition);
            contentStream.showText("Raport Saptamanal pentru Angajatul cu ID-ul: " + utilizatorId);

            // Displaying table header
            contentStream.newLineAtOffset(0, -30); // Move to the next line
            contentStream.showText("Data              Ore Lucrate      Venit cu masina");

            // Displaying working hours for each day
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");



            for (PrezentaUtilizatori prezenta : prezente) {
                if (prezenta.getOraIntrare() != null && prezenta.getOraIesire() != null) {
                    LocalTime oraIntrare = LocalTime.parse(prezenta.getOraIntrare(), timeFormatter);
                    LocalTime oraIesire = LocalTime.parse(prezenta.getOraIesire(), timeFormatter);
                    long oreLucrate = Duration.between(oraIntrare, oraIesire).toHours();

                    // Cautăm înregistrările din tabela Parcare pentru numărul de înmatriculare și data de intrare
                    List<Parcare> inregistrariParcare = parcareRepo.findByNumarInmatriculareAndDataIntrare(nrInmatriculare, prezenta.getDataIntrare());

                    // Verificăm dacă există înregistrări pentru numărul de înmatriculare respectiv în ziua specificată
                    boolean aVenitCuMasina = !inregistrariParcare.isEmpty();

                    String line = String.format("%-20s%-25s%-20s",
                            prezenta.getDataIntrare().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            oreLucrate + " ore",
                            aVenitCuMasina ? "Nu" : "Da");

                    contentStream.newLineAtOffset(0, -15);
                    contentStream.showText(line);
                } else {
                    // Handle null values for oraIntrare or oraIesire
                    // For example, log a message or skip this entry
                }
            }


            contentStream.endText();
            contentStream.close();

            document.save(out);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
