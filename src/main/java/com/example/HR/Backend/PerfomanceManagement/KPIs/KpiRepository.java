package com.example.HR.Backend.PerformanceManagement.KPIs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KpiRepository extends JpaRepository<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi, Long> {
    // Additional methods if needed
    List<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi> findAllByDeletedFlag(char deletedFlag);
}
