package br.com.estacaometeorologica.adapter.error;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class ApiError {

    String mensagem;
}