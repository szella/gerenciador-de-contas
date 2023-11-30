package br.com.szella.gerenciadordecontas.service;

import br.com.szella.gerenciadordecontas.dominio.despesa.DespesaEntity;
import br.com.szella.gerenciadordecontas.dominio.despesa.DespesaEditarRequest;
import br.com.szella.gerenciadordecontas.dominio.despesa.DespesaSalvarRequest;

import java.util.List;

public interface DespesaService {
    List<DespesaEntity> listar();

    DespesaEntity buscarPorId(Long id);

    DespesaEntity salvar(DespesaSalvarRequest request);

    DespesaEntity editar(Long id, DespesaEditarRequest request);

    void deletar(Long id);
}
