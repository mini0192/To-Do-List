package todo.app.domain.todo.appplication;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo.app.domain.todo.domain.ToDo;
import todo.app.domain.todo.domain.ToDoRepository;
import todo.app.domain.todo.pressentation.SortBy;
import todo.app.domain.todo.pressentation.Status;
import todo.app.domain.todo.pressentation.dto.ToDoRequest;
import todo.app.domain.todo.pressentation.dto.ToDoResponse;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public Page<ToDoResponse.ToDoFindAll> findAll(int page, SortBy sort, Status status) {
        Pageable pageable = PageRequest.of(page, 30);
        Page<ToDo> entityPage = toDoRepository.findAll(pageable, sort, status);
        return entityPage.map(ToDoResponse.ToDoFindAll::toDto);
    }

    public ToDoResponse.ToDoFindById findById(Long id) {
        ToDo entity = toDoRepository.findById(id);
        return ToDoResponse.ToDoFindById.toDto(entity);
    }

    @Transactional
    public void save(ToDoRequest.ToDoSave dto) {
        ToDo entity = ToDoRequest.ToDoSave.toEntity(dto);
        toDoRepository.save(entity);
    }

    @Transactional
    public void put(Long id, ToDoRequest.ToDoPut dto) {
        ToDo entity = toDoRepository.findById(id);
        entity.update(dto.title(), dto.content(), dto.priority(), dto.unixTime());
        toDoRepository.update(entity);
    }

    @Transactional
    public void patchStatus(Long id, ToDoRequest.ToDoPatchStatus dto) {
        ToDo entity = toDoRepository.findById(id);
        entity.patchStatus(dto.status());
        toDoRepository.update(entity);
    }

    @Transactional
    public void delete(Long id) {
        toDoRepository.deleteById(id);
    }
}
