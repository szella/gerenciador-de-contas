package br.com.szella.gerenciadordecontas.controller;

import br.com.szella.gerenciadordecontas.model.response.GastosPorAnoResponse;
import br.com.szella.gerenciadordecontas.service.GastoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gastos")
@AllArgsConstructor
public class GastosController {

    private GastoService service;

    @GetMapping(value = "/ano/mes")
    public ResponseEntity<List<GastosPorAnoResponse>> buscarPorAnoMes() {
        return ResponseEntity.ok(service.gastosPorAnoMes());
    }
}
