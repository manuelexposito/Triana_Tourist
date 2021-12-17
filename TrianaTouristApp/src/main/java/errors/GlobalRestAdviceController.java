package errors;

import errors.exceptions.EntityNotFoundException;
import errors.model.ApiError;
import errors.model.ApiSubError;
import errors.model.ApiValidationSubError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestAdviceController extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.buildApiError(ex, status, request, new ArrayList<>());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return buildApiError(ex, status, request,
                ex.getFieldErrors().stream().map(
                        e -> ApiValidationSubError.builder()
                                .objeto(e.getObjectName())
                                .campo(e.getField())
                                .valorRechazado(e.getRejectedValue())
                                .mensaje(e.getDefaultMessage())
                                .build()
                ).collect(Collectors.toList()));
    }



    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(EntityNotFoundException ex, WebRequest request){

        return this.buildApiError(ex, HttpStatus.NOT_FOUND, request, new ArrayList<>());

    }



    private ResponseEntity<Object> buildApiError(Exception exception, HttpStatus status, WebRequest request, List<ApiSubError> subErrorList){

        ApiError error = ApiError.builder()
                .estado(status)
                .codigo(status.value())
                .ruta(((ServletWebRequest) request).getRequest().getRequestURI())
                .mensaje(exception.getMessage())
                .apiSubErrors(subErrorList)
                .build();

        return ResponseEntity.status(status).body(error);
    }





}
