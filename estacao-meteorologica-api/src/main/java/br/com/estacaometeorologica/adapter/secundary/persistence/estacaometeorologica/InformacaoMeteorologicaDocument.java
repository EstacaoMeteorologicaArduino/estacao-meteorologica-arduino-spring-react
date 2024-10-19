package br.com.estacaometeorologica.adapter.secundary.persistence.estacaometeorologica;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Value
@Builder
@Document(collection = "informacoes-meteorologicas")
public class InformacaoMeteorologicaDocument {

    @Id
    String id;

    Double temperatura;

    Double umidade;

    Double pressao;

    Double altitude;

    Double chuva;

    LocalDateTime dataLeitura;
}