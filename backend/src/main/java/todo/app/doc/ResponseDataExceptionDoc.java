package todo.app.doc;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApiResponses(
    value = {
        @ApiResponse(
            responseCode = "400",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = {
                    @ExampleObject(
                        name = "유효성 검사에 실패함",
                        description = "유효성 검사에 실패 예외입니다. 요청 값을 확인해주세요",
                        value = """
                                    {
                                          "timestamp": "2025-02-01T04:05:48.207059",
                                          "status": 400,
                                          "error": "...",
                                          "path": "/..."
                                    }
                                """
                    ),
                    @ExampleObject(
                        name = "필드 타입 불일치",
                        description = "필드 타입 불일치 예외입니다. 필드 타입을 확인해주세요",
                        value = """
                                    {
                                          "timestamp": "2025-02-01T04:05:48.207059",
                                          "status": 400,
                                          "error": "필드 타입이 일치하지 않습니다.",
                                          "path": "/..."
                                    }
                                """
                    )
                }
            )
        )
    }
)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseDataExceptionDoc {
}
