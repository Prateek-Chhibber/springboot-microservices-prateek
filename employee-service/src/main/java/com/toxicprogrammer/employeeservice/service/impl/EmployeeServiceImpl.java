package com.toxicprogrammer.employeeservice.service.impl;

import com.toxicprogrammer.employeeservice.dto.APIResponseDto;
import com.toxicprogrammer.employeeservice.dto.DepartmentDto;
import com.toxicprogrammer.employeeservice.dto.EmployeeDto;
import com.toxicprogrammer.employeeservice.entity.Employee;
import com.toxicprogrammer.employeeservice.mapper.EmployeeMapper;
import com.toxicprogrammer.employeeservice.repository.EmployeeRepository;
import com.toxicprogrammer.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private RestTemplate restTemplate;

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
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();

        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee.getDepartmentCode(),
                DepartmentDto.class);

        DepartmentDto departmentDto = responseEntity.getBody();

//      Convert employee JPA entity to employee dto
        EmployeeDto employeeDto = EmployeeMapper.MAPPER.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }
}
