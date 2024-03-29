package br.com.szella.gerenciadordecontas.dominio.recebimento;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecebimentoSalvarRequest {
    private String nome;
    private BigDecimal valor;
    private Integer mes;
    private Integer ano;
    private Integer periodo;
}
