package br.com.szella.gerenciadordecontas.service.impl;

import br.com.szella.gerenciadordecontas.enums.MensagemDeErro;
import br.com.szella.gerenciadordecontas.exception.DBException;
import br.com.szella.gerenciadordecontas.mapper.CartaoMapper;
import br.com.szella.gerenciadordecontas.model.entity.Cartao;
import br.com.szella.gerenciadordecontas.model.request.CartaoEditarRequest;
import br.com.szella.gerenciadordecontas.model.request.CartaoSalvarRequest;
import br.com.szella.gerenciadordecontas.repository.CartaoRepository;
import br.com.szella.gerenciadordecontas.service.CartaoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartaoServiceImpl implements CartaoService {
    private final CartaoRepository cartaoRepository;

    @Override
    public List<Cartao> listar() {
        return cartaoRepository.findAll();
    }

    @Override
    public Cartao buscarPorId(Long id) {
        return Optional
                .of(cartaoRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElseThrow(() -> new DBException(MensagemDeErro.NAO_ENCONTRADO.getMensagem()));
    }

    @Override
    public Cartao salvar(CartaoSalvarRequest request) {
        return cartaoRepository.save(CartaoMapper.mapCartaoSalvar(request));
    }

    @Override
    public Cartao editar(Long id, CartaoEditarRequest request) {
        var cartao = buscarPorId(id);

        CartaoMapper.mapCartaoEditar(request, cartao);
        cartaoRepository.save(cartao);
        return cartao;
    }

    @Override
    public void deletar(Long id) {
        cartaoRepository.delete(buscarPorId(id));
    }

}
