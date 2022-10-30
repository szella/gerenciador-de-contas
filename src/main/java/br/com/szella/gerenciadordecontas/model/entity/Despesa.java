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
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "despesa")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private BigDecimal valor;
    private Month mes;
    private Year ano;

    @Enumerated(EnumType.STRING)
    private TipoDespesaEnum tipoDespesaEnum;

    @ManyToOne
    private Cartao cartao;

}
