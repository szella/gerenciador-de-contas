package br.com.szella.gerenciadordecontas.service;

import br.com.szella.gerenciadordecontas.dominio.recebimento.RecebimentoEntity;
import br.com.szella.gerenciadordecontas.dominio.recebimento.RecebimentoEditarRequest;
import br.com.szella.gerenciadordecontas.dominio.recebimento.RecebimentoSalvarRequest;

import java.util.List;

public interface RecebimentoService {
    List<RecebimentoEntity> listar();

    RecebimentoEntity buscarPorId(Long id);

    RecebimentoEntity salvar(RecebimentoSalvarRequest request);

    RecebimentoEntity editar(Long id, RecebimentoEditarRequest request);

    void deletar(Long id);
}
