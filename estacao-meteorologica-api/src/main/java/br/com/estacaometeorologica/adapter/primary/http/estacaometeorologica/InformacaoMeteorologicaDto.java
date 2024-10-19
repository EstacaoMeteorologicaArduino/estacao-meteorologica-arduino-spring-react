package br.com.estacaometeorologica.adapter.primary.http.estacaometeorologica;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
class InformacaoMeteorologicaDto {

    Double temperatura;

    Double umidade;

    Double pressao;

    Double altitude;

    Double chuva;

    LocalDateTime dataLeitura;
}