package br.com.szella.gerenciadordecontas.service;

import br.com.szella.gerenciadordecontas.dominio.despesaFixa.DespesaFixaEntity;
import br.com.szella.gerenciadordecontas.dominio.despesaFixa.DespesaFixaEditarRequest;
import br.com.szella.gerenciadordecontas.dominio.despesaFixa.DespesaFixaSalvarRequest;

import java.util.List;

public interface DespesaFixaService {
    List<DespesaFixaEntity> listar();

    DespesaFixaEntity buscarPorId(Long id);

    DespesaFixaEntity salvar(DespesaFixaSalvarRequest request);

    DespesaFixaEntity editar(Long id, DespesaFixaEditarRequest request);

    void deletar(Long id);
}
