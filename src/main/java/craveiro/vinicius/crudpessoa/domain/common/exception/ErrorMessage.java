package craveiro.vinicius.crudpessoa.domain.common.exception;

import lombok.Getter;

@Getter
public class ErrorMessage {
    private final String code;
    private final String description;
    private final Object[] extra;

    public ErrorMessage(String code, String description, Object... extra) {
        this.code = code;
        this.description = description;
        this.extra = extra;
    }
}
