package todo.app.domain.todo.appplication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo.app.domain.todo.domain.ToDo;
import todo.app.domain.todo.domain.ToDoRepository;
import todo.app.domain.todo.domain.ToDoSub;
import todo.app.domain.todo.domain.ToDoSubRepository;
import todo.app.domain.todo.pressentation.dto.ToDoSubRequest;
import todo.app.domain.todo.pressentation.dto.ToDoSubResponse;

@Service
@RequiredArgsConstructor
public class ToDoSubService {

    private final ToDoRepository toDoRepository;
    private final ToDoSubRepository toDoSubRepository;

    public ToDoSubResponse.ToDoSubFindById findById(Long id) {
        ToDoSub entity = toDoSubRepository.findById(id);
        return ToDoSubResponse.ToDoSubFindById.toDto(entity);
    }

    @Transactional
    public void save(Long parentId, ToDoSubRequest.ToDoSubSave dto) {
        ToDo parent = toDoRepository.findById(parentId);
        ToDoSub entity = ToDoSubRequest.ToDoSubSave.toEntity(dto, parent);
        toDoSubRepository.save(entity);
    }

    @Transactional
    public void put(Long id, ToDoSubRequest.ToDoSubPut dto) {
        ToDoSub entity = toDoSubRepository.findById(id);
        entity.update(dto.title(), dto.content());
    }

    @Transactional
    public void patchStatus(Long id, ToDoSubRequest.ToDoSubPatchStatus dto) {
        ToDoSub entity = toDoSubRepository.findById(id);
        entity.patchStatus(dto.status());
    }

    @Transactional
    public void delete(Long id) {
        toDoSubRepository.deleteById(id);
    }
}
