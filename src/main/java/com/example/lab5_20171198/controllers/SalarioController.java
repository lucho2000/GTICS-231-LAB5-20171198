package com.example.lab5_20171198.controllers;


import com.example.lab5_20171198.dto.JobsporMinMaxSalaryDto;
import com.example.lab5_20171198.entity.Empleado;
import com.example.lab5_20171198.entity.Jobs;
import com.example.lab5_20171198.repository.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reportes")
public class SalarioController {

    final JobRepository jobRepository;

    public SalarioController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping(value= {"/barra",""})
    public String barra(){

        return "reportes/barraLateral";
    }
    //listar salarios
    @GetMapping("/lista")
    public String listarSalarios(Model model) {

        //List<Jobs> listaDto = ;
        model.addAttribute("listaJobsDto", jobRepository.jobsPorMinyMaxSalary());


        return "reportes/salario";
    }
}
