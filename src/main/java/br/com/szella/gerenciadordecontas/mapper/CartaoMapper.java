package br.com.szella.gerenciadordecontas.mapper;

import br.com.szella.gerenciadordecontas.model.entity.CartaoEntity;
import br.com.szella.gerenciadordecontas.model.request.CartaoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.CartaoSalvarRequest;
import br.com.szella.gerenciadordecontas.model.response.CartaoResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CartaoMapper {
    public static CartaoEntity mapCartaoSalvar(CartaoSalvarRequest request) {
        return CartaoEntity.builder()
                .nome(request.getNome())
                .build();
    }

    public static void mapCartaoEditar(CartaoEditarRequest novo, CartaoEntity atual) {
        atual.setNome(novo.getNome());
    }

    public static CartaoResponse mapCartaoResponse(CartaoEntity cartao) {
        return CartaoResponse.builder()
                .id(cartao.getId())
                .nome(cartao.getNome())
                .build();
    }

    public static List<CartaoResponse> mapListCartaoResponse(List<CartaoEntity> cartoes) {
        return cartoes.stream()
                .map(CartaoMapper::mapCartaoResponse)
                .collect(Collectors.toList());
    }
}
