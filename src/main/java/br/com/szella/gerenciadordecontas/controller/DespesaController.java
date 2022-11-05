package br.com.szella.gerenciadordecontas.controller;

import br.com.szella.gerenciadordecontas.mapper.DespesaMapper;
import br.com.szella.gerenciadordecontas.model.request.DespesaEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.DespesaSalvarRequest;
import br.com.szella.gerenciadordecontas.model.response.DespesaResponse;
import br.com.szella.gerenciadordecontas.service.DespesaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/despesas")
@AllArgsConstructor
public class DespesaController {
    private final DespesaService despesaService;

    @GetMapping
    public ResponseEntity<List<DespesaResponse>> listar() {
        var despesas = despesaService.listar();
        return ResponseEntity.ok(DespesaMapper.mapListDespesaResponse(despesas));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DespesaResponse> buscarPorId(@PathVariable Long id) {
        var despesa = Optional
                .ofNullable(despesaService.buscarPorId(id))
                .filter(Objects::nonNull)
                .map(DespesaMapper::mapDespesaResponse)
                .orElse(null);

        return ResponseEntity.ok(despesa);
    }

    @PostMapping
    public ResponseEntity<DespesaResponse> salvar(@RequestBody DespesaSalvarRequest request) {
        var despesa = despesaService.salvar(request);
        return ResponseEntity.ok(DespesaMapper.mapDespesaResponse(despesa));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DespesaResponse> editar(@PathVariable Long id, @RequestBody DespesaEditarRequest request) {
        var despesa = despesaService.editar(id, request);
        return ResponseEntity.ok(DespesaMapper.mapDespesaResponse(despesa));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DespesaResponse> editar(@PathVariable Long id) {
        despesaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
