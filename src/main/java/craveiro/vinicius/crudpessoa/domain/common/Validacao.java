package craveiro.vinicius.crudpessoa.domain.common;

public interface Validacao<T> {
    void validador(T entity);
}
