package todo.app.domain.todo.pressentation;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import todo.app.doc.*;
import todo.app.domain.todo.appplication.ToDoService;
import todo.app.domain.todo.pressentation.dto.ToDoRequest;
import todo.app.domain.todo.pressentation.dto.ToDoResponse;

@Validated
@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @StatusOkDoc @Operation(summary = "전체 조회")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ToDoResponse.ToDoFindAll>> findAll(
            @RequestParam(required = false, defaultValue = "1", value = "page") @Min(1) int page,
            @RequestParam(value = "sort") SortBy sort) {
        return ResponseEntity.ok(toDoService.findAll(page - 1, sort));
    }

    @StatusOkDoc @Operation(summary = "상세 조회")
    @NotFoundExceptionDoc
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDoResponse.ToDoFindById> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(toDoService.findById(id));
    }

    @StatusCreateDoc @Operation(summary = "생성")
    @ResponseDataExceptionDoc
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@RequestBody @Valid ToDoRequest.ToDoSave dto) {
        toDoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @StatusOkDoc @Operation(summary = "수정")
    @NotFoundExceptionDoc @ResponseDataExceptionDoc
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody @Valid ToDoRequest.ToDoPut dto) {
        toDoService.put(id, dto);
        return ResponseEntity.ok().build();
    }

    @StatusOkDoc @Operation(summary = "상태 변경")
    @NotFoundExceptionDoc @ResponseDataExceptionDoc
    @PatchMapping(value = "/status/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> patchStatus(@PathVariable("id") Long id, @RequestBody @Valid ToDoRequest.ToDoPatchStatus dto) {
        toDoService.patchStatus(id, dto);
        return ResponseEntity.ok().build();
    }

    @StatusNoContentDoc @Operation(summary = "삭제")
    @NotFoundExceptionDoc
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        toDoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
