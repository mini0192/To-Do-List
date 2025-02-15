package todo.app.domain.todo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import todo.app.domain.todo.pressentation.Status;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToDoSub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.NOT_STARTED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ToDo todo;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void patchStatus(Status status) {
        this.status = status;
    }
}
