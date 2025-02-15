package todo.app.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionResponseSender {

    private ExceptionResponseSender() {}

    public static void run(HttpServletResponse response, ExceptionResponse exceptionResponse) {
        final ObjectMapper mapper = new ObjectMapper();

        try {
            String json = mapper.writeValueAsString(exceptionResponse);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(json);
            response.setStatus(exceptionResponse.getStatus());
        } catch (Exception e) {
            log.error("응답 작성 실패: {}", e.getMessage(), e);
        }
    }
}
