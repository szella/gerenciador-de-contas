package br.com.szella.gerenciadordecontas.repository;

import br.com.szella.gerenciadordecontas.dominio.recebimento.RecebimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecebimentoRepository extends JpaRepository<RecebimentoEntity, Long> {
}
