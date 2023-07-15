package craveiro.vinicius.crudpessoa.view.pessoa;

import craveiro.vinicius.crudpessoa.domain.pessoa.Pessoa;
import craveiro.vinicius.crudpessoa.domain.pessoa.PessoaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v0/pessoa")
public class PessoaRestController {
    private final PessoaService pessoaService;

    public PessoaRestController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<Page<Pessoa>> listAll(Pageable pageable) {
        Page<Pessoa> pessoas = pessoaService.listAll(pageable);
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findOneById(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.findOneById(id);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoaEditada) {
        Pessoa pessoa = pessoaService.update(id, pessoaEditada);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa novaPessoa) {
        Pessoa pessoa = pessoaService.create(novaPessoa);
        return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(pessoaService.delete(id), HttpStatus.OK);
    }

}
