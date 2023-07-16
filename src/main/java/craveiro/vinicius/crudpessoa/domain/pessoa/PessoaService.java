package craveiro.vinicius.crudpessoa.domain.pessoa;

import craveiro.vinicius.crudpessoa.domain.common.Validacao;
import craveiro.vinicius.crudpessoa.domain.contato.Contato;
import craveiro.vinicius.crudpessoa.domain.contato.ContatoRepository;
import craveiro.vinicius.crudpessoa.domain.contato.validator.EmailValidador;
import craveiro.vinicius.crudpessoa.domain.entidade.Entidade;
import craveiro.vinicius.crudpessoa.domain.pessoa.validator.CpfValidador;
import craveiro.vinicius.crudpessoa.domain.pessoa.validator.DataNascimentoValidador;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    private final ContatoRepository contatoRepository;

    private final CpfValidador cpfValidador;

    private final EmailValidador emailValidador;
    private final DataNascimentoValidador nascimentoValidador;

    public PessoaService(PessoaRepository pessoaRepository, ContatoRepository contatoRepository, CpfValidador cpfValidador,
                         EmailValidador emailValidador, DataNascimentoValidador nascimentoValidador) {
        this.pessoaRepository = pessoaRepository;
        this.contatoRepository = contatoRepository;
        this.cpfValidador = cpfValidador;
        this.emailValidador = emailValidador;
        this.nascimentoValidador = nascimentoValidador;
    }

    public List<Pessoa> listAll() {
        return this.pessoaRepository.findAll();
    }

    public Page<Pessoa> listAll(Pageable pageable) {
        return this.pessoaRepository.findAll(pageable);
    }

    public Pessoa findOneById(Long id) {
        return this.pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Identificador não encontrado."));
    }

    public Pessoa update(Long id, Pessoa editado) {
        validadores(editado);
        Pessoa pessoa = findOneById(id);
        pessoa.setNome(editado.getNome());
        pessoa.setCpf(editado.getCpf());
        pessoa.setNascimento(editado.getNascimento());

        List<Contato> existentes = pessoa.getContatos();
        List<Contato> atualizados = editado.getContatos();

        existentes.removeIf(contato -> !atualizados.contains(contato));

        for (Contato atualizado : atualizados) {
            if (existentes.contains(atualizado)) {
                Contato contato = existentes.get(existentes.indexOf(atualizado));
                contato.setNome(atualizado.getNome());
                contato.setEmail(atualizado.getEmail());
                contato.setTelefone(atualizado.getTelefone());
                atualizados.add(contato);
            } else {
                pessoa.setContatos(atualizados);
            }
        }
        return this.pessoaRepository.save(pessoa);
    }

    public Pessoa create(Pessoa novaPessoa) {
        validadores(novaPessoa);
        return pessoaRepository.save(novaPessoa);
    }

    public boolean delete(Long id) {
        Pessoa pessoa = findOneById(id);
        if (pessoa != null) {
            pessoaRepository.delete(pessoa);
            return true;
        } else {
            return false;
        }
    }

    private void validadores(Pessoa pessoa) {
        cpfValidador.validador(pessoa);
        nascimentoValidador.validador(pessoa);
        for (Contato contato : pessoa.getContatos()) {
            emailValidador.validador(contato);
        }
    }

}
