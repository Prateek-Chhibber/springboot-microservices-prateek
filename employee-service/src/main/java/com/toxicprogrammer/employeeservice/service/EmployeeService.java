package com.toxicprogrammer.employeeservice.service;

import com.toxicprogrammer.employeeservice.dto.EmployeeDto;

import java.time.LocalDateTime;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);
}
