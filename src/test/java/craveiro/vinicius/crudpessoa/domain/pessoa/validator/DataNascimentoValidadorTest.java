package craveiro.vinicius.crudpessoa.domain.pessoa.validator;

import craveiro.vinicius.crudpessoa.domain.common.exception.DataNascimentoException;
import craveiro.vinicius.crudpessoa.domain.pessoa.Pessoa;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class DataNascimentoValidadorTest {

    DataNascimentoValidador validador = new DataNascimentoValidador();

    @Test
    void validador() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNascimento(ZonedDateTime.now());
        validador.validador(pessoa);

        pessoa.setNascimento(null);
        DataNascimentoException nascimentoNulo = assertThrows(DataNascimentoException.class, () -> validador.validador(pessoa));
        assertEquals("Data de nascimento não pode ser nula.", nascimentoNulo.getMessage());

        pessoa.setNascimento(ZonedDateTime.of(LocalDateTime.MAX,ZoneId.systemDefault()));
        DataNascimentoException max = assertThrows(DataNascimentoException.class, () -> validador.validador(pessoa));
        assertEquals("Data de nascimento deve ser menor que a data atual.", max.getMessage());

    }
}