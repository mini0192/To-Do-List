package todo.app.domain.todo.pressentation.dto;

import lombok.Builder;
import todo.app.domain.todo.domain.ToDo;
import todo.app.domain.todo.domain.ToDoSub;
import todo.app.domain.todo.pressentation.PriorityType;
import todo.app.domain.todo.pressentation.Status;

import java.util.List;

public class ToDoResponse {

    @Builder
    public record ToDoFindAll(
            Long id,
            String title,
            PriorityType priority,
            Status status,
            Long unixTime
    ) {
        public static ToDoFindAll toDto(ToDo entity) {
            return ToDoFindAll.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .priority(entity.getPriority())
                    .status(entity.getStatus())
                    .unixTime(entity.getUnixTime())
                    .build();
        }
    }

    @Builder
    public record ToDoFindById(
            Long id,
            String title,
            String content,
            PriorityType priority,
            Status status,
            Long unixTime,
            List<ToDoSubInfo> sub
    ) {
        @Builder
        private record ToDoSubInfo(
                Long id,
                String title,
                Status status
        ) {}
        public static ToDoFindById toDto(ToDo entity) {

            List<ToDoSub> subs = entity.getToDoSubs();
            List<ToDoSubInfo> subInfos = subs.stream().map(s ->
                ToDoSubInfo.builder()
                        .id(s.getId())
                        .title(s.getTitle())
                        .status(s.getStatus())
                        .build()
            ).toList();

            return ToDoFindById.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .content(entity.getContent())
                    .priority(entity.getPriority())
                    .status(entity.getStatus())
                    .unixTime(entity.getUnixTime())
                    .sub(subInfos)
                    .build();
        }
    }
}
