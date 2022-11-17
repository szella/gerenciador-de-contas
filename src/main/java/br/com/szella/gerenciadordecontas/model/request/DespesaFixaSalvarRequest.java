package br.com.szella.gerenciadordecontas.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DespesaFixaSalvarRequest {
    private BigDecimal valor;
    private Integer mes;
    private Integer ano;
    private Long idDespesa;
    private Integer periodo;
}
