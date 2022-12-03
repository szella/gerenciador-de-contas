package br.com.szella.gerenciadordecontas.repository;

import br.com.szella.gerenciadordecontas.model.entity.CompraCartaoEntity;
import br.com.szella.gerenciadordecontas.model.entity.RecebimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecebimentoRepository extends JpaRepository<RecebimentoEntity, Long> {
}
