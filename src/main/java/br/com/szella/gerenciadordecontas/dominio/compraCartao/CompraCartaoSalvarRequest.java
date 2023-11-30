package br.com.szella.gerenciadordecontas.dominio.compraCartao;

import lombok.Data;

import java.math.BigDecimal;
import java.time.YearMonth;

@Data
public class CompraCartaoSalvarRequest {
    private String nome;
    private BigDecimal valor;
    private YearMonth anoMes;
    private Long idCartao;
    private Integer parcelas;
}
