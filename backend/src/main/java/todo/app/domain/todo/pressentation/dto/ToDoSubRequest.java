package todo.app.domain.todo.pressentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import todo.app.domain.todo.domain.ToDo;
import todo.app.domain.todo.domain.ToDoSub;
import todo.app.domain.todo.pressentation.Status;

public class ToDoSubRequest {
    public record ToDoSubSave(
            @NotBlank
            @Size(min = 1, max = 50)
            String title,

            @NotBlank
            @Size(min = 1, max = 1000)
            String content
    ) {
        public static ToDoSub toEntity(ToDoSubSave dto, ToDo parent) {
            return ToDoSub.builder()
                    .title(dto.title())
                    .content(dto.content())
                    .todo(parent)
                    .build();
        }
    }

    public record ToDoSubPut(
            @Size(min = 1, max = 50)
            String title,

            @Size(min = 1, max = 1000)
            String content
    ) {}

    public record ToDoSubPatchStatus(
            @NotNull
            Status status
    ) {}
}
