package br.com.szella.gerenciadordecontas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagemDeErro {
    NAO_ENCONTRADO("Registro não localizado."),
    SEM_TIPO_DESPESA("Insira um tipo de despesa.");

    private String mensagem;
}
