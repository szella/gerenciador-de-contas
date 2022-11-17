package br.com.szella.gerenciadordecontas.repository;

import br.com.szella.gerenciadordecontas.model.entity.DespesaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<DespesaEntity, Long> {
}
