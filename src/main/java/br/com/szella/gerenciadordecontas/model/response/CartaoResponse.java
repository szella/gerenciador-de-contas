package br.com.szella.gerenciadordecontas.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartaoResponse {
    private Long id;
    private String nome;
}
