package br.com.szella.gerenciadordecontas.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recebimento")
public class RecebimentoEntity implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recebimento_id_sequence_generator")
    @SequenceGenerator(name = "recebimento_id_sequence_generator", sequenceName = "recebimento_id_sequence")
    private Long id;

    private String agrupamento;
    private String nome;
    private BigDecimal valor;
    private Integer mes;
    private Integer ano;
    private LocalDateTime dataCadastro;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
