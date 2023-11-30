package br.com.szella.gerenciadordecontas.dominio.despesaFixa;

import lombok.Data;

import java.math.BigDecimal;
import java.time.YearMonth;

@Data
public class DespesaFixaEditarRequest {
    private BigDecimal valor;
    private YearMonth anoMes;
    private Long idDespesa;
}
