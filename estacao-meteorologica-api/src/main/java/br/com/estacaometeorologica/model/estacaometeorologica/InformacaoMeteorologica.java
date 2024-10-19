package br.com.estacaometeorologica.model.estacaometeorologica;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InformacaoMeteorologica {

    Double temperatura;

    Double umidade;

    Double pressao;

    Double altitude;

    Double chuva;

    LocalDateTime dataLeitura;
}