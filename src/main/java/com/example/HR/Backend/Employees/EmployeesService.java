package com.example.HR.Backend.Employees;


import com.example.HR.Backend.Utilities.EntityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeesService {

    @Autowired
    EmployeesRepository employeesRepository;
    //private int List;
    public EntityResponse create(Employees employees) {
        EntityResponse entityResponse = new EntityResponse();
        {
            try {

                employees.setPostedBy("SYSTEM");
                employees.setPostedTime(LocalDateTime.now());
                employees.setPostedFlag('Y');
                Employees savedEmployees =employeesRepository.save(employees);
                entityResponse.setMessage("Employee Created Successfully");
                entityResponse.setStatusCode(HttpStatus.CREATED.value());
                entityResponse.setEntity(savedEmployees);


            } catch (Exception e) {
                log.error("An error occurred while creating a Employee", e);
                entityResponse.setMessage("An error occurred while creating a Employee");
                entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                entityResponse.setEntity(null);
            }
        }
        return entityResponse;
    }
    public EntityResponse delete(Long id) {
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            Optional<Employees> existingEmployeesOptional = employeesRepository.findById(id)
                    ;

            if (existingEmployeesOptional.isPresent()) {
                Employees existingEmployees = existingEmployeesOptional.get();

                // Set flags
                existingEmployees.setDeletedFlag('Y');
                existingEmployees.setDeletedBy("User");
                existingEmployees.setDeletedOn(LocalDateTime.now());

                // Save changes with flags
                employeesRepository.save(existingEmployees);

                entityResponse.setMessage("Employee deleted successfully");
                entityResponse.setStatusCode(HttpStatus.OK.value());
                entityResponse.setEntity(existingEmployees);
            } else {
                entityResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
                entityResponse.setMessage("Failed to delete Employee. Committee not found.");
                entityResponse.setEntity(null);
            }
        } catch (Exception e) {
            log.error("An error occurred while deleting Employee: " + e.getMessage());
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            entityResponse.setMessage("An error occurred while deleting Employee");
            entityResponse.setEntity(null);
        }
        return entityResponse;
    }

    public EntityResponse<Employees> update(Employees updatedEmployees) {
        EntityResponse<Employees> entityResponse = new EntityResponse<>();
        try {
            Optional<Employees> existingEmployeesOptional = employeesRepository.findById(updatedEmployees.getId());

            Employees existingEmployees = existingEmployeesOptional.orElse(null);

            if (existingEmployees != null) {
                if (updatedEmployees.getId() != null) {
                    existingEmployees.setFirstName(updatedEmployees.getFirstName());
                    existingEmployees.setEmailAddress(updatedEmployees.getEmailAddress());
                    existingEmployees.setLastName(updatedEmployees.getLastName());
                    existingEmployees.setDepartment(updatedEmployees.getDepartment());
                    existingEmployees.setJobTitle(updatedEmployees.getJobTitle());

                    existingEmployees.setModifiedBy("Some User");
                    existingEmployees.setModifiedFlag('Y');
                    existingEmployees.setModifiedOn(LocalDateTime.now());

                }


                employeesRepository.save(existingEmployees);

                entityResponse.setMessage("Employee updated successfully");
                entityResponse.setStatusCode(HttpStatus.OK.value());
                entityResponse.setEntity(existingEmployees);
            } else {
                entityResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                entityResponse.setMessage("Employee not found");
            }
        } catch (Exception e) {
            log.error("An error occurred while updating the employee: " + e.getMessage(), e);
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            entityResponse.setMessage("An error occurred while updating the employee");
        }
        return entityResponse;}

    public EntityResponse<Employees> findById(Long id) {
        EntityResponse<Employees> entityResponse = new EntityResponse<>();

        try {
            Optional<Employees> existingEmployeesOptional = employeesRepository.findById(id)
                    ;
            if (existingEmployeesOptional.isPresent()) {
                Employees existingEmployees = existingEmployeesOptional.get();
                entityResponse.setMessage("Employee found successfully");
                existingEmployees.setVerifiedBy("SYSTEM");
                existingEmployees.setVerifiedTime(LocalDateTime.now());
                existingEmployees.setVerifiedFlag('Y');
                employeesRepository.save(existingEmployees);
                entityResponse.setStatusCode(HttpStatus.OK.value());
                entityResponse.setEntity(existingEmployees);
            } else {
                entityResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                entityResponse.setMessage("Employee not found");
                entityResponse.setEntity(null);
            }
        } catch (Exception e) {
            log.error("An error occurred while finding employee: " + e.getMessage());
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            entityResponse.setMessage("An error occurred while finding an employee");
            entityResponse.setEntity(null);
        }
        return entityResponse;
    }

    public EntityResponse<List<Employees>> fetchAll() {
        EntityResponse<List<Employees>> entityResponse = new EntityResponse<>();
        try {
            List<Employees> existingEmployees = employeesRepository.findAllByDeletedFlag('N');
            if (!existingEmployees.isEmpty()) {
                entityResponse.setEntity(existingEmployees);
                entityResponse.setMessage("Employee(s) retrieved successfully");
                entityResponse.setStatusCode(HttpStatus.OK.value());
            } else {
                entityResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
                entityResponse.setMessage("No employee available");
            }
        } catch (Exception e) {
            log.error("An unexpected error occurred while fetching employee(s)", e);
            entityResponse.setMessage("An unexpected error occurred while fetching employee(s)");
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return entityResponse;
    }
}
