package br.com.szella.gerenciadordecontas.service.impl;

import br.com.szella.gerenciadordecontas.enums.MensagemDeErro;
import br.com.szella.gerenciadordecontas.exception.DBException;
import br.com.szella.gerenciadordecontas.mapper.CompraCartaoMapper;
import br.com.szella.gerenciadordecontas.model.entity.CompraCartaoEntity;
import br.com.szella.gerenciadordecontas.model.request.CompraCartaoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.CompraCartaoSalvarRequest;
import br.com.szella.gerenciadordecontas.repository.CompraCartaoRepository;
import br.com.szella.gerenciadordecontas.service.CartaoService;
import br.com.szella.gerenciadordecontas.service.CompraCartaoService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CompraCartaoServiceImpl implements CompraCartaoService {
    private final CompraCartaoRepository repository;

    private final CartaoService cartaoService;

    @Override
    public List<CompraCartaoEntity> listar() {
        return repository.findAll();
    }

    @Cacheable("compra-cartao")
    @Override
    public CompraCartaoEntity buscarPorId(Long id) {
        return Optional.of(repository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElseThrow(() -> new DBException(MensagemDeErro.NAO_ENCONTRADO.getMensagem()));
    }

    @Override
    public CompraCartaoEntity salvar(CompraCartaoSalvarRequest request) {
        try {
            var entity = CompraCartaoMapper.mapEntity(request);
            entity.setCartao(cartaoService.buscarPorId(request.getIdCartao()));
            entity.setDataCadastro(LocalDateTime.now());

            if (request.getParcelas() > 1) {
                entity.setAgrupamento(UUID.randomUUID().toString());
            }

            List<CompraCartaoEntity> entities = new ArrayList<>();
            for (int x = 0; x < request.getParcelas(); x++) {
                CompraCartaoEntity novaEntity = (CompraCartaoEntity) entity.clone();
                calcularMesAnoParcelamento(novaEntity, x);

                entities.add(novaEntity);
            }
            repository.saveAll(entities);

            return entities.get(0);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @CacheEvict("compra-cartao")
    @Override
    public CompraCartaoEntity editar(Long id, CompraCartaoEditarRequest request) {
        var entity = buscarPorId(id);

        CompraCartaoMapper.mapAtualizacao(request, entity);

        if (!entity.getCartao().getId().equals(request.getIdCartao())) {
            entity.setCartao(cartaoService.buscarPorId(request.getIdCartao()));
        }

        repository.save(entity);
        return entity;
    }

    @CacheEvict("compra-cartao")
    @Override
    public void deletar(Long id) {
        repository.delete(buscarPorId(id));
    }

    private void calcularMesAnoParcelamento(CompraCartaoEntity entity, Integer parcela) {
        Integer mes = entity.getMes();
        Integer ano = entity.getAno();

        for (Integer x = 0; x < parcela; x++) {
            if (mes >= 12) {
                mes = 1;
                ano++;
            } else {
                mes++;
            }
        }

        entity.setMes(mes);
        entity.setAno(ano);
    }
}
