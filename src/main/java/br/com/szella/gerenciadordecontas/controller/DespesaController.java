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
    private final DespesaService service;

    @GetMapping
    public ResponseEntity<List<DespesaResponse>> listar() {
        var entities = service.listar();
        return ResponseEntity.ok(DespesaMapper.mapListaResponse(entities));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DespesaResponse> buscarPorId(@PathVariable Long id) {
        var entity = Optional
                .ofNullable(service.buscarPorId(id))
                .filter(Objects::nonNull)
                .map(DespesaMapper::mapResponse)
                .orElse(null);

        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<DespesaResponse> salvar(@RequestBody DespesaSalvarRequest request) {
        var entity = service.salvar(request);
        return ResponseEntity.ok(DespesaMapper.mapResponse(entity));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DespesaResponse> editar(@PathVariable Long id, @RequestBody DespesaEditarRequest request) {
        var entity = service.editar(id, request);
        return ResponseEntity.ok(DespesaMapper.mapResponse(entity));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> editar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
