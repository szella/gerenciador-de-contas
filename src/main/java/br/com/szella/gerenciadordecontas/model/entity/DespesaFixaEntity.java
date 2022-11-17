package br.com.szella.gerenciadordecontas.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "despesa_fixa")
public class DespesaFixaEntity implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_jpa_sequence_generator")
    @SequenceGenerator(name = "posts_jpa_sequence_generator", sequenceName = "despesa_fixa_id_sequence")
    private Long id;

    private BigDecimal valor;
    private Integer mes;
    private Integer ano;

    @ManyToOne
    private DespesaEntity despesa;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}