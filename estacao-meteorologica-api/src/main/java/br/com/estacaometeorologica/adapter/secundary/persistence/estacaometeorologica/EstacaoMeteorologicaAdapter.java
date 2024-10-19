package br.com.estacaometeorologica.adapter.secundary.persistence.estacaometeorologica;

import br.com.estacaometeorologica.model.estacaometeorologica.AgrupamentoDadosMeteorologicos;
import br.com.estacaometeorologica.model.estacaometeorologica.EstacaoMeteorologicaService;
import br.com.estacaometeorologica.model.estacaometeorologica.InformacaoMeteorologica;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static br.com.estacaometeorologica.model.util.Logger.logDebug;
import static java.util.Objects.isNull;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.domain.Sort.by;

@Component
@RequiredArgsConstructor
class EstacaoMeteorologicaAdapter implements EstacaoMeteorologicaService {

    private final InformacaoMeteorologicaRepository informacaoMeteorologicaRepository;
    private final AgrupamentoDadosMeteorologicosRepository agrupamentoDadosMeteorologicosRepository;
    private final InformacaoMeteorologicaDocumentMapper informacaoMeteorologicaDocumentMapper;
    private final AgrupamentoDadosMeteorologicosDocumentMapper agrupamentoDadosMeteorologicosDocumentMapper;

    @Override
    public void incluirInformacaoMeteorologica(InformacaoMeteorologica informacaoMeteorologica) {

        var informacaoMeteorologicaDocument = informacaoMeteorologicaDocumentMapper.mapToInformacaoMeteorologicaDocument(informacaoMeteorologica);

        logDebug("Incluindo registro sobre informações meteorológicas. Dados: {}", informacaoMeteorologicaDocument);

        informacaoMeteorologicaRepository.save(informacaoMeteorologicaDocument);
    }

    @Override
    public void incluirAgrupamentoDadosMeteorologicos(AgrupamentoDadosMeteorologicos agrupamentoDadosMeteorologicos) {

        var agrupamentoDadosMeteorologicosDocument = agrupamentoDadosMeteorologicosDocumentMapper
                .maptoAgrupamentoDadosMeteorologicosDocument(agrupamentoDadosMeteorologicos);

        logDebug("Incluindo registro sobre agrupamento de dados meteorológicos. Dados: {}", agrupamentoDadosMeteorologicosDocument);

        agrupamentoDadosMeteorologicosRepository.save(agrupamentoDadosMeteorologicosDocument);
    }

    @Override
    public List<InformacaoMeteorologica> buscarTodos() {

        return informacaoMeteorologicaDocumentMapper.mapToListInformacaoMeteorologica(
                informacaoMeteorologicaRepository.findAll(by(DESC, "dataLeitura")));
    }

    @Override
    public InformacaoMeteorologica buscarUltimaInformacaoMeteorologicaPorData(LocalDateTime data) {

        return informacaoMeteorologicaDocumentMapper.mapToInformacaoMeteorologica(
                informacaoMeteorologicaRepository.findFirstByDataLeituraAfterOrderByDataLeituraDesc(data));
    }

    @Override
    public List<InformacaoMeteorologica> buscarTodosPorData(LocalDateTime dataInicial, LocalDateTime dataFinal) {

        return informacaoMeteorologicaDocumentMapper.mapToListInformacaoMeteorologica(
                informacaoMeteorologicaRepository.findAllByDataLeituraBetween(dataInicial, dataFinal));
    }

    @Override
    public List<AgrupamentoDadosMeteorologicos> buscarTodosAgrupamentosPorData(LocalDate data) {

        return agrupamentoDadosMeteorologicosDocumentMapper.mapToListAgrupamentoDadosMeteorologicos(
                agrupamentoDadosMeteorologicosRepository.findAllByDataReferencia(data));
    }

    @Override
    public Double buscarTemperaturaMaxima(LocalDateTime dataInicialConsulta, LocalDateTime dataFinalConsulta) {

        var document = informacaoMeteorologicaRepository
                .findFirstByDataLeituraBetweenOrderByTemperaturaDesc(dataInicialConsulta, dataFinalConsulta);

        if (isNull(document)) {

            return null;
        }
        return document.getTemperatura();
    }

    @Override
    public Double buscarTemperaturaMinima(LocalDateTime dataInicialConsulta, LocalDateTime dataFinalConsulta) {

        var document = informacaoMeteorologicaRepository
                .findFirstByDataLeituraBetweenOrderByTemperaturaAsc(dataInicialConsulta, dataFinalConsulta);

        if (isNull(document)) {

            return null;
        }
        return document.getTemperatura();
    }

    @Override
    public Double buscarUmidadeMaxima(LocalDateTime dataInicialConsulta, LocalDateTime dataFinalConsulta) {

        var document = informacaoMeteorologicaRepository
                .findFirstByDataLeituraBetweenOrderByUmidadeDesc(dataInicialConsulta, dataFinalConsulta);

        if (isNull(document)) {

            return null;
        }
        return document.getUmidade();
    }

    @Override
    public Double buscarUmidadeMinima(LocalDateTime dataInicialConsulta, LocalDateTime dataFinalConsulta) {

        var document = informacaoMeteorologicaRepository
                .findFirstByDataLeituraBetweenOrderByUmidadeAsc(dataInicialConsulta, dataFinalConsulta);

        if (isNull(document)) {

            return null;
        }
        return document.getUmidade();
    }

    @Override
    public Double buscarPressaoMaxima(LocalDateTime dataInicialConsulta, LocalDateTime dataFinalConsulta) {

        var document = informacaoMeteorologicaRepository
                .findFirstByDataLeituraBetweenOrderByPressaoDesc(dataInicialConsulta, dataFinalConsulta);

        if (isNull(document)) {

            return null;
        }
        return document.getPressao();
    }

    @Override
    public Double buscarPressaoMinima(LocalDateTime dataInicialConsulta, LocalDateTime dataFinalConsulta) {

        var document = informacaoMeteorologicaRepository
                .findFirstByDataLeituraBetweenOrderByPressaoAsc(dataInicialConsulta, dataFinalConsulta);

        if (isNull(document)) {

            return null;
        }
        return document.getPressao();
    }
}