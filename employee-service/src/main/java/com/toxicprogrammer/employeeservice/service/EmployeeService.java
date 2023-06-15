package com.toxicprogrammer.employeeservice.service;

import com.toxicprogrammer.employeeservice.dto.APIResponseDto;
import com.toxicprogrammer.employeeservice.dto.EmployeeDto;

import java.time.LocalDateTime;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
