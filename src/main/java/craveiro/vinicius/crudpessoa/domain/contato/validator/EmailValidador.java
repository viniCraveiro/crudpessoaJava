package craveiro.vinicius.crudpessoa.domain.contato.validator;

import craveiro.vinicius.crudpessoa.domain.common.Validacao;
import craveiro.vinicius.crudpessoa.domain.common.exception.EmailException;
import craveiro.vinicius.crudpessoa.domain.contato.Contato;
import org.springframework.stereotype.Component;

@Component
public class EmailValidador implements Validacao<Contato> {
    @Override
    public void validador(Contato entity) {
        if (entity.getEmail() == null || entity.getEmail().isEmpty()) {
            throw new EmailException("Email é obrigatório.");
        } else if (!entity.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new EmailException("Email inválido.");
        }
    }
}
