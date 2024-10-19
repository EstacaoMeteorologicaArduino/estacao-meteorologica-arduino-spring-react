package br.com.estacaometeorologica.model.estacaometeorologica;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class AgrupamentoDadosMeteorologicos {

    Double temperatura;

    Double umidade;

    Double pressao;

    Double altitude;

    Double chuva;

    LocalDate dataReferencia;

    Integer horaReferencia;
}