package br.unipar.consultorio.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiErrorMessage {
    private List<String> errors;

    public ApiErrorMessage(String error){
        this.errors = Arrays.asList(error);
    }
}
