package br.unipar.consultorio.services;

import br.unipar.consultorio.model.Consulta;
import br.unipar.consultorio.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta insert(Consulta consulta) throws Exception{
        consultaRepository.saveAndFlush(consulta);
        return consulta;
    }

    public Consulta update(Consulta consulta) throws Exception{
        consultaRepository.saveAndFlush(consulta);
        return consulta;
    }

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public Consulta findById(Long id) throws Exception{
        Optional<Consulta> retorno = consultaRepository.findById(id);
        if (retorno.isPresent()){
            return retorno.get();
        }
        else {
            throw new Exception("Consulta " + id + " não foi localizada");
        }
    }

}
