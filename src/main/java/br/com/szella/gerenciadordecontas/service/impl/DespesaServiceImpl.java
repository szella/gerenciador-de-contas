package br.com.szella.gerenciadordecontas.service.impl;

import br.com.szella.gerenciadordecontas.enums.MensagemDeErro;
import br.com.szella.gerenciadordecontas.exception.DBException;
import br.com.szella.gerenciadordecontas.exception.TipoDespesaException;
import br.com.szella.gerenciadordecontas.mapper.DespesaMapper;
import br.com.szella.gerenciadordecontas.model.entity.DespesaEntity;
import br.com.szella.gerenciadordecontas.model.request.DespesaEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.DespesaSalvarRequest;
import br.com.szella.gerenciadordecontas.repository.DespesaRepository;
import br.com.szella.gerenciadordecontas.service.CartaoService;
import br.com.szella.gerenciadordecontas.service.DespesaService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DespesaServiceImpl implements DespesaService {
    private final DespesaRepository despesaRepository;

    private final CartaoService cartaoService;

    @Override
    public List<DespesaEntity> listar() {
        return despesaRepository.findAll();
    }

    @Cacheable(cacheNames = "despesa", key = "#id")
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
        var despesa = DespesaMapper.mapDespesaSalvar(request);

        switch (request.getTipoDespesaEnum()) {
            case CARTAO -> despesa.setCartao(cartaoService.buscarPorId(request.getIdCartao()));
            default -> throw new TipoDespesaException(MensagemDeErro.SEM_TIPO_DESPESA.getMensagem());
        }

        return despesaRepository.save(despesa);
    }

    @Override
    public DespesaEntity editar(Long id, DespesaEditarRequest request) {
        var despesa = buscarPorId(id);

        DespesaMapper.mapDespesaEditar(request, despesa);

        if (!despesa.getCartao().getId().equals(request.getIdCartao())) {
            despesa.setCartao(cartaoService.buscarPorId(request.getIdCartao()));
        }

        switch (despesa.getTipoDespesaEnum()) {
            case CARTAO -> {
                if (!despesa.getCartao().getId().equals(request.getIdCartao())) {
                    despesa.setCartao(cartaoService.buscarPorId(request.getIdCartao()));
                }
            }

            default -> throw new TipoDespesaException(MensagemDeErro.SEM_TIPO_DESPESA.getMensagem());
        }


        despesaRepository.save(despesa);
        return despesa;
    }

    @Override
    public void deletar(Long id) {
        despesaRepository.delete(buscarPorId(id));
    }
}
