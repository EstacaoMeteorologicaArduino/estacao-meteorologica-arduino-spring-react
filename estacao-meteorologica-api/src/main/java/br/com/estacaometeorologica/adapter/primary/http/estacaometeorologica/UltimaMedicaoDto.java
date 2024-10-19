package br.com.estacaometeorologica.adapter.primary.http.estacaometeorologica;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
class UltimaMedicaoDto {

    Double valor;

    LocalDateTime horario;
}