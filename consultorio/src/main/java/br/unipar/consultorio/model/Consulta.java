package br.unipar.consultorio.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "CONSULTA")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotBlank
    @NotEmpty
    @NotNull
    private Paciente paciente;

    @OneToOne
    @NotBlank
    @NotEmpty
    @NotNull
    private Medico medico;

    @NotBlank
    @NotEmpty
    @NotNull
    private Date dataHoraConsulta;





}
