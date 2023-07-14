package craveiro.vinicius.crudpessoa.domain.pessoa;

import craveiro.vinicius.crudpessoa.domain.contato.Contato;
import craveiro.vinicius.crudpessoa.domain.entidade.Entidade;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PESSOA")
public class Pessoa extends Entidade {

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 11, nullable = false)
    private String cpf;

    private ZonedDateTime nascimento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<Contato> contatos = new ArrayList<>();

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, ZonedDateTime nascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ZonedDateTime getNascimento() {
        return nascimento;
    }

    public void setNascimento(ZonedDateTime nascimento) {
        this.nascimento = nascimento;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
}
