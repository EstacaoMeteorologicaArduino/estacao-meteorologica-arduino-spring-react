package br.com.estacaometeorologica.model.util;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@NoArgsConstructor(access = PRIVATE)
public class Logger {

    public static <T> void logRequestController(String descricao, T requisicao) {

        log.info("Recebendo requisição para {}. Dados: {}", descricao, requisicao);
    }

    public static void logRequestController(String descricao) {

        log.info("Recebendo requisição para {}", descricao);
    }

    public static void logResponseController(String descricao) {

        log.info("Requisição para {} finalizada com sucesso.", descricao);
    }

    public static <T> void logResponseController(String descricao, T resposta) {

        log.info("Resposta da requisição para {}. Dados: {}", descricao, resposta);
    }

    public static <T> void logDebug(String descricao, T requisicao) {

        log.info(descricao, requisicao);
    }
}
