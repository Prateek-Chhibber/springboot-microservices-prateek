package com.toxicprogrammer.departmentservice.service.impl;

import com.toxicprogrammer.departmentservice.dto.DepartmentDto;
import com.toxicprogrammer.departmentservice.entity.Department;
import com.toxicprogrammer.departmentservice.mapper.DepartmentMapper;
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
        Department department = DepartmentMapper.MAPPER.mapToDepartment(departmentDto);

//        Store department JPA entity object into database by using save()
        Department savedDepartment = departmentRepository.save(department);

//        Converted savedDepartment JPA entity object into DepartmentDto and return DepartmentDto object i.e. savedDepartmentDto
        DepartmentDto savedDepartmentDto = DepartmentMapper.MAPPER.mapToDepartmentDto(savedDepartment);

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Department department = departmentRepository.findByDepartmentCode(departmentCode);

//        Convert department JPA entity to department dto
        DepartmentDto departmentDto = DepartmentMapper.MAPPER.mapToDepartmentDto(department);

        return departmentDto;
    }
}
