package com.toxicprogrammer.departmentservice.mapper;

import com.toxicprogrammer.departmentservice.dto.DepartmentDto;
import com.toxicprogrammer.departmentservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper MAPPER = Mappers.getMapper(DepartmentMapper.class);

    DepartmentDto mapToDepartmentDto(Department department);

    Department mapToDepartment(DepartmentDto departmentDto);

}
