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
            responseCode = "404",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ApiResponse.class),
                examples = {
                    @ExampleObject(
                        name = "데이터를 찾을 수 없음",
                        description = "데이터를 찾을 수 없습니다. 요청 값을 확인해주세요",
                        value = """
                                    {
                                            "timestamp": "2025-02-01T04:02:51.412976300",
                                            "status": 404,
                                            "error": "... 데이터가 존재하지 않습니다.",
                                            "path": "/..."
                                    }
                                """
                    ),
                }
            )
        )
    }
)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotFoundExceptionDoc {
}
