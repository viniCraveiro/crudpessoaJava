package craveiro.vinicius.crudpessoa.domain.pessoa.validator;

import craveiro.vinicius.crudpessoa.domain.common.Validacao;
import craveiro.vinicius.crudpessoa.domain.common.exception.CpfException;
import craveiro.vinicius.crudpessoa.domain.pessoa.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class CpfValidador implements Validacao<Pessoa> {

    @Override
    public void validador(Pessoa entity) {
        if(entity.getCpf() == null || entity.getCpf().isEmpty()) throw new CpfException("CPF não informado.");

        entity.setCpf(entity.getCpf().replaceAll("[^0-9]", ""));

        if (entity.getCpf().length() != 11) {
            throw new CpfException("CPF deve conter 11 digitos numéricos.");
        }

        if (entity.getCpf().matches("(\\d)\\1{10}")) {
            throw new CpfException("CPF inválido.");
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (entity.getCpf().charAt(i) - '0') * (10 - i);
        }
        int primeiroDigitoVerificador = 11 - (soma % 11);
        if (primeiroDigitoVerificador > 9) {
            primeiroDigitoVerificador = 0;
        }

        if (primeiroDigitoVerificador != entity.getCpf().charAt(9) - '0') {
            throw new CpfException("Primeiro dígito verificador inválido.");
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (entity.getCpf().charAt(i) - '0') * (11 - i);
        }
        int segundoDigitoVerificador = 11 - (soma % 11);
        if (segundoDigitoVerificador > 9) {
            segundoDigitoVerificador = 0;
        }

        if (segundoDigitoVerificador != entity.getCpf().charAt(10) - '0') {
            throw new CpfException("Segundo dígito verificador inválido.");
        }
    }
}
