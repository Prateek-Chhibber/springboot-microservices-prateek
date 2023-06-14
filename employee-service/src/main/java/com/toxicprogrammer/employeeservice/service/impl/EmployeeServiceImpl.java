package com.toxicprogrammer.employeeservice.service.impl;

import com.toxicprogrammer.employeeservice.dto.EmployeeDto;
import com.toxicprogrammer.employeeservice.entity.Employee;
import com.toxicprogrammer.employeeservice.exception.ResourceNotFoundException;
import com.toxicprogrammer.employeeservice.mapper.EmployeeMapper;
import com.toxicprogrammer.employeeservice.repository.EmployeeRepository;
import com.toxicprogrammer.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private ModelMapper modelMapper;

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

//      Convert employee dto to employee JPA entity
        Employee employee = EmployeeMapper.MAPPER.mapToEmployee(employeeDto);

//      Store employee JPA entity object into database by using save()
        Employee savedEmployee = employeeRepository.save(employee);

//       Convert employee JPA entity to employee dto
        EmployeeDto savedEmployeeDto = EmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);

        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee","id",employeeId)
        );

//      Convert employee JPA entity to employee dto
        EmployeeDto employeeDto = EmployeeMapper.MAPPER.mapToEmployeeDto(employee);
        return employeeDto;
    }
}
