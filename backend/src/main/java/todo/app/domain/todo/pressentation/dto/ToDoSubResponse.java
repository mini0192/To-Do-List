package todo.app.domain.todo.pressentation.dto;

import lombok.Builder;
import todo.app.domain.todo.domain.ToDoSub;
import todo.app.domain.todo.pressentation.Status;

public class ToDoSubResponse {

    @Builder
    public record ToDoSubFindById(
            Long id,
            String title,
            String content,
            Status status
    ) {
        public static ToDoSubFindById toDto(ToDoSub entity) {

            return ToDoSubFindById.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .content(entity.getContent())
                    .status(entity.getStatus())
                    .build();
        }
    }
}
