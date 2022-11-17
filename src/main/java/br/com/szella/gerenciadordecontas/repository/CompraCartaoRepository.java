package br.com.szella.gerenciadordecontas.repository;

import br.com.szella.gerenciadordecontas.model.entity.CompraCartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraCartaoRepository extends JpaRepository<CompraCartaoEntity, Long> {
}
