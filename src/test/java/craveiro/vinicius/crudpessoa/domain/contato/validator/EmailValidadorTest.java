package craveiro.vinicius.crudpessoa.domain.contato.validator;

import craveiro.vinicius.crudpessoa.domain.common.exception.EmailException;
import craveiro.vinicius.crudpessoa.domain.contato.Contato;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidadorTest {

    EmailValidador validate = new EmailValidador();

    @Test
    void validador() {
        Contato contato = new Contato();

        contato.setEmail(null);
        EmailException emailNulo = assertThrows(EmailException.class, () -> validate.validador(contato));
        assertEquals("Email é obrigatório.", emailNulo.getMessage());

        contato.setEmail("vinicius");
        EmailException email = assertThrows(EmailException.class, () -> validate.validador(contato));
        assertEquals("Email inválido.", email.getMessage());

        contato.setEmail("vinicius@com");
        EmailException emailComArrobaInvalido = assertThrows(EmailException.class, () -> validate.validador(contato));
        assertEquals("Email inválido.", emailComArrobaInvalido.getMessage());

        contato.setEmail("@email.com.br");
        EmailException email3 = assertThrows(EmailException.class, () -> validate.validador(contato));
        assertEquals("Email inválido.", email3.getMessage());

        contato.setEmail("vinicius@com.br");
        validate.validador(contato);
    }
}