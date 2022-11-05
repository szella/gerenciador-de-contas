package br.com.szella.gerenciadordecontas.model.request;

import br.com.szella.gerenciadordecontas.enums.TipoDespesaEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DespesaSalvarRequest {
    private String nome;
    private BigDecimal valor;
    private Integer mes;
    private Integer ano;
    private TipoDespesaEnum tipoDespesaEnum;
    private Long idCartao;
}
