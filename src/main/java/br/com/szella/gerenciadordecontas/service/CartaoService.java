package br.com.szella.gerenciadordecontas.service;

import br.com.szella.gerenciadordecontas.model.entity.Cartao;
import br.com.szella.gerenciadordecontas.model.request.CartaoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.CartaoSalvarRequest;

import java.util.List;

public interface CartaoService {
    List<Cartao> listar();

    Cartao buscarPorId(Long id);

    Cartao salvar(CartaoSalvarRequest request);

    Cartao editar(Long id, CartaoEditarRequest request);

    void deletar(Long id);
}
