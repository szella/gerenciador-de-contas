package br.com.szella.gerenciadordecontas.dominio.despesa;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DespesaMapper {
    public static DespesaEntity mapEntity(DespesaSalvarRequest request) {
        return DespesaEntity.builder()
                .nome(request.getNome())
                .build();
    }

    public static void mapAtualizacao(DespesaEditarRequest novo, DespesaEntity atual) {
        atual.setNome(novo.getNome());
    }

    public static DespesaResponse mapResponse(DespesaEntity entity) {
        return DespesaResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .build();
    }

    public static List<DespesaResponse> mapListaResponse(List<DespesaEntity> entities) {
        return entities.stream()
                .map(DespesaMapper::mapResponse)
                .collect(Collectors.toList());
    }
}
