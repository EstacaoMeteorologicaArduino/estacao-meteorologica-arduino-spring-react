package br.com.estacaometeorologica.model.estacaometeorologica;

import br.com.estacaometeorologica.model.error.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;
import static java.time.LocalTime.MAX;
import static java.time.LocalTime.MIN;
import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class EstacaoMeteorologicaHandler {

    private final EstacaoMeteorologicaService estacaoMeteorologicaService;

    public void incluir(InformacaoMeteorologica informacaoMeteorologica) {

        estacaoMeteorologicaService.incluirInformacaoMeteorologica(informacaoMeteorologica);
    }

    public List<InformacaoMeteorologica> buscarTodos() {

        return estacaoMeteorologicaService.buscarTodos();
    }

    public void agrupamentoDados() {

        var dataProcessamento = now(); //Data de referência da execução da rotina
        var dataReferencia = of(dataProcessamento.toLocalDate(), LocalTime.of(dataProcessamento.getHour(), 0, 0)); //Data para salvar no banco
        var dataInicialConsulta = dataReferencia.minusHours(1); //Data inicial da consulta
        var dataFinalConsulta = dataInicialConsulta.plusMinutes(59).plusSeconds(59); //Data final da consulta

        var informacoesMeteorologicas = estacaoMeteorologicaService.buscarTodosPorData(dataInicialConsulta, dataFinalConsulta);

        if (!isEmpty(informacoesMeteorologicas)) {

            var agrupamentoDadosMeteorologicos = AgrupamentoDadosMeteorologicos.builder()
                    .temperatura(calcularMediaTemperatura(informacoesMeteorologicas))
                    .umidade(calcularMediaUmidade(informacoesMeteorologicas))
                    .pressao(calcularMediaPressao(informacoesMeteorologicas))
                    .chuva(calcularSomatorioChuva(informacoesMeteorologicas))
                    .altitude(informacoesMeteorologicas.get(0).getAltitude())
                    .dataReferencia(dataReferencia.toLocalDate())
                    .horaReferencia(dataReferencia.getHour())
                    .build();

            estacaoMeteorologicaService.incluirAgrupamentoDadosMeteorologicos(agrupamentoDadosMeteorologicos);
        }
    }

    private static double calcularMediaTemperatura(List<InformacaoMeteorologica> informacoesMeteorologicas) {

        return (informacoesMeteorologicas.stream()
                .map(InformacaoMeteorologica::getTemperatura)
                .mapToDouble(d -> d)
                .filter(Objects::nonNull)
                .sum()) / informacoesMeteorologicas.size();
    }


    private static double calcularMediaPressao(List<InformacaoMeteorologica> informacoesMeteorologicas) {

        return (informacoesMeteorologicas.stream()
                .map(InformacaoMeteorologica::getPressao)
                .mapToDouble(d -> d)
                .filter(Objects::nonNull)
                .sum()) / informacoesMeteorologicas.size();
    }

    private static double calcularMediaUmidade(List<InformacaoMeteorologica> informacoesMeteorologicas) {

        return (informacoesMeteorologicas.stream()
                .map(InformacaoMeteorologica::getUmidade)
                .mapToDouble(d -> d)
                .filter(Objects::nonNull)
                .sum()) / informacoesMeteorologicas.size();
    }

    private static double calcularSomatorioChuva(List<InformacaoMeteorologica> informacoesMeteorologicas) {

        return informacoesMeteorologicas.stream()
                .map(InformacaoMeteorologica::getChuva)
                .mapToDouble(d -> d)
                .filter(Objects::nonNull)
                .sum();
    }


    public ConsultaResumoDiario buscarResumoDiario(LocalDate data) {

        var agrupamentos = estacaoMeteorologicaService.buscarTodosAgrupamentosPorData(data);

        if (isEmpty(agrupamentos)) {

            throw new RequestException("Sem dados meteorológicos para esta data");
        }

        InformacaoMeteorologica ultimaMedicao = null;

        if (LocalDate.now().equals(data)) {

            ultimaMedicao = estacaoMeteorologicaService.buscarUltimaInformacaoMeteorologicaPorData(LocalDateTime.of(data, MIN));
        }

        var dataInicialConsulta = LocalDateTime.of(data, MIN);
        var dataFinalConsulta = LocalDateTime.of(data, MAX);

        var resumoDiario = ResumoDiario.builder()
                .temperatura(getTemperatura(agrupamentos, ultimaMedicao, dataInicialConsulta, dataFinalConsulta))
                .volumeChuva(getVolumeChuva(agrupamentos, ultimaMedicao))
                .umidade(getUmidade(agrupamentos, ultimaMedicao, dataInicialConsulta, dataFinalConsulta))
                .pressao(getPressao(agrupamentos, ultimaMedicao, dataInicialConsulta, dataFinalConsulta))
                .build();

        return ConsultaResumoDiario.builder()
                .data(data)
                .altitude(agrupamentos.get(0).getAltitude())
                .dados(resumoDiario)
                .build();
    }

    private Temperatura getTemperatura(List<AgrupamentoDadosMeteorologicos> agrupamentos,
                                       InformacaoMeteorologica informacaoMeteorologica,
                                       LocalDateTime dataInicialConsulta, LocalDateTime dataFinalConsulta) {

        UltimaMedicao ultimaMedicao = null;

        if (nonNull(informacaoMeteorologica)) {

            ultimaMedicao = UltimaMedicao.builder()
                    .valor(informacaoMeteorologica.getTemperatura())
                    .horario(informacaoMeteorologica.getDataLeitura())
                    .build();
        }

        var valores = agrupamentos.stream()
                .map(EstacaoMeteorologicaHandler::getValorTemperatura)
                .toList();

        var mostradorMaximo = Mostrador.builder()
                .texto("Máxima")
                .valor(estacaoMeteorologicaService.buscarTemperaturaMaxima(dataInicialConsulta, dataFinalConsulta))
                .build();

        var mostradorMinimo = Mostrador.builder()
                .texto("Mínima")
                .valor(estacaoMeteorologicaService.buscarTemperaturaMinima(dataInicialConsulta, dataFinalConsulta))
                .build();

        return Temperatura.builder()
                .unidade("ºC")
                .ultimaMedicao(ultimaMedicao)
                .mostradores(List.of(mostradorMaximo, mostradorMinimo))
                .valores(valores)
                .build();
    }

    private static VolumeChuva getVolumeChuva(List<AgrupamentoDadosMeteorologicos> agrupamentos, InformacaoMeteorologica informacaoMeteorologica) {

        UltimaMedicao ultimaMedicao = null;

        if (nonNull(informacaoMeteorologica)) {

            ultimaMedicao = UltimaMedicao.builder()
                    .valor(informacaoMeteorologica.getChuva())
                    .horario(informacaoMeteorologica.getDataLeitura())
                    .build();
        }

        var valores = agrupamentos.stream()
                .map(EstacaoMeteorologicaHandler::getValorVolumeChuva)
                .toList();

        var mostradorAcumulado = Mostrador.builder()
                .texto("Acumulado")
                .valor(getValorAcumulado(valores))
                .build();

        return VolumeChuva.builder()
                .unidade("mm")
                .ultimaMedicao(ultimaMedicao)
                .mostradores(List.of(mostradorAcumulado))
                .valores(valores)
                .build();
    }

    private Umidade getUmidade(List<AgrupamentoDadosMeteorologicos> agrupamentos,
                               InformacaoMeteorologica informacaoMeteorologica,
                               LocalDateTime dataInicialConsulta, LocalDateTime dataFinalConsulta) {

        UltimaMedicao ultimaMedicao = null;

        if (nonNull(informacaoMeteorologica)) {

            ultimaMedicao = UltimaMedicao.builder()
                    .valor(informacaoMeteorologica.getUmidade())
                    .horario(informacaoMeteorologica.getDataLeitura())
                    .build();
        }

        var valores = agrupamentos.stream()
                .map(EstacaoMeteorologicaHandler::getValorUmidade)
                .toList();

        var mostradorMaximo = Mostrador.builder()
                .texto("Máxima")
                .valor(estacaoMeteorologicaService.buscarUmidadeMaxima(dataInicialConsulta, dataFinalConsulta))
                .build();

        var mostradorMinimo = Mostrador.builder()
                .texto("Mínima")
                .valor(estacaoMeteorologicaService.buscarUmidadeMinima(dataInicialConsulta, dataFinalConsulta))
                .build();

        return Umidade.builder()
                .unidade("%")
                .ultimaMedicao(ultimaMedicao)
                .mostradores(List.of(mostradorMaximo, mostradorMinimo))
                .valores(valores)
                .build();
    }

    private Pressao getPressao(List<AgrupamentoDadosMeteorologicos> agrupamentos,
                               InformacaoMeteorologica informacaoMeteorologica,
                               LocalDateTime dataInicialConsulta, LocalDateTime dataFinalConsulta) {

        UltimaMedicao ultimaMedicao = null;

        if (nonNull(informacaoMeteorologica)) {

            ultimaMedicao = UltimaMedicao.builder()
                    .valor(informacaoMeteorologica.getPressao())
                    .horario(informacaoMeteorologica.getDataLeitura())
                    .build();
        }

        var valores = agrupamentos.stream()
                .map(EstacaoMeteorologicaHandler::getValorPressao)
                .toList();

        var mostradorMaximo = Mostrador.builder()
                .texto("Máxima")
                .valor(estacaoMeteorologicaService.buscarPressaoMaxima(dataInicialConsulta, dataFinalConsulta))
                .build();

        var mostradorMinimo = Mostrador.builder()
                .texto("Mínima")
                .valor(estacaoMeteorologicaService.buscarPressaoMinima(dataInicialConsulta, dataFinalConsulta))
                .build();

        return Pressao.builder()
                .unidade("hPa")
                .ultimaMedicao(ultimaMedicao)
                .mostradores(List.of(mostradorMaximo, mostradorMinimo))
                .valores(valores)
                .build();
    }

    private static Valor getValorTemperatura(AgrupamentoDadosMeteorologicos agrupamento) {

        return Valor.builder()
                .hora(agrupamento.getHoraReferencia())
                .valor(agrupamento.getTemperatura())
                .build();
    }

    private static Valor getValorVolumeChuva(AgrupamentoDadosMeteorologicos agrupamento) {

        return Valor.builder()
                .hora(agrupamento.getHoraReferencia())
                .valor(agrupamento.getChuva())
                .build();
    }

    private static Valor getValorUmidade(AgrupamentoDadosMeteorologicos agrupamento) {

        return Valor.builder()
                .hora(agrupamento.getHoraReferencia())
                .valor(agrupamento.getUmidade())
                .build();
    }

    private static Valor getValorPressao(AgrupamentoDadosMeteorologicos agrupamento) {

        return Valor.builder()
                .hora(agrupamento.getHoraReferencia())
                .valor(agrupamento.getPressao())
                .build();
    }

    private static Double getValorAcumulado(List<Valor> valores) {

        return valores.stream()
                .map(Valor::getValor)
                .filter(Objects::nonNull)
                .mapToDouble(d -> d)
                .sum();
    }
}