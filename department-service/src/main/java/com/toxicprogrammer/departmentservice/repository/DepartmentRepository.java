package com.toxicprogrammer.departmentservice.repository;

import com.toxicprogrammer.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
