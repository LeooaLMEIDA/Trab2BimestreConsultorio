package br.unipar.consultorio.services;

import br.unipar.consultorio.enums.StatusENUM;
import br.unipar.consultorio.model.Endereco;
import br.unipar.consultorio.model.Medico;
import br.unipar.consultorio.model.dto.MedicoDTO;
import br.unipar.consultorio.repositories.EnderecoRepository;
import br.unipar.consultorio.repositories.MedicoRepository;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ApiModel(description = "Classe responsável pela regras de Negócio referente ao Médico")
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EnderecoService enderecoService;

    public Medico insert(Medico medico) throws Exception{
        validaInsert(medico);
        medico.setStatus(StatusENUM.ATIVO);
        medicoRepository.saveAndFlush(medico);
        Endereco endereco = enderecoService.findById(medico.getEndereco().getId());
        medico.setEndereco(endereco);
        return medico;
    }

    public Medico update(Medico medico) throws Exception{
        validaUpdate(medico);
        medico.setStatus(StatusENUM.ATIVO);
        medicoRepository.saveAndFlush(medico);
        Endereco endereco = enderecoService.findById(medico.getEndereco().getId());
        medico.setEndereco(endereco);
        return medico;
    }

    public Medico delete(Long id) throws Exception {
        Optional<Medico> retorno = medicoRepository.findById(id);
        if (retorno.isPresent()){
            Medico medico = new Medico();
            medico.setId(retorno.get().getId());
            medico.setNome(retorno.get().getNome());
            medico.setCrm(retorno.get().getCrm());
            medico.setEmail(retorno.get().getEmail());
            medico.setEndereco(retorno.get().getEndereco());
            medico.setTelefone(retorno.get().getTelefone());
            medico.setEspecialidade(retorno.get().getEspecialidade());
            medico.setStatus(StatusENUM.INATIVO);
            return medicoRepository.saveAndFlush(medico);
        }else{
            throw new Exception("Medico " + id + " não foi encontrado");
        }
    }

    public MedicoDTO findById(Long id) throws Exception{
        Optional<Medico> retorno = medicoRepository.findById(id);
        if (retorno.isPresent()){
            MedicoDTO medicoDTO = new MedicoDTO();
            medicoDTO.setNome(retorno.get().getNome());
            medicoDTO.setEmail(retorno.get().getEmail());
            medicoDTO.setCrm(retorno.get().getCrm());
            medicoDTO.setEspecialidade(retorno.get().getEspecialidade());
            medicoDTO.setStatusENUM(retorno.get().getStatus());

            return medicoDTO;
        }
        else{
            throw new Exception("Medico " + id + " não encontrado.");
        }
    }

    public List<Medico> findByFilters(String nome) throws Exception{
        return medicoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<MedicoDTO> findAll() throws Exception{
        List<Medico> medicos        = medicoRepository.findAllByOrderByNomeAsc();
        List<MedicoDTO> medicosDTO  = new ArrayList<>();

        for (Medico medico:medicos) {
            MedicoDTO medicoDTO = new MedicoDTO();
            medicoDTO.setNome(medico.getNome());
            medicoDTO.setEmail(medico.getEmail());
            medicoDTO.setCrm(medico.getCrm());
            medicoDTO.setEspecialidade(medico.getEspecialidade());
            medicoDTO.setStatusENUM(medico.getStatus());

            medicosDTO.add(medicoDTO);

        }
        return medicosDTO;
    }

    private void validaMedico(Medico medico) throws Exception{
        if (medico.getEspecialidade() == null) {
            throw new Exception("É necessário informar a Especialidade do Médico.");
        }
    }

    private void validaInsert(Medico medico) throws Exception{
        if(medico.getId() != null){
            throw new Exception("Não é necessário informar o ID para cadastrar um novo Médico");
        }
        validaMedico(medico);
    }

    private void validaUpdate(Medico medico) throws Exception{
        if(medico.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro do Médico");
        }

        Optional<Medico> retorno = medicoRepository.findById(medico.getId());
        if (!(retorno.get().getEmail().equals(medico.getEmail()))){
            throw new Exception("Não é permitido realizar a alteração do E-mail do Médico");
        }

        if (!(retorno.get().getCrm().equals(medico.getCrm()))){
            throw new Exception("Não é permitido realizar a alteração do CRM do Médico");
        }

        if (!(retorno.get().getEspecialidade().equals(medico.getEspecialidade()))){
            throw new Exception("Não é permitido realizar a alteração da Especialidade do Médico");
        }
    }
}
