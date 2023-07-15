package craveiro.vinicius.crudpessoa.domain.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class CrudPaginadoReturn<T> {
    private long quantidade;
    private long total;
    private long primeiroResultado;
    private long tamanhoPagina;
    private boolean temMais;
    private List<T> valores;

    public CrudPaginadoReturn() {
        valores = Collections.emptyList();
        quantidade = 0;
        total = 0;
        primeiroResultado = 1;
        temMais = false;
    }

    public CrudPaginadoReturn(List<T> valores) {
        this.valores = valores;
        this.quantidade = valores.size();
        this.total = valores.size();
        this.primeiroResultado = 1;
        this.temMais = false;
    }

    // TODO: 14/07/2023 Paginar por conta futuramente.

}
