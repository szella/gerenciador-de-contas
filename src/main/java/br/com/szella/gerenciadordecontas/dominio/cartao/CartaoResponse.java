package br.com.szella.gerenciadordecontas.dominio.cartao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartaoResponse {
    private Long id;
    private String nome;
}
