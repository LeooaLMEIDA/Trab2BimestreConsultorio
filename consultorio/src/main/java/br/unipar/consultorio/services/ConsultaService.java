package br.unipar.consultorio.services;

import br.unipar.consultorio.model.Consulta;
import br.unipar.consultorio.model.Medico;
import br.unipar.consultorio.model.Paciente;
import br.unipar.consultorio.model.dto.ConsultaDTO;
import br.unipar.consultorio.repositories.ConsultaRepository;
import br.unipar.consultorio.repositories.MedicoRepository;
import br.unipar.consultorio.repositories.PacienteRepository;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ApiModel(description = "Classe responsável pela regras de Negócio referente a Consulta")
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public ConsultaDTO insert(Consulta consulta) throws Exception{
        validaInsert(consulta);
        consultaRepository.saveAndFlush(consulta);
        return ConsultaDTO.consultaDTO(consulta);
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

    public List<ConsultaDTO> findAll() {
        List<Consulta> consultas    = consultaRepository.findAll();
        List<ConsultaDTO> consultasDTO = new ArrayList<>();

        for (Consulta consulta: consultas) {
            ConsultaDTO consultaDTO = new ConsultaDTO();
            consultaDTO.setId(consulta.getId());
            consultaDTO.setPaciente_id(consulta.getPaciente().getId());
            consultaDTO.setPaciente_nome(consulta.getPaciente().getNome());
            consultaDTO.setMedico_id(consulta.getMedico().getId());
            consultaDTO.setMedico_nome(consulta.getMedico().getNome());
            consultaDTO.setDataHora(consulta.getDataHoraConsulta().toString());

            consultasDTO.add(consultaDTO);
        }

        return consultasDTO;
    }

    public Consulta cancela(Consulta consulta) throws Exception{
        Consulta consultaCancelada = validaCancela(consulta);
        return consultaRepository.saveAndFlush(consultaCancelada);
    }

    private Consulta validaCancela(Consulta consulta) throws Exception{
        if (consulta.getId() == null){
            throw new Exception("Para realizar o cancelamento, é necessário informar o ID da Consulta");
        }

        if (consulta.getMotivoCancelamento() == null){
            throw new Exception("Para realizar o cancelamento, é necessário informar o Motivo");
        }
        return validaAntecedenciaConsulta(consulta);
    }

    private Consulta validaAntecedenciaConsulta(Consulta consulta) throws Exception{
        Optional<Consulta> retorno = consultaRepository.findById(consulta.getId());
        Consulta consultaValidada;

        LocalDateTime horaAtual = LocalDateTime.now();
        Instant instant = retorno.get().getDataHoraConsulta().toInstant();
        LocalDateTime datahora = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        Long horasDif = ChronoUnit.HOURS.between(horaAtual,datahora);

        if (horasDif < 24){
            throw new Exception("Só poderá cancelar uma Consulta com 24 Horas de antecedencia");
        }

        consultaValidada = retorno.get();

        if (consultaValidada.getMotivoCancelamento() != null){
            throw new Exception("Consulta já se encontra Cancelada");
        }

        consultaValidada.setMotivoCancelamento(consulta.getMotivoCancelamento());

        return consultaValidada;
    }

    private void validaInsert(Consulta consulta) throws Exception{
        if (consulta.getId() != null) {
            throw new Exception("Não é necessário informar o ID para cadastrar uma nova Consulta");
        }
        validaConsulta(consulta);
    }

    private void validaConsulta(Consulta consulta) throws Exception{
        if (consulta.getDataHoraConsulta().getHours() < 7 || consulta.getDataHoraConsulta().getHours() > 19){
            throw new Exception("O horário de Atendimento é das 07:00 às 19:00");
        }
        if (consulta.getMedico() == null){
            findMedicosDisponiveis(consulta);
        }

        validaAgendamentoAntecedencia(consulta);
        validaStatusEnvolvidos(consulta);
        validaPacienteConsulta(consulta);
        validaMedicoConsulta(consulta);

    }

    private void findMedicosDisponiveis(Consulta consulta) throws Exception{
        List<Medico> listaMedicos = medicoRepository.findMedicosDisponiveis(consulta.getDataHoraConsulta());
        if (listaMedicos.size() == 0) {
            throw new Exception("Não há médicos disponiveis no momento, tente novamente.");
        }

        consulta.setMedico(listaMedicos.get(0));
    }

    private void validaMedicoConsulta(Consulta consulta) throws Exception{
        List<Consulta> listaConsultasByMedico = consultaRepository.findConsultaByMedicoAndDataHoraConsulta(
                consulta.getMedico(), consulta.getDataHoraConsulta());

        if (listaConsultasByMedico.size() > 0){
            throw new Exception("Médico já possui uma Consulta para a data informada");
        }
    }

    private void validaPacienteConsulta(Consulta consulta) throws Exception{
        List<Consulta> listaConsultasByPaciente = consultaRepository.findConsultaByPacienteAndAndDataHoraConsulta(
                consulta.getPaciente(),consulta.getDataHoraConsulta());

        if (listaConsultasByPaciente.size() > 0) {
            throw new Exception("Paciente já possui uma Consulta para a data informada");
        }
    }

    private void validaStatusEnvolvidos(Consulta consulta) throws Exception{
        Optional<Paciente> retornoPaciente = pacienteRepository.findById(consulta.getPaciente().getId());

        String status = String.valueOf(retornoPaciente.get().getStatus());

        if (status.equals("INATIVO")){
            throw new Exception("O Paciente deve estar ATIVO para poder agendar uma Consulta");
        }

        Optional<Medico> retornoMedico = medicoRepository.findById(consulta.getMedico().getId());

        status = String.valueOf(retornoMedico.get().getStatus());

        if (status.equals("INATIVO")){
            throw new Exception("O Médico deve estar ATIVO para poder agendar uma Consulta");
        }
    }

    private void validaAgendamentoAntecedencia(Consulta consulta) throws Exception {
        LocalDateTime horaAtual = LocalDateTime.now();
        Instant instant = consulta.getDataHoraConsulta().toInstant();
        LocalDateTime datahora = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        Long tempoDif = ChronoUnit.MINUTES.between(horaAtual,datahora);

        if (tempoDif < 30) {
            throw new Exception("É preciso uma antecedencia de no minimo 30 minutos para realizar o agendameno da Consulta");
        }
    }
}
