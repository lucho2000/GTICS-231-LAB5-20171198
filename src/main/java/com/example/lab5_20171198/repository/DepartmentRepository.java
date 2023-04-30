package com.example.lab5_20171198.repository;

import com.example.lab5_20171198.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
