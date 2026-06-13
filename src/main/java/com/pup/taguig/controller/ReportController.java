package com.pup.taguig.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pup.taguig.dto.SalesReportDTO;
import com.pup.taguig.dto.TopProductDTO;
import com.pup.taguig.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/sales")
    public ResponseEntity<?> getSalesReport() {
        SalesReportDTO report = reportService.getSalesReport();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/top-products")
    public ResponseEntity<?> getTopSellingProducts(
            @RequestParam(value = "limit", defaultValue = "10") int limit) {
        List<TopProductDTO> topProducts = reportService.getTopSellingProducts(limit);
        return ResponseEntity.ok(topProducts);
    }
}
