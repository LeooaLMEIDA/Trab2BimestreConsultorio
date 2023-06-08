package br.unipar.consultorio.controllers;

import br.unipar.consultorio.model.Medico;
import br.unipar.consultorio.services.MedicoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/medico")
@ApiOperation(value = "Controlador REST Responsável pela Operações que representam o objeto de negócios Médico")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @ApiOperation(value = "Operação resposável pela Inserção de um novo Medico")
    public Medico insert(@RequestBody @Valid Medico medico) throws Exception{
        return medicoService.insert(medico);
    }

    @PutMapping
    @ApiOperation(value = "Operação responsável pela Atualização de um Medico já existente")
    public Medico update(@RequestBody Medico medico) throws Exception{
        return medicoService.update(medico);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Médico via ID")
    public Medico findById(@PathVariable Long id) throws Exception{
        return medicoService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca do Médico pelo Nome")
    public List<Medico> findByFilters(@RequestParam("nome") String nome) throws Exception{
        return medicoService.findByFilters(nome);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todos os Médicos cadastrados no sistema")
    public List<Medico> findAll() throws Exception{
        return medicoService.findAll();
    }

}
