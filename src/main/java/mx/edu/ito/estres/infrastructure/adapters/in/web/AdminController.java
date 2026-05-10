package mx.edu.ito.estres.infrastructure.adapters.in.web;

import mx.edu.ito.estres.application.service.ExportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ExportService exportService;

    public AdminController(
            ExportService exportService
    ) {
        this.exportService = exportService;
    }

    @GetMapping("/export/csv")
    public ResponseEntity<String> exportCsv() {

        String csv =
                exportService.generateCsv();

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=stress-data.csv"
                )
                .contentType(MediaType.TEXT_PLAIN)
                .body(csv);
    }
}
