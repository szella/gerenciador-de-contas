package br.com.szella.gerenciadordecontas.mapper;

import br.com.szella.gerenciadordecontas.model.entity.CompraCartaoEntity;
import br.com.szella.gerenciadordecontas.model.request.CompraCartaoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.CompraCartaoSalvarRequest;
import br.com.szella.gerenciadordecontas.model.response.CompraCartaoResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CompraCartaoMapper {
    public static CompraCartaoEntity mapEntity(CompraCartaoSalvarRequest request) {
        return CompraCartaoEntity.builder()
                .nome(request.getNome())
                .valor(request.getValor())
                .mes(request.getMes())
                .ano(request.getAno())
                .build();
    }

    public static void mapAtualizacao(CompraCartaoEditarRequest novo, CompraCartaoEntity atual) {
        atual.setNome(novo.getNome());
        atual.setValor(novo.getValor());
        atual.setMes(novo.getMes());
        atual.setAno(novo.getAno());
    }

    public static CompraCartaoResponse mapResponse(CompraCartaoEntity entity) {
        return CompraCartaoResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .valor(entity.getValor())
                .mes(entity.getMes())
                .ano(entity.getAno())
                .cartao(CartaoMapper.mapResponse(entity.getCartao()))
                .build();
    }

    public static List<CompraCartaoResponse> mapListaResponse(List<CompraCartaoEntity> entities) {
        return entities.stream()
                .map(CompraCartaoMapper::mapResponse)
                .collect(Collectors.toList());
    }
}
