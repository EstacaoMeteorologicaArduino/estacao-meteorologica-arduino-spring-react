package br.com.estacaometeorologica.adapter.secundary.persistence.estacaometeorologica;

import br.com.estacaometeorologica.model.estacaometeorologica.AgrupamentoDadosMeteorologicos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
interface AgrupamentoDadosMeteorologicosDocumentMapper {

    @Mapping(target = "id", ignore = true)
    AgrupamentoDadosMeteorologicosDocument maptoAgrupamentoDadosMeteorologicosDocument(
            AgrupamentoDadosMeteorologicos agrupamentoDadosMeteorologicos);

    List<AgrupamentoDadosMeteorologicos> mapToListAgrupamentoDadosMeteorologicos(
            List<AgrupamentoDadosMeteorologicosDocument> agrupamentoDadosMeteorologicosDocuments);
}