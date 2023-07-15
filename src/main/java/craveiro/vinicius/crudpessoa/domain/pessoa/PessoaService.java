package craveiro.vinicius.crudpessoa.domain.pessoa;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> listAll() {
        return this.pessoaRepository.findAll();
    }

    public Page<Pessoa> listAll(Pageable pageable) {
        return this.pessoaRepository.findAll(pageable);
    }

    public Pessoa findOneById(Long id) {
        return this.pessoaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Pessoa update(Long id, Pessoa editado) {
        Pessoa pessoa = findOneById(id);
        pessoa.setNome(editado.getNome());
        pessoa.setCpf(editado.getCpf());
        pessoa.setNascimento(editado.getNascimento());
        pessoa.setContatos(editado.getContatos());
        return this.pessoaRepository.save(pessoa);
    }

    public Pessoa create(Pessoa novaPessoa) {
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
}
