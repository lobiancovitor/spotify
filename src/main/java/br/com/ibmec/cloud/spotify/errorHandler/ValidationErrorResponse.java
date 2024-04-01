package br.com.ibmec.cloud.spotify.errorHandler;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorResponse {
    public String message = "Existem erros na sua requisição";
    public List<Validation> validationErrors = new ArrayList<>();
}
