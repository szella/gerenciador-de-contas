package br.com.szella.gerenciadordecontas.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cartao")
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;

}
