package br.com.szella.gerenciadordecontas.model.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CompraCartaoResponse {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer mes;
    private Integer ano;
    private CartaoResponse cartao;
}
