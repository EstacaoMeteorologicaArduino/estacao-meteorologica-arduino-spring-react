package br.com.estacaometeorologica.adapter.primary.http.scheduler;

import br.com.estacaometeorologica.application.EstacaoMeteorologicaApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RotinaAgrupamentoDadosScheduler {

    private final EstacaoMeteorologicaApplication estacaoMeteorologicaApplication;

    @Scheduled(cron = "10 0 * * * *")
    public void agendamentoRotinaAgrupamentoDados() {

        estacaoMeteorologicaApplication.agrupamentoDados();
    }
}