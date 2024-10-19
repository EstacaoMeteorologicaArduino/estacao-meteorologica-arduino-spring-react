package br.com.estacaometeorologica.application;

import br.com.estacaometeorologica.model.estacaometeorologica.ConsultaResumoDiario;
import br.com.estacaometeorologica.model.estacaometeorologica.EstacaoMeteorologicaHandler;
import br.com.estacaometeorologica.model.estacaometeorologica.InformacaoMeteorologica;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EstacaoMeteorologicaApplication {

    private final EstacaoMeteorologicaHandler estacaoMeteorologicaHandler;

    public void incluir(InformacaoMeteorologica informacaoMeteorologica) {

        estacaoMeteorologicaHandler.incluir(informacaoMeteorologica);
    }

    public List<InformacaoMeteorologica> buscarTodos() {

        return estacaoMeteorologicaHandler.buscarTodos();
    }

    public void agrupamentoDados() {

        estacaoMeteorologicaHandler.agrupamentoDados();
    }

    public ConsultaResumoDiario buscarResumoDiario(LocalDate data) {

        return estacaoMeteorologicaHandler.buscarResumoDiario(data);
    }
}