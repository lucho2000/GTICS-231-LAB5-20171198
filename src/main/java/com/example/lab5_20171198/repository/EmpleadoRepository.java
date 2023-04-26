package com.example.lab5_20171198.repository;

import com.example.lab5_20171198.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {

    List<Empleado> findByCompanyname(String nombre);

    @Query(value = "select * from employees where CompanyName = ?1",
            nativeQuery = true)
    List<Empleado> buscarTransPorCompName(String nombre);


    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "update shippers set companyname = ?1 where shipperid = ?2")
    void actualizarNombreCompania(String companyName, int shipperId);
}
