package br.com.estacaometeorologica.adapter.secundary.persistence.estacaometeorologica;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
interface AgrupamentoDadosMeteorologicosRepository extends MongoRepository<AgrupamentoDadosMeteorologicosDocument, String> {

    List<AgrupamentoDadosMeteorologicosDocument> findAllByDataReferencia(LocalDate data);
}