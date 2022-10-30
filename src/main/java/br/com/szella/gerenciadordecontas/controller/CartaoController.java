package br.com.szella.gerenciadordecontas.controller;

import br.com.szella.gerenciadordecontas.mapper.CartaoMapper;
import br.com.szella.gerenciadordecontas.model.request.CartaoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.CartaoSalvarRequest;
import br.com.szella.gerenciadordecontas.model.response.CartaoResponse;
import br.com.szella.gerenciadordecontas.service.CartaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
@AllArgsConstructor
public class CartaoController {
    private final CartaoService cartaoService;

    @GetMapping
    public ResponseEntity<List<CartaoResponse>> listar() {
        var cartoes = cartaoService.listar();
        return ResponseEntity.ok(CartaoMapper.mapListCartaoResponse(cartoes));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CartaoResponse> buscarPorId(@PathVariable Long id) {
        var cartao = Optional
                .ofNullable(cartaoService.buscarPorId(id))
                .filter(Objects::nonNull)
                .map(CartaoMapper::mapCartaoResponse)
                .orElse(null);

        return ResponseEntity.ok(cartao);
    }

    @PostMapping
    public ResponseEntity<CartaoResponse> salvar(@RequestBody CartaoSalvarRequest request) {
        var cartao = cartaoService.salvar(request);
        return ResponseEntity.ok(CartaoMapper.mapCartaoResponse(cartao));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CartaoResponse> editar(@PathVariable Long id, @RequestBody CartaoEditarRequest request) {
        var cartao = cartaoService.editar(id, request);
        return ResponseEntity.ok(CartaoMapper.mapCartaoResponse(cartao));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CartaoResponse> editar(@PathVariable Long id) {
        cartaoService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
