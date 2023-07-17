package craveiro.vinicius.crudpessoa.domain.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice()
@Slf4j
public class APIExceptionTranslator {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage processNullPointerException(NullPointerException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), "Entity not fount", ex.getStackTrace()[0], ex.getStackTrace()[1]);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage process(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String description =
                fieldErrors.stream().map(s -> String.format("Campo %s: %s", s.getField(), s.getDefaultMessage())).collect(Collectors.joining("; "));
        return new ErrorMessage(ex.getClass().getSimpleName(), description, fieldErrors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage process(IllegalArgumentException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getLocalizedMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage process(HttpMessageNotReadableException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getLocalizedMessage());
    }

    @ExceptionHandler(EmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage process(EmailException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(DataNascimentoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage process(DataNascimentoException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(CpfException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage process(CpfException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage process(DataIntegrityViolationException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), "Constraint violada.", ex.getLocalizedMessage());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage process(EmptyResultDataAccessException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getLocalizedMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage process(NoSuchElementException ex) {
        return new ErrorMessage(ex.getClass().getSimpleName(), ex.getLocalizedMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage processGeneralException(Throwable ex) {
        log.info(Arrays.asList(ex.getStackTrace()).toString());
        return new ErrorMessage(
                ex.getClass().getSimpleName(),
                ex.getLocalizedMessage(),
                Arrays.stream(ex.getStackTrace())
                        .filter(s -> s.getClassName().startsWith("br.com.produtec"))
                        .limit(3L)
                        .map(s -> s.getClassName() + ": " + s.getLineNumber())
                        .collect(Collectors.toList()));
    }
}
