package com.example.HR.Backend.Employees;

import com.example.HR.Backend.Utilities.EntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.bouncycastle.asn1.x509.X509ObjectIdentifiers.id;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeesController {

    @Autowired
    EmployeesService employeesService;

    @PostMapping("/add")
    public EntityResponse createEmployees(@RequestBody Employees employees) {
        return employeesService.create(employees);
    }

    @DeleteMapping("/delete")
    public EntityResponse deleteEmployees(@RequestParam Long id) {
        return employeesService.delete(id);
    }

    @PutMapping("/update")
    public EntityResponse updateEmployees(@RequestBody Employees employees) {
        return employeesService.update(employees);
    }

    @GetMapping("/findById")
    public EntityResponse findById(@RequestParam Long id) {
        return employeesService.findById(id);
    }

    @GetMapping("/getAll")
    public EntityResponse fetchAll() {
        return employeesService.fetchAll();
    }
}

