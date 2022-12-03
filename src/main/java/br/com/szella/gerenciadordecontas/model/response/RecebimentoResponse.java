package br.com.szella.gerenciadordecontas.model.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class RecebimentoResponse {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer mes;
    private Integer ano;
    private LocalDateTime dataCadastro;
    private String agrupamento;
}
