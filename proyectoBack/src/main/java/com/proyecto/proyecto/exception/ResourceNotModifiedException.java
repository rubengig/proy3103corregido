package com.proyecto.proyecto.exception;

public class ResourceNotModifiedException extends RuntimeException {
    public ResourceNotModifiedException(String mensaje) {
        super(mensaje);
    }
}
