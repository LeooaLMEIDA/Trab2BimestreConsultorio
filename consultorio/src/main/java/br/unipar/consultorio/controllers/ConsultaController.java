package br.unipar.consultorio.controllers;

import br.unipar.consultorio.services.ConsultaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/consulta")
@Api(description = "Controlador REST Responsável pela Operações que representam o objeto de negócios Consulta")
public class ConsultaController {
   /// @Autowired
   // private ConsultaService consultaService;

}
