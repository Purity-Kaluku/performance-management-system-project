package com.example.HR.Backend.Employees;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employees , Long> {
    List<Employees> findAllByDeletedFlag(char n);
}
