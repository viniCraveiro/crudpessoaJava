package craveiro.vinicius.crudpessoa.domain.contato;

import craveiro.vinicius.crudpessoa.domain.entidade.Entidade;
import craveiro.vinicius.crudpessoa.domain.pessoa.Pessoa;
import jakarta.persistence.*;

@Entity
@Table(name = "CONTATO")
public class Contato extends Entidade {
    @Column(length = 100, nullable = false)
    private String nome;
    @Column(length = 13, nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "pessoa_fk")
    private Pessoa pessoa;

    public Contato() {
    }

    public Contato(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
