package br.com.letscode.clientes.exceptions;

import org.aspectj.weaver.ast.Not;

public class NotFoundException extends Exception {
    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}
