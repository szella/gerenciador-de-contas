package br.com.szella.gerenciadordecontas.controller;

import br.com.szella.gerenciadordecontas.mapper.DespesaFixaMapper;
import br.com.szella.gerenciadordecontas.model.request.DespesaFixaEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.DespesaFixaSalvarRequest;
import br.com.szella.gerenciadordecontas.model.response.DespesaFixaResponse;
import br.com.szella.gerenciadordecontas.service.DespesaFixaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/despesas-fixa")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class DespesaFixaController {
    private final DespesaFixaService service;

    @GetMapping
    public ResponseEntity<List<DespesaFixaResponse>> listar() {
        var entities = service.listar();
        return ResponseEntity.ok(DespesaFixaMapper.mapListaResponse(entities));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DespesaFixaResponse> buscarPorId(@PathVariable Long id) {
        var entity = Optional
                .ofNullable(service.buscarPorId(id))
                .filter(Objects::nonNull)
                .map(DespesaFixaMapper::mapResponse)
                .orElse(null);

        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<DespesaFixaResponse> salvar(@RequestBody DespesaFixaSalvarRequest request) {
        var entity = service.salvar(request);
        return ResponseEntity.ok(DespesaFixaMapper.mapResponse(entity));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DespesaFixaResponse> editar(@PathVariable Long id, @RequestBody DespesaFixaEditarRequest request) {
        var entity = service.editar(id, request);
        return ResponseEntity.ok(DespesaFixaMapper.mapResponse(entity));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> editar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
