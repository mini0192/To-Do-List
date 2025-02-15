package todo.app.domain.todo.pressentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import todo.app.domain.todo.domain.ToDo;
import todo.app.domain.todo.pressentation.PriorityType;
import todo.app.domain.todo.pressentation.Status;

public class ToDoRequest {
    public record ToDoSave(
            @NotBlank
            @Size(min = 1, max = 50)
            String title,

            @NotBlank
            @Size(min = 1, max = 1000)
            String content,

            PriorityType priority,

            @NotNull
            Long unixTime
    ) {
        public static ToDo toEntity(ToDoSave dto) {
            PriorityType priorityType = dto.priority();
            if(priorityType == null) priorityType = PriorityType.NONE;
            return ToDo.builder()
                    .title(dto.title())
                    .content(dto.content())
                    .priority(priorityType)
                    .unixTime(dto.unixTime())
                    .build();
        }
    }


    public record ToDoPut(
            @NotBlank
            @Size(min = 1, max = 50)
            String title,

            @NotBlank
            @Size(min = 1, max = 1000)
            String content,

            PriorityType priority,

            @NotNull
            Long unixTime
    ) {}


    public record ToDoPatchStatus(
            @NotNull
            Status status
    ) {}
}
