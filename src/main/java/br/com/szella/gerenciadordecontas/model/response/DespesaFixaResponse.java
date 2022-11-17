package br.com.szella.gerenciadordecontas.model.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DespesaFixaResponse {
    private Long id;
    private BigDecimal valor;
    private Integer mes;
    private Integer ano;
    private DespesaResponse despesa;
}
