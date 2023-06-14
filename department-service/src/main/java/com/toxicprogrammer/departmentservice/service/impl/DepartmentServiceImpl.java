package com.toxicprogrammer.departmentservice.service.impl;

import com.toxicprogrammer.departmentservice.dto.DepartmentDto;
import com.toxicprogrammer.departmentservice.entity.Department;
import com.toxicprogrammer.departmentservice.repository.DepartmentRepository;
import com.toxicprogrammer.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
//        Convert department DTO to department JPA Entity.
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );

//        Store department JPA entity object into database by using save()
        Department savedDepartment = departmentRepository.save(department);

//        Converted savedDepartment JPA entity object into DepartmentDto and return DepartmentDto object i.e. savedDepartmentDto
        DepartmentDto savedDepartmentDto = new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode()
        );

        return savedDepartmentDto;
    }
}
