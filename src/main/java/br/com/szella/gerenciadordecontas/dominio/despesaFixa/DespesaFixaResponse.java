package br.com.szella.gerenciadordecontas.dominio.despesaFixa;

import br.com.szella.gerenciadordecontas.dominio.despesa.DespesaResponse;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Data
@Builder
public class DespesaFixaResponse {
    private Long id;
    private BigDecimal valor;
    private YearMonth anoMes;
    private LocalDateTime dataCadastro;
    private DespesaResponse despesa;
}
