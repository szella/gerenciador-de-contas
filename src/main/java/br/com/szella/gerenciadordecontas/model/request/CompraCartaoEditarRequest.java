package br.com.szella.gerenciadordecontas.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CompraCartaoEditarRequest {
    private String nome;
    private BigDecimal valor;
    private Integer mes;
    private Integer ano;
    private Long idCartao;
}
