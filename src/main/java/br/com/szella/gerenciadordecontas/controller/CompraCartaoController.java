package br.com.szella.gerenciadordecontas.controller;

import br.com.szella.gerenciadordecontas.dominio.compraCartao.CompraCartaoMapper;
import br.com.szella.gerenciadordecontas.dominio.compraCartao.CompraCartaoEditarRequest;
import br.com.szella.gerenciadordecontas.dominio.compraCartao.CompraCartaoSalvarRequest;
import br.com.szella.gerenciadordecontas.dominio.compraCartao.CompraCartaoAgrupadoResponse;
import br.com.szella.gerenciadordecontas.dominio.compraCartao.CompraCartaoResponse;
import br.com.szella.gerenciadordecontas.service.CompraCartaoService;
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
import java.util.Optional;

@RestController
@RequestMapping("/compras-cartao")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class CompraCartaoController {
    private final CompraCartaoService service;

    @GetMapping
    public ResponseEntity<List<CompraCartaoResponse>> listar() {
        var entities = service.listar();
        return ResponseEntity.ok(CompraCartaoMapper.mapListaResponse(entities));
    }

    @GetMapping("/agrupado")
    public ResponseEntity<List<CompraCartaoAgrupadoResponse>> listarAgrupado() {
        return ResponseEntity.ok(service.listarAgrupado());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompraCartaoResponse> buscarPorId(@PathVariable Long id) {
        var entity = Optional
                .ofNullable(service.buscarPorId(id))
                .map(CompraCartaoMapper::mapResponse)
                .orElse(null);

        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<CompraCartaoResponse> salvar(@RequestBody CompraCartaoSalvarRequest request) {
        var entity = service.salvar(request);
        return ResponseEntity.ok(CompraCartaoMapper.mapResponse(entity));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CompraCartaoResponse> editar(@PathVariable Long id, @RequestBody CompraCartaoEditarRequest request) {
        var entity = service.editar(id, request);
        return ResponseEntity.ok(CompraCartaoMapper.mapResponse(entity));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> editar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
