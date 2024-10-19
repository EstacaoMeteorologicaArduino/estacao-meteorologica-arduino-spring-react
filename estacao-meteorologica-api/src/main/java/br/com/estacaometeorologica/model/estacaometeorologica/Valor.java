package br.com.estacaometeorologica.model.estacaometeorologica;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Valor {

    Integer hora;

    Double valor;
}