package br.com.szella.gerenciadordecontas.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "compra_cartao")
public class CompraCartaoEntity implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_cartao_id_sequence_generator")
    @SequenceGenerator(name = "compra_cartao_id_sequence_generator", sequenceName = "compra_cartao_id_sequence")
    private Long id;

    private String agrupamento;
    private String nome;
    private BigDecimal valor;
    private Integer mes;
    private Integer ano;
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private CartaoEntity cartao;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
