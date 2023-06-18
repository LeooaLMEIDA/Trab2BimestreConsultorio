package br.unipar.consultorio.model;

import br.unipar.consultorio.enums.MotivoCancelConsultENUM;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONSULTA")
@ApiModel(description = "Modelo para representação de uma Consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @ApiModelProperty(required = true)
    private Paciente paciente;

    @ManyToOne
    @ApiModelProperty(required = true)
    private Medico medico;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(required = true)
    private MotivoCancelConsultENUM motivoCancelamento;

    @NotNull
    @ApiModelProperty(required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date dataHoraConsulta;

}
