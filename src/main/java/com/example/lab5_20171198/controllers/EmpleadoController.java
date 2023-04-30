package com.example.lab5_20171198.controllers;


import com.example.lab5_20171198.entity.Empleado;
import com.example.lab5_20171198.repository.DepartmentRepository;
import com.example.lab5_20171198.repository.EmpleadoRepository;
import com.example.lab5_20171198.repository.JobRepository;
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
    final JobRepository jobRepository;
    final DepartmentRepository departmentRepository;


    public EmpleadoController(EmpleadoRepository empleadoRepository, JobRepository jobRepository, DepartmentRepository departmentRepository) {
        this.empleadoRepository = empleadoRepository;
        this.jobRepository = jobRepository;
        this.departmentRepository = departmentRepository;
    }



    //listar empelados
    @GetMapping(value = {"/list", ""})
    public String listarEmpleados(Model model) {

        List<Empleado> lista = empleadoRepository.findAll();
        model.addAttribute("empleadoList", lista);
        //model.addAttribute("empleadoPorRegion", employeeRepository.listarEmpleadosPorRegion());
       //model.addAttribute("empleadosPorPais",employeeRepository.listarEmpleadosPorPais());

        //empleadoRepository.actualizarNombreCompania("hola2",11);

        return "empleados/listaEmpleados";
    }


    //vista para crear un empelado
    @GetMapping("/new")
    public String nuevoEmpleado(Model model) {

        model.addAttribute("puestos", jobRepository.findAll());
        model.addAttribute("departamentos", departmentRepository.findAll());
        model.addAttribute("empleadoList", empleadoRepository.findAll());
        return "empleados/nuevoEmpleado";
    }

    @PostMapping("/save")
    public String guardarNuevoEmpleado(@RequestParam("name")String nombre, @RequestParam("apellido") String apellido,
                                       @RequestParam("email") String email, @RequestParam("password") String password,
                                       @RequestParam("jobs") String jobId, @RequestParam("sueldo") Double sueldo,
                                       @RequestParam("jefe") int jefeId, @RequestParam("departamento") int departamentoId,
                                       RedirectAttributes attr) {


        //empleado=new Empleado(nombre, apellido, email, password, jobId, sueldo, jefeId, departamentoId);
        //if (empleado.getId() == null) {
        attr.addFlashAttribute("msg", "empleado creado exitosamente");
        empleadoRepository.guardarEmpleado(nombre, apellido, email, password, jobId, sueldo, jefeId, departamentoId);
        //}


        return "redirect:/empleado/list";
    }

    @GetMapping("/edit")
    public String editarEmpleado(Model model,
                                      @RequestParam("id") int id) {

        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);

        if (optionalEmpleado.isPresent()) {
            Empleado empleado = optionalEmpleado.get();
            model.addAttribute("empleado", empleado);
            return "empleados/editarEmp";
        } else {
            return "redirect:/empleado/list";
        }
    }

    @GetMapping("/delete")
    public String borrarEmpleado(@RequestParam("id") int id) {

        Optional<Empleado> optShipper = empleadoRepository.findById(id);
        if (optShipper.isPresent()) {
            empleadoRepository.borrarEmpleado(id);
        }
        return "redirect:/empleado/list";

    }

    @PostMapping("/BuscarEmpleado")
    public String buscarTransportista(@RequestParam("searchField") String searchField,
                                      Model model) {

        //List<Empleado> listaEmpleados = empleadoRepository.buscarTransPorCompName(searchField);
        //model.addAttribute("empleadosList", listaEmpleados);

        return "empleados/listaEmpleados";
    }
}
