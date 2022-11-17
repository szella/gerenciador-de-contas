package br.com.szella.gerenciadordecontas.service.impl;

import br.com.szella.gerenciadordecontas.enums.MensagemDeErro;
import br.com.szella.gerenciadordecontas.exception.DBException;
import br.com.szella.gerenciadordecontas.mapper.CompraCartaoMapper;
import br.com.szella.gerenciadordecontas.model.entity.CompraCartaoEntity;
import br.com.szella.gerenciadordecontas.model.request.CompraCartaoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.CompraCartaoSalvarRequest;
import br.com.szella.gerenciadordecontas.repository.DespesaRepository;
import br.com.szella.gerenciadordecontas.service.CartaoService;
import br.com.szella.gerenciadordecontas.service.CompraCartaoService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CompraCartaoServiceImpl implements CompraCartaoService {
    private final DespesaRepository despesaRepository;

    private final CartaoService cartaoService;

    @Override
    public List<CompraCartaoEntity> listar() {
        return despesaRepository.findAll();
    }

    @Cacheable(cacheNames = "despesa", key = "#id")
    @Override
    public CompraCartaoEntity buscarPorId(Long id) {
        return Optional
                .of(despesaRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElseThrow(() -> new DBException(MensagemDeErro.NAO_ENCONTRADO.getMensagem()));
    }

    @Override
    public CompraCartaoEntity salvar(CompraCartaoSalvarRequest request) {
        try {
            var despesaBase = CompraCartaoMapper.mapEntity(request);

            if (request.getParcelas() > 1) {
                despesaBase.setAgrupamento(UUID.randomUUID().toString());
            }

            List<CompraCartaoEntity> despesas = new ArrayList<>();
            for (int x = 0; x < request.getParcelas(); x++) {
                CompraCartaoEntity despesa = (CompraCartaoEntity) despesaBase.clone();
                calcularMesAnoParcelamento(despesa, x);

                despesas.add(despesa);
            }
            despesaRepository.saveAll(despesas);

            return despesas.get(0);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompraCartaoEntity editar(Long id, CompraCartaoEditarRequest request) {
        var despesa = buscarPorId(id);

        CompraCartaoMapper.mapAtualizacao(request, despesa);

        if (!despesa.getCartao().getId().equals(request.getIdCartao())) {
            despesa.setCartao(cartaoService.buscarPorId(request.getIdCartao()));
        }

        despesaRepository.save(despesa);
        return despesa;
    }

    @Override
    public void deletar(Long id) {
        despesaRepository.delete(buscarPorId(id));
    }

    private void calcularMesAnoParcelamento(CompraCartaoEntity despesa, Integer parcela) {
        Integer mes = despesa.getMes();
        Integer ano = despesa.getAno();

        for (Integer x = 0; x < parcela; x++) {
            if (mes >= 12) {
                mes = 1;
                ano++;
            } else {
                mes++;
            }
        }

        despesa.setMes(mes);
        despesa.setAno(ano);
    }
}
