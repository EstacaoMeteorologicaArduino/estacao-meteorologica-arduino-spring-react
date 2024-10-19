package br.com.estacaometeorologica.adapter.primary.http.estacaometeorologica;

import br.com.estacaometeorologica.model.estacaometeorologica.InformacaoMeteorologica;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
interface InformacaoMeteorologicaDtoMapper {

    InformacaoMeteorologica mapToInformacaoMeteorologica(InformacaoMeteorologicaDto informacaoMeteorologicaDto);

    List<InformacaoMeteorologicaDto> mapToListDto(List<InformacaoMeteorologica> informacoesMeteorologicas);
}
