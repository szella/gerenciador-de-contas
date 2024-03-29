package br.com.szella.gerenciadordecontas.dominio.recebimento;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecebimentoEditarRequest {
    private String nome;
    private BigDecimal valor;
    private Integer mes;
    private Integer ano;
}
