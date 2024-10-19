package br.com.estacaometeorologica.model.estacaometeorologica;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Mostrador {

    String texto;

    Double valor;
}