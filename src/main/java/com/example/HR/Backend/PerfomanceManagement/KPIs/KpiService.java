package com.example.HR.Backend.PerfomanceManagement.KPIs;
import com.example.HR.Backend.PerformanceManagement.KPIs.KpiRepository;


import com.example.HR.Backend.Utilities.EntityResponse; // Import statement corrected

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class KpiService {

    @Autowired
    private com.example.HR.Backend.PerformanceManagement.KPIs.KpiRepository kpiRepository;

    public EntityResponse<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi> createKPI(com.example.HR.Backend.PerformanceManagement.KPIs.Kpi kpi) { // Removed unnecessary package qualification
        EntityResponse<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi> entityResponse = new EntityResponse<>(); // Removed unnecessary package qualification

        try {
            com.example.HR.Backend.PerformanceManagement.KPIs.Kpi savedKpi = kpiRepository.save(kpi); // Removed unnecessary package qualification

            savedKpi.setPostedFlag('Y');
            savedKpi.setPostedBy("Current User");
            savedKpi.setPostedTime(LocalDateTime.now());
            entityResponse.setMessage("New KPI created successfully.");
            entityResponse.setStatusCode(HttpStatus.CREATED.value());
            entityResponse.setData(savedKpi);
        } catch (Exception e) {
            log.error("Failed to create KPI.", e);
            entityResponse.setMessage("An error occurred while creating the KPI.");
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            entityResponse.setData(null);
        }
        return entityResponse;
    }

    public EntityResponse<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi> getById(Long id) {
        EntityResponse<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi> entityResponse = new EntityResponse<>();

        try {
            Optional<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi> existingKpiOptional = kpiRepository.findById(id);
            if (existingKpiOptional.isPresent()) {
                com.example.HR.Backend.PerformanceManagement.KPIs.Kpi existingKpi = existingKpiOptional.get();

                entityResponse.setStatusCode(HttpStatus.FOUND.value());
                entityResponse.setMessage("KPI retrieved successfully.");
                entityResponse.setEntity(existingKpi);
            } else {
                entityResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                entityResponse.setMessage("KPI not found.");
            }
        } catch (Exception e) {
            log.error("Failed to retrieve the KPI", e);
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            entityResponse.setMessage("An error occurred while retrieving the KPI.");
        }
        return entityResponse;
    }

    public EntityResponse<String> deleteKPI(Long id) {
        EntityResponse<String> entityResponse = new EntityResponse<>();

        try {
            Optional<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi> kpiOptional = kpiRepository.findById(id); // Removed unnecessary package qualification
            if (kpiOptional.isPresent()) {
                kpiRepository.deleteById(id); // Removed unnecessary package qualification
                entityResponse.setStatusCode(HttpStatus.OK.value());
                entityResponse.setMessage("KPI deleted successfully.");
                entityResponse.setData("KPI ID: " + id);
            } else {
                entityResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                entityResponse.setMessage("KPI not found.");
                entityResponse.setData(null);
            }
        } catch (Exception e) {
            log.error("Failed to delete KPI", e);
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            entityResponse.setMessage("An error occurred while deleting the KPI.");
            entityResponse.setData(null);
        }
        return entityResponse;
    }

    public EntityResponse<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi> modifyKPI (com.example.HR.Backend.PerformanceManagement.KPIs.Kpi modifiedKPI) {
        EntityResponse<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi> entityResponse = new EntityResponse<>();

        Optional<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi> existingModifiedKpi = kpiRepository.findById(modifiedKPI.getId());
        try {
            if (existingModifiedKpi.isPresent()) {
                com.example.HR.Backend.PerformanceManagement.KPIs.Kpi existingKpi = existingModifiedKpi.get();

                existingKpi.setUnitOfMeasure(modifiedKPI.getUnitOfMeasure());
                existingKpi.setKpiName(modifiedKPI.getKpiName());
                existingKpi.setAggregation(modifiedKPI.getAggregation()); // corrected method call
                existingKpi.setDescription(modifiedKPI.getDescription());
                existingKpi.setCategory(modifiedKPI.getCategory()); // corrected method call
                existingKpi.setTargetType(modifiedKPI.getTargetType()); // corrected method call
                existingKpi.setTarget(modifiedKPI.getTarget()); // corrected method call


                com.example.HR.Backend.PerformanceManagement.KPIs.Kpi savedKpi = kpiRepository.save(existingKpi);
                savedKpi.setModifiedBy("Current User");
                savedKpi.setModifiedFlag('Y');
                savedKpi.setModifiedTime(LocalDateTime.now());

                entityResponse.setStatusCode(HttpStatus.OK.value());
                entityResponse.setMessage("KPI modified successfully.");
                entityResponse.setEntity(savedKpi);
            } else {
                entityResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                entityResponse.setMessage("KPI not found..");
                entityResponse.setEntity(null);
            }
        } catch (Exception e) {
            log.error("KPI modification failed.");
            entityResponse.setMessage("An error occurred while modifying the KPI.");
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            entityResponse.setEntity(null);
        }
        return entityResponse;
    }

    public EntityResponse<List<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi>> getAllKpis(){
        EntityResponse<List<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi>> entityResponse = new EntityResponse<>();

        List<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi> allKPIs = kpiRepository.findAllByDeletedFlag('N');

        try {
            if (allKPIs.isEmpty()){

                entityResponse.setMessage("No KPIs found.");
                entityResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                entityResponse.setEntity(null);
            }
            else {
                entityResponse.setMessage("KPIs retrieved successfully.");
                entityResponse.setStatusCode(HttpStatus.OK.value());
                entityResponse.setEntity(allKPIs);
            }
        }
        catch (Exception e){
            log.error("Retrieval of KPIs failed.", e);
            entityResponse.setMessage("An error occurred while retrieving the KPIs.");
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            entityResponse.setEntity(null);
        }
        return entityResponse;
}
}