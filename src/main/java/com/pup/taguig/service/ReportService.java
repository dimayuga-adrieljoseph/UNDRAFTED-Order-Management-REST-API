package com.pup.taguig.service;

import java.util.List;

import com.pup.taguig.dto.SalesReportDTO;
import com.pup.taguig.dto.TopProductDTO;

public interface ReportService {

    SalesReportDTO getSalesReport();

    List<TopProductDTO> getTopSellingProducts(int limit);
}
