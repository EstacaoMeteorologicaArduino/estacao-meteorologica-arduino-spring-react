package br.com.estacaometeorologica.model.estacaometeorologica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EstacaoMeteorologicaService {

    void incluirInformacaoMeteorologica(InformacaoMeteorologica informacaoMeteorologica);

    void incluirAgrupamentoDadosMeteorologicos(AgrupamentoDadosMeteorologicos agrupamentoDadosMeteorologicos);

    List<InformacaoMeteorologica> buscarTodos();

    InformacaoMeteorologica buscarUltimaInformacaoMeteorologicaPorData(LocalDateTime data);

    List<InformacaoMeteorologica> buscarTodosPorData(LocalDateTime dataInicial, LocalDateTime dataFinal);

    List<AgrupamentoDadosMeteorologicos> buscarTodosAgrupamentosPorData(LocalDate data);

    Double buscarTemperaturaMaxima(LocalDateTime dataInicialConsulta, LocalDateTime dataFinalConsulta);

    Double buscarTemperaturaMinima(LocalDateTime dataInicialConsulta, LocalDateTime dataFinalConsulta);

    Double buscarUmidadeMaxima(LocalDateTime dataInicialConsulta, LocalDateTime dataFinalConsulta);

    Double buscarUmidadeMinima(LocalDateTime dataInicialConsulta, LocalDateTime dataFinalConsulta);

    Double buscarPressaoMaxima(LocalDateTime dataInicialConsulta, LocalDateTime dataFinalConsulta);

    Double buscarPressaoMinima(LocalDateTime dataInicialConsulta, LocalDateTime dataFinalConsulta);
}