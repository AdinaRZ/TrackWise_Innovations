package com.example.IP_Project.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface RaportService {
    ByteArrayInputStream generateReport(Long utilizatorId) throws IOException;
}
