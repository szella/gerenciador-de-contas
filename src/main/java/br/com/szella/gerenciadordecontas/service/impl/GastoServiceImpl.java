package br.com.szella.gerenciadordecontas.service.impl;

import br.com.szella.gerenciadordecontas.mapper.CompraCartaoMapper;
import br.com.szella.gerenciadordecontas.mapper.DespesaFixaMapper;
import br.com.szella.gerenciadordecontas.model.response.GastosPorAnoResponse;
import br.com.szella.gerenciadordecontas.model.response.GastosPorMesResponse;
import br.com.szella.gerenciadordecontas.service.CompraCartaoService;
import br.com.szella.gerenciadordecontas.service.DespesaFixaService;
import br.com.szella.gerenciadordecontas.service.GastoService;
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

    @Override
    public List<GastosPorAnoResponse> gastosPorAnoMes() {
        var compraCartaoList = compraCartaoService.listar();
        var despesaFixaList = despesaFixaService.listar();

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

                if (!compraCartaoFilter.isEmpty() || !despesaFixaFilter.isEmpty()) {
                    gastosPorMeses
                            .add(GastosPorMesResponse.builder()
                                    .mes(mes)
                                    .comprasCartao(compraCartaoFilter)
                                    .despesasFixa(despesaFixaFilter)
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
