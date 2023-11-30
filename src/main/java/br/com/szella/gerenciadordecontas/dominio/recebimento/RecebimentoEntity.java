package br.com.szella.gerenciadordecontas.dominio.recebimento;

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
