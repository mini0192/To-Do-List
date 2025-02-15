package todo.app.domain.todo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import todo.app.domain.todo.pressentation.PriorityType;
import todo.app.domain.todo.pressentation.Status;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = @Index(name = "idx_unix_time", columnList = "unixTime"))
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PriorityType priority;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.NOT_STARTED;

    @Column(nullable = false)
    private Long unixTime;

    @OneToMany(mappedBy = "todo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    List<ToDoSub> toDoSubs = new ArrayList<>();

    public void update(String title, String content, PriorityType priority, Long unixTime) {
        if(title != null && !title.isEmpty()) this.title = title;
        if(content != null && !content.isEmpty()) this.content = content;
        if(priority != null) this.priority = priority;
        if(unixTime != null) this.unixTime = unixTime;
    }

    public void patchStatus(Status status) {
        this.status = status;
    }
}
