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
@Api(description = "Controlador REST responsável pela Operações que representam o objeto de negócios Consulta")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @ApiOperation(value = "Operação responsável por realizar a Inserção de uma nova Consulta")
    public ConsultaDTO insert(@RequestBody @Valid Consulta consulta) throws Exception{
        return consultaService.insert(consulta);
    }

    @PutMapping(path = "/cancelar")
    @ApiOperation("Operação responsável por realizar o Cancelamento de uma Consulta")
    public Consulta cancela(@RequestBody Consulta consulta) throws Exception{
        return consultaService.cancela(consulta);
    }

    @GetMapping
    @ApiOperation("Operação resposável por buscar todas as Consultas Agendadas")
    public List<ConsultaDTO> findAll() throws Exception{
        return consultaService.findAll();
    }
}
