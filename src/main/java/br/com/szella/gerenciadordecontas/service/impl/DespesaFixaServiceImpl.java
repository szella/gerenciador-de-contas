package br.com.szella.gerenciadordecontas.service.impl;

import br.com.szella.gerenciadordecontas.enums.MensagemDeErro;
import br.com.szella.gerenciadordecontas.exception.DBException;
import br.com.szella.gerenciadordecontas.mapper.DespesaFixaMapper;
import br.com.szella.gerenciadordecontas.model.entity.DespesaFixaEntity;
import br.com.szella.gerenciadordecontas.model.request.DespesaFixaEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.DespesaFixaSalvarRequest;
import br.com.szella.gerenciadordecontas.repository.DespesaFixaRepository;
import br.com.szella.gerenciadordecontas.service.DespesaFixaService;
import br.com.szella.gerenciadordecontas.service.DespesaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DespesaFixaServiceImpl implements DespesaFixaService {
    private final DespesaFixaRepository repository;

    private final DespesaService despesaService;

    @Override
    public List<DespesaFixaEntity> listar() {
        return repository.findAll();
    }

    @Override
    public DespesaFixaEntity buscarPorId(Long id) {
        return Optional.of(repository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElseThrow(() -> new DBException(MensagemDeErro.NAO_ENCONTRADO.getMensagem()));
    }

    @Override
    public DespesaFixaEntity salvar(DespesaFixaSalvarRequest request) {
        try {
            var entity = DespesaFixaMapper.mapEntity(request);
            entity.setDespesa(despesaService.buscarPorId(request.getIdDespesa()));
            entity.setDataCadastro(LocalDateTime.now());

            List<DespesaFixaEntity> entities = new ArrayList<>();
            for (int x = 0; x < request.getPeriodo(); x++) {
                DespesaFixaEntity novaEntity = (DespesaFixaEntity) entity.clone();
                calcularMesAnoParcelamento(novaEntity, x);

                entities.add(novaEntity);
            }
            repository.saveAll(entities);

            return entities.get(0);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DespesaFixaEntity editar(Long id, DespesaFixaEditarRequest request) {
        var despesa = buscarPorId(id);

        DespesaFixaMapper.mapAtualizacao(request, despesa);

        if (!despesa.getDespesa().getId().equals(request.getIdDespesa())) {
            despesa.setDespesa(despesaService.buscarPorId(request.getIdDespesa()));
        }

        repository.save(despesa);
        return despesa;
    }

    @Override
    public void deletar(Long id) {
        repository.delete(buscarPorId(id));
    }

    private void calcularMesAnoParcelamento(DespesaFixaEntity despesa, Integer parcela) {
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
