package br.com.szella.gerenciadordecontas.service.impl;

import br.com.szella.gerenciadordecontas.enums.MensagemDeErro;
import br.com.szella.gerenciadordecontas.exception.DBException;
import br.com.szella.gerenciadordecontas.mapper.CartaoMapper;
import br.com.szella.gerenciadordecontas.model.entity.CartaoEntity;
import br.com.szella.gerenciadordecontas.model.request.CartaoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.CartaoSalvarRequest;
import br.com.szella.gerenciadordecontas.repository.CartaoRepository;
import br.com.szella.gerenciadordecontas.service.CartaoService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartaoServiceImpl implements CartaoService {
    private final CartaoRepository cartaoRepository;

    @Override
    public List<CartaoEntity> listar() {
        return cartaoRepository.findAll();
    }

    @Cacheable(cacheNames = "cartao", key = "#id")
    @Override
    public CartaoEntity buscarPorId(Long id) {
        return Optional
                .of(cartaoRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElseThrow(() -> new DBException(MensagemDeErro.NAO_ENCONTRADO.getMensagem()));
    }

    @Override
    public CartaoEntity salvar(CartaoSalvarRequest request) {
        return cartaoRepository.save(CartaoMapper.mapCartaoSalvar(request));
    }

    @CacheEvict(cacheNames = "cartao", key = "#id")
    @Override
    public CartaoEntity editar(Long id, CartaoEditarRequest request) {
        var cartao = buscarPorId(id);

        CartaoMapper.mapCartaoEditar(request, cartao);
        cartaoRepository.save(cartao);
        return cartao;
    }

    @CacheEvict(cacheNames = "cartao", key = "#id")
    @Override
    public void deletar(Long id) {
        cartaoRepository.delete(buscarPorId(id));
    }

}
