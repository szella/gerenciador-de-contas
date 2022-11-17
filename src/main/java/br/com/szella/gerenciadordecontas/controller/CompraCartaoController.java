package br.com.szella.gerenciadordecontas.controller;

import br.com.szella.gerenciadordecontas.mapper.CompraCartaoMapper;
import br.com.szella.gerenciadordecontas.model.request.CompraCartaoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.CompraCartaoSalvarRequest;
import br.com.szella.gerenciadordecontas.model.response.CompraCartaoResponse;
import br.com.szella.gerenciadordecontas.service.CompraCartaoService;
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
@RequestMapping("/compras_cartao")
@AllArgsConstructor
public class CompraCartaoController {
    private final CompraCartaoService compraCartaoService;

    @GetMapping
    public ResponseEntity<List<CompraCartaoResponse>> listar() {
        var despesas = compraCartaoService.listar();
        return ResponseEntity.ok(CompraCartaoMapper.mapListaResponse(despesas));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompraCartaoResponse> buscarPorId(@PathVariable Long id) {
        var despesa = Optional
                .ofNullable(compraCartaoService.buscarPorId(id))
                .filter(Objects::nonNull)
                .map(CompraCartaoMapper::mapResponse)
                .orElse(null);

        return ResponseEntity.ok(despesa);
    }

    @PostMapping
    public ResponseEntity<CompraCartaoResponse> salvar(@RequestBody CompraCartaoSalvarRequest request) {
        var despesa = compraCartaoService.salvar(request);
        return ResponseEntity.ok(CompraCartaoMapper.mapResponse(despesa));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CompraCartaoResponse> editar(@PathVariable Long id, @RequestBody CompraCartaoEditarRequest request) {
        var despesa = compraCartaoService.editar(id, request);
        return ResponseEntity.ok(CompraCartaoMapper.mapResponse(despesa));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CompraCartaoResponse> editar(@PathVariable Long id) {
        compraCartaoService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
