package craveiro.vinicius.crudpessoa.domain.pessoa.validator;

import craveiro.vinicius.crudpessoa.domain.common.exception.CpfException;
import craveiro.vinicius.crudpessoa.domain.pessoa.Pessoa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpfValidadorTest {

    CpfValidador validador = new CpfValidador();


    @Test
    void validador() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("682.520.690-70");
        validador.validador(pessoa);
    }

    @Test
    void CpfComQuantidadeDigitosInvalido() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("123");
        CpfException tresDigitos = assertThrows(CpfException.class, () -> validador.validador(pessoa));
        assertEquals("CPF deve conter 11 digitos numéricos.", tresDigitos.getMessage());
        pessoa.setCpf("123456789012345");
        CpfException quinzeDigitos = assertThrows(CpfException.class, () -> validador.validador(pessoa));
        assertEquals("CPF deve conter 11 digitos numéricos.", quinzeDigitos.getMessage());

        pessoa.setCpf(null);
        CpfException cpfNulo = assertThrows(CpfException.class, () -> validador.validador(pessoa));
        assertEquals("CPF não informado.", cpfNulo.getMessage());
    }

    @Test
    void CpfComLetras() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("ABCDEFGHIJK");
        CpfException cpfComLetra = assertThrows(CpfException.class, () -> validador.validador(pessoa));
        assertEquals("CPF deve conter 11 digitos numéricos.", cpfComLetra.getMessage());
    }

    @Test
    void VerificaPrimeiroDigitoVerificador() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("682.520.690-00");
        CpfException cpfComLetra = assertThrows(CpfException.class, () -> validador.validador(pessoa));
        assertEquals("Primeiro dígito verificador inválido.", cpfComLetra.getMessage());
    }

    @Test
    void VerificaSegundoDigitoVerificador() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("682.520.690-71");
        CpfException cpfComLetra = assertThrows(CpfException.class, () -> validador.validador(pessoa));
        assertEquals("Segundo dígito verificador inválido.", cpfComLetra.getMessage());
    }

    @Test
    void VerificaDigitosIguais() {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf("000.000.000-00");
        CpfException cpfComLetra = assertThrows(CpfException.class, () -> validador.validador(pessoa));
        assertEquals("CPF inválido.", cpfComLetra.getMessage());
    }
}