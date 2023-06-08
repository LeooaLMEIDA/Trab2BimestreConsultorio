package br.unipar.consultorio.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "ENDERECO")
@ApiModel(description = "Modelo para representação de um Endereço")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Id Autogerado pelo Sistema")
    private Long id;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    @ApiModelProperty(required = true)
    private String Logradouro;

    @ApiModelProperty(required = false)
    private Integer numero;

    @ApiModelProperty(required = false)
    private String complemento;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    @ApiModelProperty(required = true)
    private String bairro;

    @NotBlank
    @NotEmpty
    @NotNull
    //@Size(min = 1, max = 9)
    @Pattern(regexp = "\\d{5}-\\d{3}")
    @ApiModelProperty(name = "O cep deve ser informado no seguinte formato: 00000-000", required = true)
    private String cep;


}
