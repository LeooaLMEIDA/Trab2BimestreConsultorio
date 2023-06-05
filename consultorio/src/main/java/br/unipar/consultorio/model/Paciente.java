package br.unipar.consultorio.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 1, max = 11)
    @ApiModelProperty(required = true)
    private String cpf;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    @NotBlank
    @NotEmpty
    @NotNull
    @ApiModelProperty(required = true)
    private Endereco endereco;

}
