package br.com.estacaometeorologica.adapter.secundary.persistence.estacaometeorologica;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Value
@Builder
@Document(collection = "agrupamento-dados-meteorologicos")
public class AgrupamentoDadosMeteorologicosDocument {

    @Id
    String id;

    Double temperatura;

    Double umidade;

    Double pressao;

    Double altitude;

    Double chuva;

    LocalDate dataReferencia;

    Integer horaReferencia;
}