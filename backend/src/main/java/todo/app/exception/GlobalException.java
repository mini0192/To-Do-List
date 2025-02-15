package todo.app.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    // 404
    @ExceptionHandler(NoHandlerFoundException.class)
    public void handleNotFound(NoHandlerFoundException ex, HttpServletRequest request, HttpServletResponse response) {
        String message = "해당 경로를 찾을 수 없습니다.";
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                request.getRequestURI(),
                HttpStatus.NOT_FOUND.value(),
                message
        );
        exceptionResponse.logger();
        ExceptionResponseSender.run(response, exceptionResponse);
    }

    // 405
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public void handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex, HttpServletRequest request, HttpServletResponse response) {
        String message = "해당 요청을 처리 할 수 없습니다.";
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                request.getRequestURI(),
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                message
        );
        exceptionResponse.logger();
        ExceptionResponseSender.run(response, exceptionResponse);
    }

    // 요청을 처리할 수 없음
    @ExceptionHandler(RequestException.class)
    public void handleRequestException(RequestException ex, HttpServletRequest request, HttpServletResponse response) {
        String message = ex.getMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                message
        );
        exceptionResponse.logger();
        ExceptionResponseSender.run(response, exceptionResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void handleValidationExceptions(ConstraintViolationException ex, HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );

        String message = errors.toString();

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                message
        );
        exceptionResponse.logger();
        ExceptionResponseSender.run(response, exceptionResponse);
    }

    // 값 누락
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handlerMissingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletRequest request, HttpServletResponse response) {
        String parameterName = ex.getParameterName();
        String message = MessageFormat.format("{0}의 값이 누락되었습니다.", parameterName);

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                message
        );
        exceptionResponse.logger();
        ExceptionResponseSender.run(response, exceptionResponse);
    }

    // 유효성 검사 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream()
                .map(error -> error.getField() + ", " + error.getDefaultMessage())
                .toList().toString();

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                message
        );
        exceptionResponse.logger();
        ExceptionResponseSender.run(response, exceptionResponse);
    }

    // 매개변수 타입이 다름
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public void handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request, HttpServletResponse response) {
        String message = ex.getMessage();

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                message
        );
        exceptionResponse.logger();
        ExceptionResponseSender.run(response, exceptionResponse);
    }

    // 필드 타입 불 일치
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handlerHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request, HttpServletResponse response) {
        String message = "필드 타입이 일치하지 않습니다.";

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                message
        );
        exceptionResponse.logger();
        ExceptionResponseSender.run(response, exceptionResponse);
    }

    // 데이터가 없음
    @ExceptionHandler(NotFoundDataException.class)
    public void handleNotFoundDataException(NotFoundDataException ex, HttpServletRequest request, HttpServletResponse response) {
        String message = ex.getMessage();

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                request.getRequestURI(),
                HttpStatus.NOT_FOUND.value(),
                message
        );
        exceptionResponse.logger();
        ExceptionResponseSender.run(response, exceptionResponse);
    }

    // 무결성 제약 조건 위반
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handlerDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request, HttpServletResponse response) {
        String message = ex.getMessage();

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message
        );
        exceptionResponse.logger();
        ExceptionResponseSender.run(response, exceptionResponse);
    }

    // 모든 예외
    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex, HttpServletRequest request, HttpServletResponse response){
        String message = ex.getMessage();

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                request.getRequestURI(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message
        );
        exceptionResponse.logger();
        ExceptionResponseSender.run(response, exceptionResponse);
    }
}
