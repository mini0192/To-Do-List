package todo.app.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Getter
public class ExceptionResponse {

    private final String timestamp = LocalDateTime.now().toString();
    private final int status;
    private final String error;
    private final String path;

    public ExceptionResponse(String path, int httpStatus, String message) {
        this.status = httpStatus;
        this.error = message;
        this.path = path;
    }

    public void logger() {
        log.error("Path: {}, Status: {}, Exception: {}", this.path, this.status, this.error);
    }
}
