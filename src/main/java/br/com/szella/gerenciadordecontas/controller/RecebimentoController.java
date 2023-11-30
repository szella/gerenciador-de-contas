package br.com.szella.gerenciadordecontas.controller;

import br.com.szella.gerenciadordecontas.dominio.recebimento.RecebimentoMapper;
import br.com.szella.gerenciadordecontas.dominio.recebimento.RecebimentoEditarRequest;
import br.com.szella.gerenciadordecontas.dominio.recebimento.RecebimentoSalvarRequest;
import br.com.szella.gerenciadordecontas.dominio.recebimento.RecebimentoResponse;
import br.com.szella.gerenciadordecontas.service.RecebimentoService;
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
@RequestMapping("/recebimentos")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class RecebimentoController {
    private final RecebimentoService service;

    @GetMapping
    public ResponseEntity<List<RecebimentoResponse>> listar() {
        var entities = service.listar();
        return ResponseEntity.ok(RecebimentoMapper.mapListaResponse(entities));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RecebimentoResponse> buscarPorId(@PathVariable Long id) {
        var entity = Optional
                .ofNullable(service.buscarPorId(id))
                .filter(Objects::nonNull)
                .map(RecebimentoMapper::mapResponse)
                .orElse(null);

        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<RecebimentoResponse> salvar(@RequestBody RecebimentoSalvarRequest request) {
        var entity = service.salvar(request);
        return ResponseEntity.ok(RecebimentoMapper.mapResponse(entity));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RecebimentoResponse> editar(@PathVariable Long id, @RequestBody RecebimentoEditarRequest request) {
        var entity = service.editar(id, request);
        return ResponseEntity.ok(RecebimentoMapper.mapResponse(entity));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> editar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
