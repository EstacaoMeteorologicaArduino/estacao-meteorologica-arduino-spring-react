package br.com.estacaometeorologica.adapter.primary.http.estacaometeorologica;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class MostradorDto {

    String texto;

    Double valor;
}