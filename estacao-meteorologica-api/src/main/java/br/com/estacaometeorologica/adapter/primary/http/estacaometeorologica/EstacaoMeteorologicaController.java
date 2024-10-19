package br.com.estacaometeorologica.adapter.primary.http.estacaometeorologica;

import br.com.estacaometeorologica.application.EstacaoMeteorologicaApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static br.com.estacaometeorologica.model.util.Logger.logRequestController;
import static br.com.estacaometeorologica.model.util.Logger.logResponseController;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/estacao-meteorologica")
class EstacaoMeteorologicaController {

    private static final String INCLUIR_DADOS_METEOROLOGICOS = "incluir dados meteorológicos";
    private static final String BUSCAR_TODOS_DADOS_METEOROLOGICOS = "buscar todos os dados meteorológicos";
    private static final String BUSCAR_RESUMO_DIARIO = "buscar resumo diário dos dados meteorológicos";

    private final InformacaoMeteorologicaDtoMapper informacaoMeteorologicaDtoMapper;
    private final ConsultaResumoDiarioDtoMapper consultaResumoDiarioDtoMapper;
    private final EstacaoMeteorologicaApplication estacaoMeteorologicaApplication;

    @PostMapping
    @ResponseStatus(CREATED)
    void incluir(@RequestBody InformacaoMeteorologicaDto request) {

        logRequestController(INCLUIR_DADOS_METEOROLOGICOS, request);

        var informacaoMeteorologica = informacaoMeteorologicaDtoMapper.mapToInformacaoMeteorologica(request);
        estacaoMeteorologicaApplication.incluir(informacaoMeteorologica);

        logResponseController(INCLUIR_DADOS_METEOROLOGICOS);
    }

    @GetMapping
    @ResponseStatus(OK)
    List<InformacaoMeteorologicaDto> buscarTodos() {

        logRequestController(BUSCAR_TODOS_DADOS_METEOROLOGICOS);

        var informacoesMeteorologicas = estacaoMeteorologicaApplication.buscarTodos();
        var response = informacaoMeteorologicaDtoMapper.mapToListDto(informacoesMeteorologicas);

        logResponseController(BUSCAR_TODOS_DADOS_METEOROLOGICOS, response);
        return response;
    }

    @GetMapping("resumo-diario")
    @ResponseStatus(OK)
    ConsultaResumoDiarioDto buscarResumoDiario(@RequestParam LocalDate data) {

        logRequestController(BUSCAR_RESUMO_DIARIO);

        var resumoDiario = estacaoMeteorologicaApplication.buscarResumoDiario(data);
        var response = consultaResumoDiarioDtoMapper.mapToConsultaResumoDiarioDto(resumoDiario);

        logResponseController(BUSCAR_TODOS_DADOS_METEOROLOGICOS, response);
        return response;
    }
}