package br.unipar.consultorio.model;

import br.unipar.consultorio.enums.EspecialidadeENUM;
import br.unipar.consultorio.enums.StatusENUM;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "MEDICO")
@ApiModel(description = "Modelo para representação de um Medico")
public class Medico {
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

    @NotNull
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(required = true)
    private EspecialidadeENUM especialidade;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    @ApiModelProperty(required = true)
    private String crm;

    @OneToOne
    @ApiModelProperty(required = true)
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private StatusENUM status;

}
