package craveiro.vinicius.crudpessoa.domain.common;

import craveiro.vinicius.crudpessoa.domain.common.exception.InvalidPageParameterException;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public abstract class CrudRepository<T> {

    protected EntityManager em;

    public CrudPaginadoReturn<T> paginadoList(long primeiro, long tamanho) {
        if (primeiro < 1) {
            throw new InvalidPageParameterException("O parametro PRIMEIRO deve ser maior que 0");
        }

        CrudPaginadoReturn<T> toReturn = new CrudPaginadoReturn<>();

        toReturn.setTamanhoPagina(tamanho);
//        toReturn.setTotal(count);
//        Query nativeQuery = em.createNativeQuery(sql);
//        List<Tuple> resultList = nativeQuery.getResultList();
//        if (!resultList.isEmpty()){
//        toReturn.setTemMais(resultList.size() > tamanho);
//        if (toReturn.isTemMais())
//            resultList.remove(resultList.size() - 1);
//        toReturn.setQuantidade(resultList.size());
//        toReturn.setPrimeiroResultado(first);
        toReturn.setValores(new ArrayList<>());

        return toReturn;
    }

    public int contador(String id) {
        return 0;
    }
}
