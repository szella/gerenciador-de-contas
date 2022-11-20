package br.com.szella.gerenciadordecontas.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GastosPorMesResponse {
    private Integer mes;
    List<CompraCartaoResponse> comprasCartao;
    List<DespesaFixaResponse> despesasFixa;
}
