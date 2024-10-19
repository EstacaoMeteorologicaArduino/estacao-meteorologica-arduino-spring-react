package br.com.estacaometeorologica.adapter.secundary.persistence.estacaometeorologica;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
interface InformacaoMeteorologicaRepository extends MongoRepository<InformacaoMeteorologicaDocument, String> {

    List<InformacaoMeteorologicaDocument> findAllByDataLeituraBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

    InformacaoMeteorologicaDocument findFirstByDataLeituraAfterOrderByDataLeituraDesc(LocalDateTime dataLeitura);

    InformacaoMeteorologicaDocument findFirstByDataLeituraBetweenOrderByTemperaturaDesc(LocalDateTime dataInicial, LocalDateTime dataFinal);

    InformacaoMeteorologicaDocument findFirstByDataLeituraBetweenOrderByTemperaturaAsc(LocalDateTime dataInicial, LocalDateTime dataFinal);

    InformacaoMeteorologicaDocument findFirstByDataLeituraBetweenOrderByUmidadeDesc(LocalDateTime dataInicial, LocalDateTime dataFinal);

    InformacaoMeteorologicaDocument findFirstByDataLeituraBetweenOrderByUmidadeAsc(LocalDateTime dataInicial, LocalDateTime dataFinal);

    InformacaoMeteorologicaDocument findFirstByDataLeituraBetweenOrderByPressaoDesc(LocalDateTime dataInicial, LocalDateTime dataFinal);

    InformacaoMeteorologicaDocument findFirstByDataLeituraBetweenOrderByPressaoAsc(LocalDateTime dataInicial, LocalDateTime dataFinal);
}