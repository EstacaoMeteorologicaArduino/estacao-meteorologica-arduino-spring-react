package br.com.estacaometeorologica.model.error;

public class RequestException extends RuntimeException {

    public RequestException(String message) {

        super(message);
    }
}
