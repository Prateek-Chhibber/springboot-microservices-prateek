package com.toxicprogrammer.employeeservice.repository;

import com.toxicprogrammer.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
