package br.com.szella.gerenciadordecontas.repository;

import br.com.szella.gerenciadordecontas.dominio.cartao.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {
}
