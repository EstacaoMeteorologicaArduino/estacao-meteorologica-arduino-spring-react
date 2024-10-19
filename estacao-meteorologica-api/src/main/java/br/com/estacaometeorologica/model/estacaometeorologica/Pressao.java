package br.com.estacaometeorologica.model.estacaometeorologica;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Pressao {

    String unidade;

    UltimaMedicao ultimaMedicao;

    List<Mostrador> mostradores;

    List<Valor> valores;
}