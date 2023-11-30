package br.com.szella.gerenciadordecontas.dominio.despesaFixa;

import br.com.szella.gerenciadordecontas.dominio.despesa.DespesaMapper;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DespesaFixaMapper {
    public static DespesaFixaEntity mapEntity(DespesaFixaSalvarRequest request) {
        return DespesaFixaEntity.builder()
                .valor(request.getValor())
                                .anoMes(request.getAnoMes())
                .build();
    }

    public static void mapAtualizacao(DespesaFixaEditarRequest novo, DespesaFixaEntity atual) {
        atual.setValor(novo.getValor());
         atual.setAnoMes(novo.getAnoMes());
    }

    public static DespesaFixaResponse mapResponse(DespesaFixaEntity entity) {
        return DespesaFixaResponse.builder()
                .id(entity.getId())
                .valor(entity.getValor())
                .anoMes(entity.getAnoMes())
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
