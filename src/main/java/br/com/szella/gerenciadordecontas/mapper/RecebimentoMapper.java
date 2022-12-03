package br.com.szella.gerenciadordecontas.mapper;

import br.com.szella.gerenciadordecontas.model.entity.CompraCartaoEntity;
import br.com.szella.gerenciadordecontas.model.entity.RecebimentoEntity;
import br.com.szella.gerenciadordecontas.model.request.CompraCartaoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.CompraCartaoSalvarRequest;
import br.com.szella.gerenciadordecontas.model.request.RecebimentoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.RecebimentoSalvarRequest;
import br.com.szella.gerenciadordecontas.model.response.CompraCartaoResponse;
import br.com.szella.gerenciadordecontas.model.response.RecebimentoResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RecebimentoMapper {
    public static RecebimentoEntity mapEntity(RecebimentoSalvarRequest request) {
        return RecebimentoEntity.builder()
                .nome(request.getNome())
                .valor(request.getValor())
                .mes(request.getMes())
                .ano(request.getAno())
                .build();
    }

    public static void mapAtualizacao(RecebimentoEditarRequest novo, RecebimentoEntity atual) {
        atual.setNome(novo.getNome());
        atual.setValor(novo.getValor());
        atual.setMes(novo.getMes());
        atual.setAno(novo.getAno());
    }

    public static RecebimentoResponse mapResponse(RecebimentoEntity entity) {
        return RecebimentoResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .valor(entity.getValor())
                .mes(entity.getMes())
                .ano(entity.getAno())
                .dataCadastro(entity.getDataCadastro())
                .agrupamento(entity.getAgrupamento())
                .build();
    }

    public static List<RecebimentoResponse> mapListaResponse(List<RecebimentoEntity> entities) {
        return entities.stream()
                .map(RecebimentoMapper::mapResponse)
                .collect(Collectors.toList());
    }
}
