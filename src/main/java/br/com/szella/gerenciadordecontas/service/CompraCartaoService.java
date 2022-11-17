package br.com.szella.gerenciadordecontas.service;

import br.com.szella.gerenciadordecontas.model.entity.CompraCartaoEntity;
import br.com.szella.gerenciadordecontas.model.request.CompraCartaoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.CompraCartaoSalvarRequest;

import java.util.List;

public interface CompraCartaoService {
    List<CompraCartaoEntity> listar();

    CompraCartaoEntity buscarPorId(Long id);

    CompraCartaoEntity salvar(CompraCartaoSalvarRequest request);

    CompraCartaoEntity editar(Long id, CompraCartaoEditarRequest request);

    void deletar(Long id);
}
