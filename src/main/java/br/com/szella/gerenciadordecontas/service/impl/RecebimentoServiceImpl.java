package br.com.szella.gerenciadordecontas.service.impl;

import br.com.szella.gerenciadordecontas.enums.MensagemDeErro;
import br.com.szella.gerenciadordecontas.exception.DBException;
import br.com.szella.gerenciadordecontas.mapper.RecebimentoMapper;
import br.com.szella.gerenciadordecontas.model.entity.RecebimentoEntity;
import br.com.szella.gerenciadordecontas.model.request.RecebimentoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.RecebimentoSalvarRequest;
import br.com.szella.gerenciadordecontas.repository.RecebimentoRepository;
import br.com.szella.gerenciadordecontas.service.RecebimentoService;
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
public class RecebimentoServiceImpl implements RecebimentoService {
    private final RecebimentoRepository repository;

    @Override
    public List<RecebimentoEntity> listar() {
        return repository.findAll();
    }

    @Cacheable("recebimento")
    @Override
    public RecebimentoEntity buscarPorId(Long id) {
        return Optional.of(repository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElseThrow(() -> new DBException(MensagemDeErro.NAO_ENCONTRADO.getMensagem()));
    }

    @Override
    public RecebimentoEntity salvar(RecebimentoSalvarRequest request) {
        try {
            var entity = RecebimentoMapper.mapEntity(request);
            entity.setDataCadastro(LocalDateTime.now());

            if (request.getPeriodo() > 1) {
                entity.setAgrupamento(UUID.randomUUID().toString());
            }

            List<RecebimentoEntity> entities = new ArrayList<>();
            for (int x = 0; x < request.getPeriodo(); x++) {
                RecebimentoEntity novaEntity = (RecebimentoEntity) entity.clone();
                calcularMesAnoParcelamento(novaEntity, x);

                entities.add(novaEntity);
            }
            repository.saveAll(entities);

            return entities.get(0);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @CacheEvict("recebimento")
    @Override
    public RecebimentoEntity editar(Long id, RecebimentoEditarRequest request) {
        var entity = buscarPorId(id);

        RecebimentoMapper.mapAtualizacao(request, entity);

        repository.save(entity);
        return entity;
    }

    @CacheEvict("recebimento")
    @Override
    public void deletar(Long id) {
        repository.delete(buscarPorId(id));
    }

    private void calcularMesAnoParcelamento(RecebimentoEntity despesa, Integer parcela) {
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
