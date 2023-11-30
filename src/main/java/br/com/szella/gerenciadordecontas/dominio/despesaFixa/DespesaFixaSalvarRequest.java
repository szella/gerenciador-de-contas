package br.com.szella.gerenciadordecontas.dominio.despesaFixa;

import lombok.Data;

import java.math.BigDecimal;
import java.time.YearMonth;

@Data
public class DespesaFixaSalvarRequest {
    private BigDecimal valor;
    private YearMonth anoMes;
    private Long idDespesa;
    private Integer periodo;
}
