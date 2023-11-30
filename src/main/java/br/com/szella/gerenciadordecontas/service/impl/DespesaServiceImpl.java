package br.com.szella.gerenciadordecontas.service.impl;

import br.com.szella.gerenciadordecontas.enums.MensagemDeErro;
import br.com.szella.gerenciadordecontas.exception.DBException;
import br.com.szella.gerenciadordecontas.dominio.despesa.DespesaMapper;
import br.com.szella.gerenciadordecontas.dominio.despesa.DespesaEntity;
import br.com.szella.gerenciadordecontas.dominio.despesa.DespesaEditarRequest;
import br.com.szella.gerenciadordecontas.dominio.despesa.DespesaSalvarRequest;
import br.com.szella.gerenciadordecontas.repository.DespesaRepository;
import br.com.szella.gerenciadordecontas.service.DespesaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DespesaServiceImpl implements DespesaService {
    private final DespesaRepository despesaRepository;

    @Override
    public List<DespesaEntity> listar() {
        return despesaRepository.findAll();
    }

    @Override
    public DespesaEntity buscarPorId(Long id) {
        return Optional
                .of(despesaRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElseThrow(() -> new DBException(MensagemDeErro.NAO_ENCONTRADO.getMensagem()));
    }

    @Override
    public DespesaEntity salvar(DespesaSalvarRequest request) {
        return despesaRepository.save(DespesaMapper.mapEntity(request));
    }

    @Override
    public DespesaEntity editar(Long id, DespesaEditarRequest request) {
        var despesa = buscarPorId(id);

        DespesaMapper.mapAtualizacao(request, despesa);
        despesaRepository.save(despesa);
        return despesa;
    }

    @Override
    public void deletar(Long id) {
        despesaRepository.delete(buscarPorId(id));
    }

}
