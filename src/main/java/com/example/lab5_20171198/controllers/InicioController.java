package com.example.lab5_20171198.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {


    @GetMapping("/")
    public String inicio(){

        return "principal";
    }

    /*@GetMapping("/reportes")
    public String reportes(){

        return "reportes/barraLateral";
    }*/

    /*@GetMapping("/reportes/salarios")
    public String salarios(){

        return "reportes/salario";
    }*/
}
