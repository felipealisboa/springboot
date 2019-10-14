package br.com.scc4.tms.service.exception;

import org.springframework.http.HttpStatus;

public class RegistroJaExisteException extends BusinessException {

    public RegistroJaExisteException(String code) {
        super(code, HttpStatus.BAD_REQUEST);
    }
}
