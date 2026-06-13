package com.pup.taguig.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pup.taguig.dto.SalesReportDTO;
import com.pup.taguig.dto.TopProductDTO;
import com.pup.taguig.repository.ReportMapper;
import com.pup.taguig.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public SalesReportDTO getSalesReport() {
        return reportMapper.getTotalSales();
    }

    @Override
    public List<TopProductDTO> getTopSellingProducts(int limit) {
        return reportMapper.getTopSellingProducts(limit);
    }
}
