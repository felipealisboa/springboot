package br.com.scc4.tms.service.exception;

import org.springframework.http.HttpStatus;

public class RegistroNaoEncontradoException extends BusinessException {

    public RegistroNaoEncontradoException(String code) {
        super(code, HttpStatus.NOT_FOUND);
    }
}
