package br.com.szella.gerenciadordecontas.service;

import br.com.szella.gerenciadordecontas.dominio.compraCartao.CompraCartaoEntity;
import br.com.szella.gerenciadordecontas.dominio.compraCartao.CompraCartaoEditarRequest;
import br.com.szella.gerenciadordecontas.dominio.compraCartao.CompraCartaoSalvarRequest;
import br.com.szella.gerenciadordecontas.dominio.compraCartao.CompraCartaoAgrupadoResponse;

import java.util.List;

public interface CompraCartaoService {
    List<CompraCartaoEntity> listar();

    List<CompraCartaoAgrupadoResponse> listarAgrupado();

    CompraCartaoEntity buscarPorId(Long id);

    CompraCartaoEntity salvar(CompraCartaoSalvarRequest request);

    CompraCartaoEntity editar(Long id, CompraCartaoEditarRequest request);

    void deletar(Long id);
}
