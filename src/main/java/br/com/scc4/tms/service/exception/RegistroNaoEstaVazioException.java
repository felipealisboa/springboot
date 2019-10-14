package br.com.scc4.tms.service.exception;

import org.springframework.http.HttpStatus;

public class RegistroNaoEstaVazioException extends BusinessException {

    public RegistroNaoEstaVazioException(String code) {
        super(code, HttpStatus.valueOf("Existem registros cadastrados"));
    }
}
