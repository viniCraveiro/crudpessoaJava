package craveiro.vinicius.crudpessoa.domain.entidade;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entidade implements IEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected Entidade() {
    }

    protected Entidade(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
