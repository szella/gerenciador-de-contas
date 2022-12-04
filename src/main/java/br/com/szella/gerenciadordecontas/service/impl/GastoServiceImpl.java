package br.com.szella.gerenciadordecontas.service.impl;

import br.com.szella.gerenciadordecontas.mapper.CompraCartaoMapper;
import br.com.szella.gerenciadordecontas.mapper.DespesaFixaMapper;
import br.com.szella.gerenciadordecontas.mapper.RecebimentoMapper;
import br.com.szella.gerenciadordecontas.model.response.GastosPorAnoResponse;
import br.com.szella.gerenciadordecontas.model.response.GastosPorMesResponse;
import br.com.szella.gerenciadordecontas.service.CompraCartaoService;
import br.com.szella.gerenciadordecontas.service.DespesaFixaService;
import br.com.szella.gerenciadordecontas.service.GastoService;
import br.com.szella.gerenciadordecontas.service.RecebimentoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class GastoServiceImpl implements GastoService {

    private final CompraCartaoService compraCartaoService;
    private final DespesaFixaService despesaFixaService;

    private final RecebimentoService recebimentoService;

    @Override
    public List<GastosPorAnoResponse> gastosPorAnoMes() {
        var compraCartaoList = compraCartaoService.listar();
        var despesaFixaList = despesaFixaService.listar();
        var recebimentoList = recebimentoService.listar();

        var anos = new HashSet<Integer>();
        var meses = new HashSet<Integer>();

        for (var compraCartao : compraCartaoList) {
            anos.add(compraCartao.getAno());
            meses.add(compraCartao.getMes());
        }

        for (var despesaFixa : despesaFixaList) {
            anos.add(despesaFixa.getAno());
            meses.add(despesaFixa.getMes());
        }

        for (var recebimento : recebimentoList) {
            anos.add(recebimento.getAno());
            meses.add(recebimento.getMes());
        }

        var gastosPorAnos = new ArrayList<GastosPorAnoResponse>();
        for (Integer ano : anos) {

            var gastosPorMeses = new ArrayList<GastosPorMesResponse>();
            for (Integer mes : meses) {
                var compraCartaoFilter = compraCartaoList.stream()
                        .filter(compraCartao -> ano.equals(compraCartao.getAno()) && mes.equals(compraCartao.getMes()))
                        .map(CompraCartaoMapper::mapResponse)
                        .toList();
                var despesaFixaFilter = despesaFixaList.stream()
                        .filter(despesaFixa -> ano.equals(despesaFixa.getAno()) && mes.equals(despesaFixa.getMes()))
                        .map(DespesaFixaMapper::mapResponse)
                        .toList();
                var recebimentoFilter = recebimentoList.stream()
                        .filter(recebimento -> ano.equals(recebimento.getAno()) && mes.equals(recebimento.getMes()))
                        .map(RecebimentoMapper::mapResponse)
                        .toList();

                if (!compraCartaoFilter.isEmpty() || !despesaFixaFilter.isEmpty()) {
                    gastosPorMeses
                            .add(GastosPorMesResponse.builder()
                                    .mes(mes)
                                    .comprasCartao(compraCartaoFilter)
                                    .despesasFixa(despesaFixaFilter)
                                    .recebimentos(recebimentoFilter)
                                    .build());
                }
            }

            gastosPorAnos
                    .add(GastosPorAnoResponse.builder()
                            .ano(ano)
                            .meses(gastosPorMeses)
                            .build());
        }

        return gastosPorAnos;
    }
}
