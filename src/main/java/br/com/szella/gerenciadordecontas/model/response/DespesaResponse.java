package br.com.szella.gerenciadordecontas.model.response;

import br.com.szella.gerenciadordecontas.enums.TipoDespesaEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DespesaResponse {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer mes;
    private Integer ano;
    private TipoDespesaEnum tipoDespesaEnum;
    private CartaoResponse cartao;
}
