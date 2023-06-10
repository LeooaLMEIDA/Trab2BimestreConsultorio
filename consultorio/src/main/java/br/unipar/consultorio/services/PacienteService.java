package br.unipar.consultorio.services;

import br.unipar.consultorio.enums.StatusENUM;
import br.unipar.consultorio.model.Paciente;
import br.unipar.consultorio.model.dto.PacienteDTO;
import br.unipar.consultorio.repositories.PacienteRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ApiOperation(value = "")
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente insert(Paciente paciente) throws Exception{
        validaInsert(paciente);

        paciente.setStatus(StatusENUM.ATIVO);
        pacienteRepository.saveAndFlush(paciente);
        return paciente;
    }

    public Paciente update(Paciente paciente) throws Exception{
        validaUpdate(paciente);
        paciente.setStatus(StatusENUM.ATIVO);
        pacienteRepository.saveAndFlush(paciente);
        return paciente;
    }

    public Paciente delete(Long id) throws Exception{
        Optional<Paciente> retorno = pacienteRepository.findById(id);
        if (retorno.isPresent()){
            Paciente paciente = new Paciente();
            paciente.setId(retorno.get().getId());
            paciente.setNome(retorno.get().getNome());
            paciente.setCpf(retorno.get().getCpf());
            paciente.setEmail(retorno.get().getEmail());
            paciente.setEndereco(retorno.get().getEndereco());
            paciente.setTelefone(retorno.get().getTelefone());
            paciente.setStatus(StatusENUM.INATIVO);
            return pacienteRepository.saveAndFlush(paciente);
        }
        else{
            throw new Exception("Paciente "+ id + " não foi encontrado");
        }
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

    public List<PacienteDTO> findAll() throws Exception{
        List<Paciente> pacientes = pacienteRepository.findAllByOrderByNomeAsc();
        List<PacienteDTO> pacientesDTO = new ArrayList<>();

        for (Paciente paciente:pacientes) {
            PacienteDTO pacienteDTO = new PacienteDTO();
            pacienteDTO.setNome(paciente.getNome());
            pacienteDTO.setEmail(paciente.getEmail());
            pacienteDTO.setCpf(paciente.getCpf());

            pacientesDTO.add(pacienteDTO);
        }

        return pacientesDTO;
    }

    private void validaInsert(Paciente paciente) throws Exception {
        if (paciente.getId() != null) {
            throw new Exception("Não é necessário informar o ID para cadastrar um novo Paciente");
        }
    }

    private void validaUpdate(Paciente paciente) throws Exception{
        if (paciente.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o Paciente");
        }

        Optional<Paciente> retorno = pacienteRepository.findById(paciente.getId());
        if (!(retorno.get().getEmail().equals(paciente.getEmail()))){
            throw new Exception("Não é permitido realizar a alteração do E-mail do Paciente");
        }

        if (!(retorno.get().getCpf().equals(paciente.getCpf()))){
            throw new Exception("Não é permitido realizar a alteração do CPF do Paciente");
        }
    }
}
