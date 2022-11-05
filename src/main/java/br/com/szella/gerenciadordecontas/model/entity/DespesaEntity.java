package br.com.szella.gerenciadordecontas.model.entity;

import br.com.szella.gerenciadordecontas.enums.TipoDespesaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "despesa")
public class DespesaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_jpa_sequence_generator")
    @SequenceGenerator(name = "posts_jpa_sequence_generator", sequenceName = "despesa_id_sequence")
    private Long id;

    private String nome;
    private BigDecimal valor;
    private Integer mes;
    private Integer ano;

    @Enumerated(EnumType.STRING)
    private TipoDespesaEnum tipoDespesaEnum;

    @ManyToOne
    private CartaoEntity cartao;

}
