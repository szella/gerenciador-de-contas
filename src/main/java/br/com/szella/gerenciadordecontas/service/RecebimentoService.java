package br.com.szella.gerenciadordecontas.service;

import br.com.szella.gerenciadordecontas.model.entity.CompraCartaoEntity;
import br.com.szella.gerenciadordecontas.model.entity.RecebimentoEntity;
import br.com.szella.gerenciadordecontas.model.request.CompraCartaoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.CompraCartaoSalvarRequest;
import br.com.szella.gerenciadordecontas.model.request.RecebimentoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.RecebimentoSalvarRequest;

import java.util.List;

public interface RecebimentoService {
    List<RecebimentoEntity> listar();

    RecebimentoEntity buscarPorId(Long id);

    RecebimentoEntity salvar(RecebimentoSalvarRequest request);

    RecebimentoEntity editar(Long id, RecebimentoEditarRequest request);

    void deletar(Long id);
}
