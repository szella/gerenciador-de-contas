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
    public static CartaoEntity mapEntity(CartaoSalvarRequest request) {
        return CartaoEntity.builder()
                .nome(request.getNome())
                .build();
    }

    public static void mapAtualizacao(CartaoEditarRequest novo, CartaoEntity atual) {
        atual.setNome(novo.getNome());
    }

    public static CartaoResponse mapResponse(CartaoEntity entity) {
        return CartaoResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .build();
    }

    public static List<CartaoResponse> mapListaResponse(List<CartaoEntity> entities) {
        return entities.stream()
                .map(CartaoMapper::mapResponse)
                .collect(Collectors.toList());
    }
}
