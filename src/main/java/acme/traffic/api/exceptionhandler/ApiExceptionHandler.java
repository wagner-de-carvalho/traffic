package acme.traffic.api.exceptionhandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import acme.traffic.domain.exception.NegocioException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(NegocioException.class)
    public ProblemDetail handleNegocio(NegocioException e) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("https://traffic.com/errors/business-rule"));
        return problemDetail;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolation(DataIntegrityViolationException e) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Recurso está em uso");
        problemDetail.setType(URI.create("https:/traffic.com/errors/resource-in-use"));
        return problemDetail;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais campos estão inválidos");
        problemDetail.setType(URI.create("https:/traffic.com/errors/invalid-fields"));

        Map<String, String> fields = exception.getBindingResult().getAllErrors()
                .stream()
                .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(),
                        objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));

        problemDetail.setProperty("fields", fields);

        return handleExceptionInternal(exception, problemDetail, headers, status, request);
    }

}
