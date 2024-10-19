package br.com.estacaometeorologica.model.estacaometeorologica;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ConsultaResumoDiario {

    LocalDate data;

    Double altitude;

    ResumoDiario dados;
}