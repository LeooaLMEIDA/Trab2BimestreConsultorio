package br.unipar.consultorio.model;

import br.unipar.consultorio.enums.StatusENUM;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Generated;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "PACIENTE")
@ApiModel(description = "Modelo para representação de um Paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Id Autogerado pelo Sistema")
    private Long id;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    @ApiModelProperty(required = true)
    private String nome;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    @ApiModelProperty(required = true)
    private String email;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 12)
    @ApiModelProperty(required = true)
    private String telefone;

    @NotBlank
    @NotEmpty
    @NotNull
    @CPF
    @ApiModelProperty(name = "O CPF deve ser informado no seguinte formato: 000.000.000-00", required = true)
    private String cpf;

    @OneToOne
    @ApiModelProperty(required = true)
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(required = true)
    private StatusENUM status;

}
