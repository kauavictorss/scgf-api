package scgf.api.infrastructure.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Void> tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<DtoErro> tratarErroRegraDeNegocio(RegraDeNegocioException exception) {
        return ResponseEntity.badRequest().body(new DtoErro(exception.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DtoErro> tratarErroArgumentoInvalido(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(new DtoErro(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DtoErroValidacao>> tratarErro400(MethodArgumentNotValidException exception) {
        var erros = exception.getFieldErrors()
            .stream()
            .map(DtoErroValidacao::new)
            .toList();

        return ResponseEntity.badRequest().body(erros);
    }

    public record DtoErro(String mensagem) {
    }

    public record DtoErroValidacao(String campo, String mensagem) {

        public DtoErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
