package com.toxicprogrammer.departmentservice.service.impl;

import com.toxicprogrammer.departmentservice.dto.DepartmentDto;
import com.toxicprogrammer.departmentservice.entity.Department;
import com.toxicprogrammer.departmentservice.repository.DepartmentRepository;
import com.toxicprogrammer.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private ModelMapper modelMapper;

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
//        Convert department DTO to department JPA Entity.
        Department department = modelMapper.map(departmentDto, Department.class);

//        Store department JPA entity object into database by using save()
        Department savedDepartment = departmentRepository.save(department);

//        Converted savedDepartment JPA entity object into DepartmentDto and return DepartmentDto object i.e. savedDepartmentDto
        DepartmentDto savedDepartmentDto = modelMapper.map(savedDepartment, DepartmentDto.class);

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Department department = departmentRepository.findByDepartmentCode(departmentCode);

//        Convert department JPA entity to department dto
        DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);

        return departmentDto;
    }
}
