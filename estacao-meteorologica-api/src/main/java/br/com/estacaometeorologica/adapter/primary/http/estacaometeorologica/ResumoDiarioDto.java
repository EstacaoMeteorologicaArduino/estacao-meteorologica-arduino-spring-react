package br.com.estacaometeorologica.adapter.primary.http.estacaometeorologica;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Value
@Builder
@JsonInclude(NON_NULL)
class ResumoDiarioDto {

    TemperaturaDto temperatura;

    UmidadeDto umidade;

    PressaoDto pressao;

    VolumeChuvaDto volumeChuva;
}