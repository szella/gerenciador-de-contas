package br.com.szella.gerenciadordecontas.service;

import br.com.szella.gerenciadordecontas.model.entity.DespesaEntity;
import br.com.szella.gerenciadordecontas.model.request.DespesaEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.DespesaSalvarRequest;

import java.util.List;

public interface DespesaService {
    List<DespesaEntity> listar();

    DespesaEntity buscarPorId(Long id);

    DespesaEntity salvar(DespesaSalvarRequest request);

    DespesaEntity editar(Long id, DespesaEditarRequest request);

    void deletar(Long id);
}
