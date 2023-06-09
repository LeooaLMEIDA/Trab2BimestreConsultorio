package br.unipar.consultorio.controllers;

import br.unipar.consultorio.model.Paciente;
import br.unipar.consultorio.model.dto.PacienteDTO;
import br.unipar.consultorio.services.PacienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/paciente")
@ApiOperation(value = "Controlador REST Responsável pela Operações que representam o objeto de negócios Paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    @ApiOperation(value = "Operação resposável pela Inserção de um novo Paciente")
    public Paciente insert(@RequestBody @Valid Paciente paciente) throws Exception{
        return pacienteService.insert(paciente);
    }

    @PutMapping
    @ApiOperation(value = "Operação responsável pela Atualização de um Paciente já existente")
    public Paciente update(@RequestBody @Valid Paciente paciente) throws Exception{
        return pacienteService.update(paciente);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "")
    public Paciente delete(@PathVariable Long id) throws Exception{
        return pacienteService.delete(id);
    }


    @GetMapping(path ="/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Paciente via ID")
    public Paciente findById(@PathVariable Long id) throws Exception{
        return pacienteService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca do Paciente pelo Nome")
    public List<Paciente> findByFielters(@RequestParam String nome) throws Exception{
        return pacienteService.findByFilters(nome);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todos os Pacientes cadastrados no sistema")
    public List<PacienteDTO> findAll() throws Exception{
        return pacienteService.findAll();
    }

}
