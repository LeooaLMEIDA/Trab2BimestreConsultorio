package br.unipar.consultorio.enums;


import io.swagger.annotations.ApiOperation;

@ApiOperation("Classe ENUM para os Motivos de Cancelamento das Consultas")
public enum MotivoCancelConsultENUM {
    PACIENTE_DESISTIU,
    MEDICO_CANCELOU,
    OUTROS;
}
