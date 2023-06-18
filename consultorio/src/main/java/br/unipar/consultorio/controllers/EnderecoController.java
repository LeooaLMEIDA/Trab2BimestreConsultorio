package br.unipar.consultorio.controllers;

import br.unipar.consultorio.model.Endereco;
import br.unipar.consultorio.services.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/endereco")
@Api(description = "Controlador REST Responsável pela Operações que representam o objeto de negócios Endereço")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    @ApiOperation(value = "Operação resposável pela Inserção de um novo Endereço")
    public Endereco insert(@RequestBody @Valid Endereco endereco) throws Exception{
        return enderecoService.insert(endereco);
    }

    @PutMapping
    @ApiOperation(value = "Operação responsável pela Atualização de um Endereço já existente")
    public Endereco update(@RequestBody @Valid Endereco endereco) throws Exception{
        return enderecoService.update(endereco);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Endereço via ID")
    public Endereco findById(@PathVariable Long id) throws Exception{
        return enderecoService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca do Endereço via CEP")
    public List<Endereco> findByFilters(@RequestParam("cep") String cep)throws Exception{
        return enderecoService.findByFilters(cep);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todos os Endereços cadastrados no sistema")
    public List<Endereco> findAll() throws Exception{
        return enderecoService.findAll();
    }
}
