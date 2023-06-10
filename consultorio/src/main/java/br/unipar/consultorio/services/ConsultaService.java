package br.unipar.consultorio.services;

import br.unipar.consultorio.model.Consulta;
import br.unipar.consultorio.model.dto.ConsultaDTO;
import br.unipar.consultorio.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public ConsultaDTO insert(Consulta consulta) throws Exception{
        consultaRepository.saveAndFlush(consulta);
        return ConsultaDTO.consultaDTO(consulta);
    }

    public Consulta cancela(Consulta consulta) throws Exception{
        if (consulta.getId() == null){
            throw new Exception("Para realizar o cancelmaneto, é necessário informar o ID da Consulta");
        }

        if (consulta.getMotivoCancelamento() == null){
            throw new Exception("Para realizar o cancelamento, é necessário informar o Motivo");
        }

        return validaAntecedenciaConsulta(consulta);
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

    private Consulta validaAntecedenciaConsulta(Consulta consulta) throws Exception{
        Optional<Consulta> retorno = consultaRepository.findById(consulta.getId());
        Consulta consultaValidada = new Consulta();
        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaMarcadaConsulta = retorno.get().getDataHoraConsulta();

        Duration diferença = Duration.between(horaAtual,horaMarcadaConsulta);
        Long horasDiferença = diferença.toHours();

        if (horasDiferença < 24){
            throw new Exception("Só podera cancelar uma Consulta com 24 Horas de antecedencia");
        }
        consultaValidada = retorno.get();

        consultaValidada.setMotivoCancelamento(retorno.get().getMotivoCancelamento());

        return consultaValidada;

    }

}
