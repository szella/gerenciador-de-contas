package br.com.szella.gerenciadordecontas.mapper;

import br.com.szella.gerenciadordecontas.model.entity.DespesaFixaEntity;
import br.com.szella.gerenciadordecontas.model.request.DespesaFixaEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.DespesaFixaSalvarRequest;
import br.com.szella.gerenciadordecontas.model.response.DespesaFixaResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DespesaFixaMapper {
    public static DespesaFixaEntity mapEntity(DespesaFixaSalvarRequest request) {
        return DespesaFixaEntity.builder()
                .valor(request.getValor())
                .mes(request.getMes())
                .ano(request.getAno())
                .build();
    }

    public static void mapAtualizacao(DespesaFixaEditarRequest novo, DespesaFixaEntity atual) {
        atual.setValor(novo.getValor());
        atual.setMes(novo.getMes());
        atual.setAno(novo.getAno());
    }

    public static DespesaFixaResponse mapResponse(DespesaFixaEntity entity) {
        return DespesaFixaResponse.builder()
                .id(entity.getId())
                .valor(entity.getValor())
                .mes(entity.getMes())
                .ano(entity.getAno())
                .dataCadastro(entity.getDataCadastro())
                .despesa(DespesaMapper.mapResponse(entity.getDespesa()))
                .build();
    }

    public static List<DespesaFixaResponse> mapListaResponse(List<DespesaFixaEntity> entities) {
        return entities.stream()
                .map(DespesaFixaMapper::mapResponse)
                .collect(Collectors.toList());
    }
}
