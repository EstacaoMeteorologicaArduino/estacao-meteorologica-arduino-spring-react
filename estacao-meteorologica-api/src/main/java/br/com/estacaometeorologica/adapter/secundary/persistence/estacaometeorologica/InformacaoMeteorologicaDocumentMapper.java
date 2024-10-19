package br.com.estacaometeorologica.adapter.secundary.persistence.estacaometeorologica;

import br.com.estacaometeorologica.model.estacaometeorologica.InformacaoMeteorologica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
interface InformacaoMeteorologicaDocumentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataLeitura", expression = "java(LocalDateTime.now())")
    InformacaoMeteorologicaDocument mapToInformacaoMeteorologicaDocument(InformacaoMeteorologica informacaoMeteorologica);

    List<InformacaoMeteorologica> mapToListInformacaoMeteorologica(List<InformacaoMeteorologicaDocument> documents);

    InformacaoMeteorologica mapToInformacaoMeteorologica(InformacaoMeteorologicaDocument document);
}