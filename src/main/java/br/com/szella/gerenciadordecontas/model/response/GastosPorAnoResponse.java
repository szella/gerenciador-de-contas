package br.com.szella.gerenciadordecontas.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GastosPorAnoResponse {
    private Integer ano;
    List<GastosPorMesResponse> meses;
}
