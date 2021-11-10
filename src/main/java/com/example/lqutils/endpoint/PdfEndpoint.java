package com.example.lqutils.endpoint;

import com.example.lqutils.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/pdf")
public class PdfEndpoint {


    private final PdfService pdfService;

    public PdfEndpoint(PdfService pdfService) {
        this.pdfService = pdfService;
    }


    @PostMapping(value = "/mergeFiles", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<byte[]> mergeFiles(
            @RequestPart("files") List<MultipartFile> files,
            @RequestParam(name = "mergedFileName", defaultValue = "mergedFileName") String mergedFileName) throws IOException {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + mergedFileName + "\"")
                .body(pdfService.mergeFiles(files));
    }



}
