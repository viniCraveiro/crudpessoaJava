package craveiro.vinicius.crudpessoa.view.pessoa;

import craveiro.vinicius.crudpessoa.domain.pessoa.Pessoa;
import craveiro.vinicius.crudpessoa.domain.pessoa.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtem lista paginada de pessoa", tags = "PESSOA")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pessoa encontrada.", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida"),
            @ApiResponse(responseCode = "404", description = "Não encontrado.")
    })
    @GetMapping
    public ResponseEntity<Page<Pessoa>> listAll(Pageable pageable) {
        Page<Pessoa> pessoas = pessoaService.listAll(pageable);
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    @Operation(summary = "Obtem pessoa por ID", tags = "PESSOA")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada.", content = {@Content(mediaType =
                    "application/json", schema = @Schema(implementation = Pessoa.class))}),
            @ApiResponse(responseCode = "404", description = "Não encontrado.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findOneById(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.findOneById(id);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @Operation(summary = "Edita a pessoa por ID", tags = "PESSOA")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa editada.", content = {@Content(mediaType =
                    "application/json", schema = @Schema(implementation = Pessoa.class))}),
            @ApiResponse(responseCode = "404", description = "Não encontrado.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoaEditada) {
        Pessoa pessoa = pessoaService.update(id, pessoaEditada);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @Operation(summary = "Inseri uma pessoa", tags = "PESSOA")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa inserida com sucesso.", content = {@Content(mediaType =
                    "application/json", schema = @Schema(implementation = Pessoa.class))}),
            @ApiResponse(responseCode = "404", description = "Pessoa não inserida.")
    })
    @PostMapping()
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa novaPessoa) {
        Pessoa pessoa = pessoaService.create(novaPessoa);
        return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }

    @Operation(summary = "Deleta uma pessoa por ID", tags = "PESSOA")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pessoa deletada."),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida"),
            @ApiResponse(responseCode = "404", description = "Não encontrado.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(pessoaService.delete(id), HttpStatus.NO_CONTENT);
    }

}
