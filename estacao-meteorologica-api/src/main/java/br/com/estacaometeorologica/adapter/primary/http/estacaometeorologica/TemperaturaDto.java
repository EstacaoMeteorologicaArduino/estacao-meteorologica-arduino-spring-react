package br.com.estacaometeorologica.adapter.primary.http.estacaometeorologica;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
class TemperaturaDto {

    String unidade;

    UltimaMedicaoDto ultimaMedicao;

    List<MostradorDto> mostradores;

    List<ValorDto> valores;
}