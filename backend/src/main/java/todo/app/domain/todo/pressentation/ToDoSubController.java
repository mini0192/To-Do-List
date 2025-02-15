package todo.app.domain.todo.pressentation;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo.app.doc.*;
import todo.app.domain.todo.appplication.ToDoSubService;
import todo.app.domain.todo.pressentation.dto.ToDoSubRequest;
import todo.app.domain.todo.pressentation.dto.ToDoSubResponse;

@RestController
@RequestMapping("/api/v1/todos/subs")
@RequiredArgsConstructor
public class ToDoSubController {

    private final ToDoSubService toDoSubService;

    @StatusOkDoc @Operation(summary = "상세 조회")
    @NotFoundExceptionDoc
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDoSubResponse.ToDoSubFindById> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(toDoSubService.findById(id));
    }

    @StatusCreateDoc @Operation(summary = "생성")
    @NotFoundExceptionDoc @ResponseDataExceptionDoc
    @PostMapping(value = "{parentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@PathVariable("parentId") Long parentId, @RequestBody @Valid ToDoSubRequest.ToDoSubSave dto) {
        toDoSubService.save(parentId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @StatusOkDoc @Operation(summary = "수정")
    @NotFoundExceptionDoc @ResponseDataExceptionDoc
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody @Valid ToDoSubRequest.ToDoSubPut dto) {
        toDoSubService.put(id, dto);
        return ResponseEntity.ok().build();
    }

    @StatusOkDoc @Operation(summary = "상태 변경")
    @NotFoundExceptionDoc @ResponseDataExceptionDoc
    @PatchMapping(value = "/status/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> patchStatus(@PathVariable("id") Long id, @RequestBody @Valid ToDoSubRequest.ToDoSubPatchStatus dto) {
        toDoSubService.patchStatus(id, dto);
        return ResponseEntity.ok().build();
    }

    @StatusNoContentDoc @Operation(summary = "삭제")
    @NotFoundExceptionDoc
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        toDoSubService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
