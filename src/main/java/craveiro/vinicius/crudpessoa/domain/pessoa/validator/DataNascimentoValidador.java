package craveiro.vinicius.crudpessoa.domain.pessoa.validator;

import craveiro.vinicius.crudpessoa.domain.common.Validacao;
import craveiro.vinicius.crudpessoa.domain.common.exception.DataNascimentoException;
import craveiro.vinicius.crudpessoa.domain.pessoa.Pessoa;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class DataNascimentoValidador implements Validacao<Pessoa> {

    @Override
    public void validador(Pessoa entity) {
        if (entity.getNascimento() == null) throw new DataNascimentoException("Data de nascimento não pode ser nula.");
        if (entity.getNascimento().isAfter(ZonedDateTime.now()))
            throw new DataNascimentoException("Data de nascimento deve ser menor que a data atual.");
    }
}
