package br.com.szella.gerenciadordecontas.service;

import br.com.szella.gerenciadordecontas.model.response.GastosPorAnoResponse;

import java.util.List;

public interface GastoService {

    List<GastosPorAnoResponse> gastosPorAnoMes();
}
