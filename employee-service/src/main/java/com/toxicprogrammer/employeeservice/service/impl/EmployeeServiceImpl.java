package com.toxicprogrammer.employeeservice.service.impl;

import com.toxicprogrammer.employeeservice.dto.EmployeeDto;
import com.toxicprogrammer.employeeservice.entity.Employee;
import com.toxicprogrammer.employeeservice.repository.EmployeeRepository;
import com.toxicprogrammer.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

//      Convert employee dto to employee JPA entity
        Employee employee = new Employee(
                employeeDto.getId(), employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getEmail()
        );

//      Store employee JPA entity object into database by using save()
        Employee savedEmployee = employeeRepository.save(employee);

//       Convert employee JPA entity to employee dto
        EmployeeDto savedEmployeeDto = new EmployeeDto(
                savedEmployee.getId(), savedEmployee.getFirstName(), savedEmployee.getLastName(), savedEmployee.getEmail()
        );
        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();

//      Convert employee JPA entity to department dto
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
        return employeeDto;
    }
}
