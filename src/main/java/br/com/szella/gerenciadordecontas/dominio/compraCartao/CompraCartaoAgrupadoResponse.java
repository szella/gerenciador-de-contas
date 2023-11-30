package br.com.szella.gerenciadordecontas.dominio.compraCartao;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@Data
@Builder
public class CompraCartaoAgrupadoResponse {
    private YearMonth anoMes;
    private BigDecimal valorTotal;
    private List<CompraCartaoResponse> comprasCartao;

    public void somarValorTotal(BigDecimal valorSomar) {
        this.valorTotal = this.getValorTotal().add(valorSomar);
    }
}
