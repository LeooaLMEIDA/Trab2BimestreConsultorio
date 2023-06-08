package br.unipar.consultorio.services;

import br.unipar.consultorio.model.Medico;
import br.unipar.consultorio.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    public Medico insert(Medico medico) throws Exception{
        medicoRepository.saveAndFlush(medico);
        return medico;
    }

    public Medico update(Medico medico) throws Exception{
        medicoRepository.saveAndFlush(medico);
        return medico;
    }

    public Medico findById(Long id) throws Exception{
        Optional<Medico> retorno = medicoRepository.findById(id);
        if (retorno.isPresent()){
            return retorno.get();
        }
        else{
            throw new Exception("Medico " + id + " não encontrado.");
        }
    }

    public List<Medico> findByFilters(String nome) throws Exception{
        return medicoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Medico> findAll() throws Exception{
        return medicoRepository.findAll();
    }

}
