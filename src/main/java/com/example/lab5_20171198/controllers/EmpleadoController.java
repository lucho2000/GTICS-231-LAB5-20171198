package com.example.lab5_20171198.controllers;


import com.example.lab5_20171198.entity.Empleado;
import com.example.lab5_20171198.repository.EmpleadoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {


    final EmpleadoRepository empleadoRepository;

    public EmpleadoController(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping(value = {"/list", ""})
    public String listarTransportistas(Model model) {

        List<Empleado> lista = empleadoRepository.findAll();
        model.addAttribute("empleadoList", lista);
        //model.addAttribute("empleadoPorRegion", employeeRepository.listarEmpleadosPorRegion());
       //model.addAttribute("empleadosPorPais",employeeRepository.listarEmpleadosPorPais());

        empleadoRepository.actualizarNombreCompania("hola2",11);

        return "Empleados/listaEmpleados";
    }

    @GetMapping("/new")
    public String nuevoTransportistaFrm() {
        return "Empleados/nuevoEmpleado";
    }

    @PostMapping("/save")
    public String guardarNuevoTransportista(Empleado empleado, RedirectAttributes attr) {

        if (empleado.getId() == null) {
            attr.addFlashAttribute("msg", "Transportista creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Transportista actualizado exitosamente");
        }

        empleadoRepository.save(empleado);
        return "redirect:/empleado/list";
    }

    @GetMapping("/edit")
    public String editarEmpleado(Model model,
                                      @RequestParam("id") int id) {

        Optional<Empleado> optShipper = empleadoRepository.findById(id);

        if (optShipper.isPresent()) {
            Empleado empleado = optShipper.get();
            model.addAttribute("shipper", empleado);
            return "Empleados/editarEmp";
        } else {
            return "redirect:/empleado/list";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(@RequestParam("id") int id) {

        Optional<Empleado> optShipper = empleadoRepository.findById(id);
        if (optShipper.isPresent()) {
            empleadoRepository.deleteById(id);
        }
        return "redirect:/empleado/list";

    }

    @PostMapping("/BuscarEmpleado")
    public String buscarTransportista(@RequestParam("searchField") String searchField,
                                      Model model) {

        List<Empleado> listaTransportistas = empleadoRepository.buscarTransPorCompName(searchField);
        model.addAttribute("shipperList", listaTransportistas);

        return "Empleados/listaEmpleados";
    }
}
