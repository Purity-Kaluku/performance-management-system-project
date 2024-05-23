package com.example.HR.Backend.PerformanceManagement.KPIs;

import com.example.HR.Backend.PerfomanceManagement.KPIs.KpiService;
import com.example.HR.Backend.Utilities.EntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/KPIs")
public class KpiController {

    @Autowired
    KpiService kpiService;

    @PostMapping("/create")
    public ResponseEntity<EntityResponse<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi>> createKPI(@RequestBody com.example.HR.Backend.PerformanceManagement.KPIs.Kpi kpi) {
        EntityResponse<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi> response = kpiService.createKPI(kpi);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityResponse<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi>> getById(@PathVariable Long id) {
        EntityResponse<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi> response = kpiService.getById(id);

        if (response.getStatusCode() == HttpStatus.FOUND.value()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<EntityResponse<String>> deleteKPI(@RequestParam Long id) {
        EntityResponse<String> response = kpiService.deleteKPI(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/modify/KPI")
    public EntityResponse<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi> modifyKPI(@RequestBody com.example.HR.Backend.PerformanceManagement.KPIs.Kpi modifiedKPI){
        return kpiService.modifyKPI(modifiedKPI);
    }

    @GetMapping("/get/allKPIs")
    public EntityResponse<List<com.example.HR.Backend.PerformanceManagement.KPIs.Kpi>> getAllKpis (){
        return kpiService.getAllKpis();
    }
}
