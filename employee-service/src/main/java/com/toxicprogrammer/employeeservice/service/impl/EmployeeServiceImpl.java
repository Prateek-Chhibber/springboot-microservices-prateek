package com.toxicprogrammer.employeeservice.service.impl;

import com.toxicprogrammer.employeeservice.dto.APIResponseDto;
import com.toxicprogrammer.employeeservice.dto.DepartmentDto;
import com.toxicprogrammer.employeeservice.dto.EmployeeDto;
import com.toxicprogrammer.employeeservice.dto.OrganizationDto;
import com.toxicprogrammer.employeeservice.entity.Employee;
import com.toxicprogrammer.employeeservice.mapper.EmployeeMapper;
import com.toxicprogrammer.employeeservice.repository.EmployeeRepository;
import com.toxicprogrammer.employeeservice.service.APIClient;
import com.toxicprogrammer.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private APIClient apiClient;

    private WebClient webClient;

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

//        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        //  DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

//      Convert employee JPA entity to employee dto
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode(),
                employee.getOrganizationCode()

        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setOrganizationDto(organizationDto);
        return apiResponseDto;
    }
}
