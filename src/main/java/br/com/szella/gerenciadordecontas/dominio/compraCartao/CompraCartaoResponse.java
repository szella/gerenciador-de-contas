package br.com.szella.gerenciadordecontas.dominio.compraCartao;

import br.com.szella.gerenciadordecontas.dominio.cartao.CartaoResponse;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Data
@Builder
public class CompraCartaoResponse {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private YearMonth anoMes;
    private LocalDateTime dataCadastro;
    private String agrupamento;
    private CartaoResponse cartao;
}
