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

    public static CompraCartaoResponse mapResponse(CompraCartaoEntity compraCartao) {
        return CompraCartaoResponse.builder()
                .id(compraCartao.getId())
                .nome(compraCartao.getNome())
                .valor(compraCartao.getValor())
                .mes(compraCartao.getMes())
                .ano(compraCartao.getAno())
                .cartao(CartaoMapper.mapResponse(compraCartao.getCartao()))
                .build();
    }

    public static List<CompraCartaoResponse> mapListaResponse(List<CompraCartaoEntity> compraCartaoList) {
        return compraCartaoList.stream()
                .map(CompraCartaoMapper::mapResponse)
                .collect(Collectors.toList());
    }
}
