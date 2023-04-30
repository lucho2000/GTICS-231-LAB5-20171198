package com.example.lab5_20171198.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

public interface JobsporMinMaxSalaryDto {

    String getNombrePuesto();
    int getSalarioMaximo();

    int getSalarioMinimo();

    int getSalarioTotal();

    int getSalarioProm();


}
