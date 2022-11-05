package br.com.szella.gerenciadordecontas.service;

import br.com.szella.gerenciadordecontas.model.entity.CartaoEntity;
import br.com.szella.gerenciadordecontas.model.request.CartaoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.CartaoSalvarRequest;

import java.util.List;

public interface CartaoService {
    List<CartaoEntity> listar();

    CartaoEntity buscarPorId(Long id);

    CartaoEntity salvar(CartaoSalvarRequest request);

    CartaoEntity editar(Long id, CartaoEditarRequest request);

    void deletar(Long id);
}
