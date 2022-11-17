package br.com.szella.gerenciadordecontas.service;

import br.com.szella.gerenciadordecontas.model.entity.DespesaFixaEntity;
import br.com.szella.gerenciadordecontas.model.request.DespesaFixaEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.DespesaFixaSalvarRequest;

import java.util.List;

public interface DespesaFixaService {
    List<DespesaFixaEntity> listar();

    DespesaFixaEntity buscarPorId(Long id);

    DespesaFixaEntity salvar(DespesaFixaSalvarRequest request);

    DespesaFixaEntity editar(Long id, DespesaFixaEditarRequest request);

    void deletar(Long id);
}
