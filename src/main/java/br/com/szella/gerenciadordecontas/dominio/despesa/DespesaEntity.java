package br.com.szella.gerenciadordecontas.dominio.despesa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "despesa")
public class DespesaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "despesa_id_sequence_generator")
    @SequenceGenerator(name = "despesa_id_sequence_generator", sequenceName = "despesa_id_sequence")
    private Long id;

    private String nome;
}
