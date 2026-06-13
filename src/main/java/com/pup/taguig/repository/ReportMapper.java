package com.pup.taguig.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.pup.taguig.dto.SalesReportDTO;
import com.pup.taguig.dto.TopProductDTO;

@Mapper
public interface ReportMapper {

    SalesReportDTO getTotalSales();

    List<TopProductDTO> getTopSellingProducts(@Param("limit") int limit);
}
