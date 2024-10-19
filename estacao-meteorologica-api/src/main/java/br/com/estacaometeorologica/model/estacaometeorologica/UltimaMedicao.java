package br.com.estacaometeorologica.model.estacaometeorologica;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class UltimaMedicao {

    Double valor;

    LocalDateTime horario;
}