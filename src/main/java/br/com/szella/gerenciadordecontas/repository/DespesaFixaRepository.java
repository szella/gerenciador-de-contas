package br.com.szella.gerenciadordecontas.repository;

import br.com.szella.gerenciadordecontas.dominio.despesaFixa.DespesaFixaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaFixaRepository extends JpaRepository<DespesaFixaEntity, Long> {
}
