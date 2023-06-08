package br.unipar.consultorio.services;

import br.unipar.consultorio.model.Medico;
import br.unipar.consultorio.repositories.MedicoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    public Medico insert(@RequestBody @Valid Medico medico) throws Exception{
        medicoRepository.saveAndFlush(medico);
        return medico;
    }

}
