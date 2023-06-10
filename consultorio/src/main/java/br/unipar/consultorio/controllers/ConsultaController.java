package br.unipar.consultorio.controllers;

import br.unipar.consultorio.model.Consulta;
import br.unipar.consultorio.model.dto.ConsultaDTO;
import br.unipar.consultorio.services.ConsultaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/consulta")
@Api(description = "Controlador REST Responsável pela Operações que representam o objeto de negócios Consulta")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @ApiOperation(value = "Operação Responsável por realizar a inserção de uma nova consulta")
    public ConsultaDTO insert(@RequestBody @Valid Consulta consulta) throws Exception{
        return consultaService.insert(consulta);
    }

    @PutMapping(path = "/cancelar")
    public Consulta cancela(@RequestBody Consulta consulta) throws Exception{
        return consultaService.cancela(consulta);
    }

    @GetMapping
    public List<Consulta> findAll() throws Exception{
        return consultaService.findAll();
    }


}
