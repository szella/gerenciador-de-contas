package br.com.szella.gerenciadordecontas.service.impl;

import br.com.szella.gerenciadordecontas.enums.MensagemDeErro;
import br.com.szella.gerenciadordecontas.exception.DBException;
import br.com.szella.gerenciadordecontas.dominio.compraCartao.CompraCartaoMapper;
import br.com.szella.gerenciadordecontas.dominio.compraCartao.CompraCartaoEntity;
import br.com.szella.gerenciadordecontas.dominio.compraCartao.CompraCartaoEditarRequest;
import br.com.szella.gerenciadordecontas.dominio.compraCartao.CompraCartaoSalvarRequest;
import br.com.szella.gerenciadordecontas.dominio.compraCartao.CompraCartaoAgrupadoResponse;
import br.com.szella.gerenciadordecontas.dominio.compraCartao.CompraCartaoResponse;
import br.com.szella.gerenciadordecontas.repository.CompraCartaoRepository;
import br.com.szella.gerenciadordecontas.service.CartaoService;
import br.com.szella.gerenciadordecontas.service.CompraCartaoService;
import lombok.AllArgsConstructor;
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

    @Override
    public List<CompraCartaoAgrupadoResponse> listarAgrupado() {
        var entities = listar();

        var response = new ArrayList<CompraCartaoAgrupadoResponse>();

        for (var entity : entities) {
            response.stream()
                    .filter(responseFilter -> entity.getAnoMes().equals(responseFilter.getAnoMes()))
                    .findFirst()
                    .ifPresentOrElse(
                            compraCartao -> {
                                compraCartao.somarValorTotal(entity.getValor());
                                compraCartao.getComprasCartao().add(CompraCartaoMapper.mapResponse(entity));
                            },
                            () -> {
                                var responses = new ArrayList<CompraCartaoResponse>();
                                responses.add(CompraCartaoMapper.mapResponse(entity));
                                response.add(
                                        CompraCartaoAgrupadoResponse.builder()
                                                .anoMes(entity.getAnoMes())
                                                .valorTotal(entity.getValor())
                                                .comprasCartao(responses)
                                                .build()
                                );
                            });
        }

        return response;
    }

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
                novaEntity.setAnoMes(novaEntity.getAnoMes().plusMonths(x));

                entities.add(novaEntity);
            }
            repository.saveAll(entities);

            return entities.get(0);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

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

    @Override
    public void deletar(Long id) {
        repository.delete(buscarPorId(id));
    }

}
