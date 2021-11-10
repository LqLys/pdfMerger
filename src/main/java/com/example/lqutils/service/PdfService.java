package com.example.lqutils.service;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
public class PdfService {

    public byte[] mergeFiles(List<MultipartFile> files) throws IOException {
        PDFMergerUtility obj = new PDFMergerUtility();
        obj.setDestinationStream(new ByteArrayOutputStream());
        for (MultipartFile file : files) {
            obj.addSource(file.getInputStream());
        }
        obj.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());

        return ((ByteArrayOutputStream) obj.getDestinationStream()).toByteArray();
    }


}
