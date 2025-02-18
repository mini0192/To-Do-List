package todo.app.domain.todo.pressentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import todo.app.domain.todo.appplication.ToDoSubService;
import todo.app.domain.todo.pressentation.dto.ToDoSubRequest;
import todo.app.domain.todo.pressentation.dto.ToDoSubResponse;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ToDoSubController.class)
class ToDoSubControllerTest {
    private final String URL = "/api/v1/todos/subs";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ToDoSubService toDoSubService;

    @Nested
    @DisplayName("FindById")
    class FindById {
        @Test
        @DisplayName("기본 조회 [id: 1L]")
        void test1() throws Exception {
            ToDoSubResponse.ToDoSubFindById response = new ToDoSubResponse.ToDoSubFindById(1L, "Test ToDo", "Content", Status.NOT_STARTED);

            BDDMockito.given(toDoSubService.findById(anyLong()))
                    .willReturn(response);

            mockMvc.perform(get(URL + "/{id}", 1L)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1L))
                    .andExpect(jsonPath("$.title").value("Test ToDo"))
                    .andExpect(jsonPath("$.content").value("Content"))
                    .andExpect(jsonPath("$.status").value(Status.NOT_STARTED.toString()));

            verify(toDoSubService).findById(eq(1L));
        }
    }

    @Nested
    @DisplayName("Save")
    class Save {
        @Test
        @DisplayName("기본 생성 [Title: a, content: b")
        void test1() throws Exception {
            ToDoSubRequest.ToDoSubSave request = new ToDoSubRequest.ToDoSubSave("a", "b");
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(post(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated());

            verify(toDoSubService).save(1L, request);
        }

        @Test
        @DisplayName("실패 Title 누락 [Title: null, content: b]")
        void test2() throws Exception {
            ToDoSubRequest.ToDoSubSave request = new ToDoSubRequest.ToDoSubSave(null, "b");
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(post(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패 Content 누락 [Title: a, content: null]")
        void test3() throws Exception {
            ToDoSubRequest.ToDoSubSave request = new ToDoSubRequest.ToDoSubSave("a", null);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(post(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패 Title 크기 초과 [Title: a * 51, content: b]")
        void test4() throws Exception {
            ToDoSubRequest.ToDoSubSave request = new ToDoSubRequest.ToDoSubSave("a".repeat(51), "b");
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(post(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패 Content 크기 초과 [Title: a, content: b * 3001]")
        void test5() throws Exception {
            ToDoSubRequest.ToDoSubSave request = new ToDoSubRequest.ToDoSubSave("a", "b".repeat(3001));
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(post(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Put")
    class Put {
        @Test
        @DisplayName("기본 수정 [Title: a, content: b]")
        void test1() throws Exception {
            ToDoSubRequest.ToDoSubPut request = new ToDoSubRequest.ToDoSubPut("a", "b");
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(put(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoSubService).put(1L, request);
        }

        @Test
        @DisplayName("기본 수정 [Title: null, content: b]")
        void test2() throws Exception {
            ToDoSubRequest.ToDoSubPut request = new ToDoSubRequest.ToDoSubPut(null, "b");
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(put(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoSubService).put(1L, request);
        }

        @Test
        @DisplayName("기본 수정 [Title: a, content: null]")
        void test3() throws Exception {
            ToDoSubRequest.ToDoSubPut request = new ToDoSubRequest.ToDoSubPut("a", null);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(put(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoSubService).put(1L, request);
        }

        @Test
        @DisplayName("실패 Title 크기 초과 [Title: a * 51, content: b]")
        void test4() throws Exception {
            ToDoSubRequest.ToDoSubPut request = new ToDoSubRequest.ToDoSubPut("a".repeat(51), "b");
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(put(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패 Content 크기 초과 [Title: a, content: b * 3001]")
        void test5() throws Exception {
            ToDoSubRequest.ToDoSubPut request = new ToDoSubRequest.ToDoSubPut("a", "b".repeat(3001));
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(put(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("PatchStatus")
    class PatchStatus {
        @Test
        @DisplayName("기본 수정 [Status: NOT_STARTED]")
        void test1() throws Exception {
            ToDoSubRequest.ToDoSubPatchStatus request = new ToDoSubRequest.ToDoSubPatchStatus(Status.NOT_STARTED);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(patch(URL + "/status/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoSubService).patchStatus(1L, request);
        }

        @Test
        @DisplayName("기본 수정 [Status: IN_PROGRESS]")
        void test2() throws Exception {
            ToDoSubRequest.ToDoSubPatchStatus request = new ToDoSubRequest.ToDoSubPatchStatus(Status.IN_PROGRESS);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(patch(URL + "/status/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoSubService).patchStatus(1L, request);
        }

        @Test
        @DisplayName("기본 수정 [Status: COMPLETED]")
        void test3() throws Exception {
            ToDoSubRequest.ToDoSubPatchStatus request = new ToDoSubRequest.ToDoSubPatchStatus(Status.COMPLETED);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(patch(URL + "/status/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoSubService).patchStatus(1L, request);
        }

        @Test
        @DisplayName("실패 Status 누락 [Status: null]")
        void test4() throws Exception {
            ToDoSubRequest.ToDoSubPatchStatus request = new ToDoSubRequest.ToDoSubPatchStatus(null);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(patch(URL + "/status/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Delete")
    class Delete {
        @Test
        @DisplayName("기본 삭제")
        void deleteTest() throws Exception {
            mockMvc.perform(delete(URL + "/{id}", 1L))
                    .andExpect(status().isNoContent());

            verify(toDoSubService).delete(1L);
        }
    }
}