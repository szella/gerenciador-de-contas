package br.com.szella.gerenciadordecontas.mapper;

import br.com.szella.gerenciadordecontas.model.entity.DespesaEntity;
import br.com.szella.gerenciadordecontas.model.request.DespesaEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.DespesaSalvarRequest;
import br.com.szella.gerenciadordecontas.model.response.DespesaResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DespesaMapper {
    public static DespesaEntity mapDespesaSalvar(DespesaSalvarRequest request) {
        return DespesaEntity.builder()
                .nome(request.getNome())
                .valor(request.getValor())
                .mes(request.getMes())
                .ano(request.getAno())
                .tipoDespesaEnum(request.getTipoDespesaEnum())
                .build();
    }

    public static void mapDespesaEditar(DespesaEditarRequest novo, DespesaEntity atual) {
        atual.setNome(novo.getNome());
        atual.setValor(novo.getValor());
        atual.setMes(novo.getMes());
        atual.setAno(novo.getAno());
    }

    public static DespesaResponse mapDespesaResponse(DespesaEntity despesa) {
        return DespesaResponse.builder()
                .id(despesa.getId())
                .nome(despesa.getNome())
                .valor(despesa.getValor())
                .mes(despesa.getMes())
                .ano(despesa.getAno())
                .tipoDespesaEnum(despesa.getTipoDespesaEnum())
                .cartao(CartaoMapper.mapCartaoResponse(despesa.getCartao()))
                .build();
    }

    public static List<DespesaResponse> mapListDespesaResponse(List<DespesaEntity> despesas) {
        return despesas.stream()
                .map(DespesaMapper::mapDespesaResponse)
                .collect(Collectors.toList());
    }
}
