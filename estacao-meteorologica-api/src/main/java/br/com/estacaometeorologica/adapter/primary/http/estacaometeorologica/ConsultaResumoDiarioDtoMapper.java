package br.com.estacaometeorologica.adapter.primary.http.estacaometeorologica;

import br.com.estacaometeorologica.model.estacaometeorologica.ConsultaResumoDiario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface ConsultaResumoDiarioDtoMapper {

    ConsultaResumoDiarioDto mapToConsultaResumoDiarioDto(ConsultaResumoDiario consultaResumoDiario);
}