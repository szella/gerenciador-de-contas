package br.com.szella.gerenciadordecontas.dominio.compraCartao;

import br.com.szella.gerenciadordecontas.dominio.cartao.CartaoMapper;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CompraCartaoMapper {
    public static CompraCartaoEntity mapEntity(CompraCartaoSalvarRequest request) {
        return CompraCartaoEntity.builder()
                .nome(request.getNome())
                .valor(request.getValor())

                .anoMes(request.getAnoMes())
                .build();
    }

    public static void mapAtualizacao(CompraCartaoEditarRequest novo, CompraCartaoEntity atual) {
        atual.setNome(novo.getNome());
        atual.setValor(novo.getValor());
        atual.setAnoMes(novo.getAnoMes());
    }

    public static CompraCartaoResponse mapResponse(CompraCartaoEntity entity) {
        return CompraCartaoResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .valor(entity.getValor())
                                .anoMes(entity.getAnoMes())
                .dataCadastro(entity.getDataCadastro())
                .agrupamento(entity.getAgrupamento())
                .cartao(CartaoMapper.mapResponse(entity.getCartao()))
                .build();
    }

    public static List<CompraCartaoResponse> mapListaResponse(List<CompraCartaoEntity> entities) {
        return entities.stream()
                .map(CompraCartaoMapper::mapResponse)
                .collect(Collectors.toList());
    }
}
