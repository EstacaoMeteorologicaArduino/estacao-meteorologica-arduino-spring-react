package br.com.estacaometeorologica.model.estacaometeorologica;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResumoDiario {

    Temperatura temperatura;

    VolumeChuva volumeChuva;

    Umidade umidade;

    Pressao pressao;
}