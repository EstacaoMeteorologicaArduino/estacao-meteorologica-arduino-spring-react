package br.com.estacaometeorologica.adapter.primary.http.estacaometeorologica;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
class PressaoDto {

    String unidade;

    UltimaMedicaoDto ultimaMedicao;

    List<MostradorDto> mostradores;

    List<ValorDto> valores;
}