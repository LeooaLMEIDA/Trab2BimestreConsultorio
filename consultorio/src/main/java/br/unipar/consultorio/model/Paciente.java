package br.unipar.consultorio.model;

import br.unipar.consultorio.enums.StatusENUM;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Generated;

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
    @Pattern(regexp = "000.000.000-00")
    @ApiModelProperty(name = "O CPF deve ser informado no seguinte formato: 000.000.000-00", required = true)
    private String cpf;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    @NotBlank
    @NotEmpty
    @NotNull
    @ApiModelProperty(required = true)
    private Endereco endereco;

    @NotBlank
    @NotEmpty
    @NotNull
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(required = true)
    private StatusENUM status;

}
