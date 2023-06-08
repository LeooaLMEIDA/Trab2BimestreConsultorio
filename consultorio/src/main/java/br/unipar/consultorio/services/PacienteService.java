package br.unipar.consultorio.services;

import br.unipar.consultorio.model.Paciente;
import br.unipar.consultorio.repositories.PacienteRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ApiOperation(value = "")
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente insert(Paciente paciente) throws Exception{
        pacienteRepository.saveAndFlush(paciente);
        return paciente;
    }

    public Paciente update(Paciente paciente) throws Exception{
        pacienteRepository.saveAndFlush(paciente);
        return paciente;
    }

    public Paciente findById(Long id) throws Exception{
        Optional<Paciente> resultado = pacienteRepository.findById(id);
        if (resultado.isPresent()){
            return resultado.get();
        }else{
            throw new Exception("Paciente " + id + " não encontrado.");
        }
    }

    public List<Paciente> findByFilters(String nome) throws Exception{
        return pacienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Paciente> findAll(){
        return pacienteRepository.findAll();
    }

}
