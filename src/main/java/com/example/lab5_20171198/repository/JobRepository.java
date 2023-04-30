package com.example.lab5_20171198.repository;

import com.example.lab5_20171198.dto.JobsporMinMaxSalaryDto;
import com.example.lab5_20171198.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Jobs,Integer> {

    @Query(value = "SELECT j.job_title as nombrepuesto, max(e.salary) as salariomaximo, min(e.salary) as salariominimo , sum(e.salary) as salariototal, truncate(avg(e.salary),2) as salarioprom\n" +
            "FROM jobs j\n" +
            "inner join employees e on e.job_id=j.job_id\n" +
            "group by j.job_title", nativeQuery = true)
    List<JobsporMinMaxSalaryDto> jobsPorMinyMaxSalary(); //asoaciado con el tipo de dto creado
}
