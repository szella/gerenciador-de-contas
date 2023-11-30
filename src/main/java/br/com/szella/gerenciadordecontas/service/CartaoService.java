package br.com.szella.gerenciadordecontas.service;

import br.com.szella.gerenciadordecontas.dominio.cartao.CartaoEntity;
import br.com.szella.gerenciadordecontas.dominio.cartao.CartaoEditarRequest;
import br.com.szella.gerenciadordecontas.dominio.cartao.CartaoSalvarRequest;

import java.util.List;

public interface CartaoService {
    List<CartaoEntity> listar();

    CartaoEntity buscarPorId(Long id);

    CartaoEntity salvar(CartaoSalvarRequest request);

    CartaoEntity editar(Long id, CartaoEditarRequest request);

    void deletar(Long id);
}
