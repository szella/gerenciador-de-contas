package br.com.szella.gerenciadordecontas.dominio.despesa;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DespesaResponse {
    private Long id;
    private String nome;
}
