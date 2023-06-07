package br.unipar.consultorio.controllers;

import br.unipar.consultorio.model.Consulta;
import br.unipar.consultorio.services.ConsultaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/consulta")
@Api(description = "Controlador REST Responsável pela Operações que representam o objeto de negócios Consulta")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @ApiOperation(value = "Operação Responsável por realizar a inserção de uma nova consulta")
    public Consulta insert(@RequestBody @Valid Consulta consulta) throws Exception{
        return consultaService.insert(consulta);
    }


}
