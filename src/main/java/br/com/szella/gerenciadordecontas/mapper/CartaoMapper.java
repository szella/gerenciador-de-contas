package br.com.szella.gerenciadordecontas.mapper;

import br.com.szella.gerenciadordecontas.model.entity.Cartao;
import br.com.szella.gerenciadordecontas.model.request.CartaoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.CartaoSalvarRequest;
import br.com.szella.gerenciadordecontas.model.response.CartaoResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CartaoMapper {
    public static Cartao mapCartaoSalvar(CartaoSalvarRequest request) {
        return Cartao.builder()
                .nome(request.getNome())
                .build();
    }

    public static void mapCartaoEditar(CartaoEditarRequest novo, Cartao atual) {
        atual.setNome(novo.getNome());
    }

    public static CartaoResponse mapCartaoResponse(Cartao cartao) {
        return CartaoResponse.builder()
                .id(cartao.getId())
                .nome(cartao.getNome())
                .build();
    }

    public static List<CartaoResponse> mapListCartaoResponse(List<Cartao> cartoes) {
        return cartoes.stream()
                .map(cartao ->
                        CartaoResponse.builder()
                                .id(cartao.getId())
                                .nome(cartao.getNome())
                                .build()).collect(Collectors.toList());
    }
}
