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
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import todo.app.domain.todo.appplication.ToDoService;
import todo.app.domain.todo.pressentation.dto.ToDoRequest;
import todo.app.domain.todo.pressentation.dto.ToDoResponse;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ToDoController.class)
class ToDoControllerTest {
    private final String URL = "/api/v1/todos";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ToDoService toDoService;

    @Nested
    @DisplayName("FindAll")
    class FindAll {
        @Test
        @DisplayName("기본 조회 [page: null, sort: ASC]")
        void test1() throws Exception {
            ToDoResponse.ToDoFindAll response = new ToDoResponse.ToDoFindAll(1L, "Test ToDo", PriorityType.HIGH, Status.IN_PROGRESS, 1004L);

            BDDMockito.given(toDoService.findAll(anyInt(), any()))
                    .willReturn(new PageImpl<>(List.of(response)));

            mockMvc.perform(get(URL)
                            .param("sort", SortBy.ASC.toString())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content[0].id").value(1L))
                    .andExpect(jsonPath("$.content[0].title").value("Test ToDo"))
                    .andExpect(jsonPath("$.content[0].priority").value(PriorityType.HIGH.toString()))
                    .andExpect(jsonPath("$.content[0].status").value(Status.IN_PROGRESS.toString()))
                    .andExpect(jsonPath("$.content[0].unixTime").value(1004L));

            verify(toDoService).findAll(eq(0), eq(SortBy.ASC));
        }

        @Test
        @DisplayName("기본 조회 [page: null, sort: DESC]")
        void test2() throws Exception {
            ToDoResponse.ToDoFindAll response = new ToDoResponse.ToDoFindAll(1L, "Test ToDo", PriorityType.HIGH, Status.IN_PROGRESS, 1004L);

            BDDMockito.given(toDoService.findAll(anyInt(), any()))
                    .willReturn(new PageImpl<>(List.of(response)));

            mockMvc.perform(get(URL)
                            .param("sort", SortBy.DESC.toString())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content[0].id").value(1L))
                    .andExpect(jsonPath("$.content[0].title").value("Test ToDo"))
                    .andExpect(jsonPath("$.content[0].priority").value(PriorityType.HIGH.toString()))
                    .andExpect(jsonPath("$.content[0].status").value(Status.IN_PROGRESS.toString()))
                    .andExpect(jsonPath("$.content[0].unixTime").value(1004L));

            verify(toDoService).findAll(eq(0), eq(SortBy.DESC));
        }

        @Test
        @DisplayName("기본 조회 [page: 1, sort: ASC]")
        void test3() throws Exception {
            ToDoResponse.ToDoFindAll response = new ToDoResponse.ToDoFindAll(1L, "Test ToDo", PriorityType.HIGH, Status.IN_PROGRESS, 1004L);

            BDDMockito.given(toDoService.findAll(anyInt(), any()))
                    .willReturn(new PageImpl<>(List.of(response)));

            mockMvc.perform(get(URL)
                            .param("page", "2")
                            .param("sort", SortBy.ASC.toString())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content[0].id").value(1L))
                    .andExpect(jsonPath("$.content[0].title").value("Test ToDo"))
                    .andExpect(jsonPath("$.content[0].priority").value(PriorityType.HIGH.toString()))
                    .andExpect(jsonPath("$.content[0].status").value(Status.IN_PROGRESS.toString()))
                    .andExpect(jsonPath("$.content[0].unixTime").value(1004L));

            verify(toDoService).findAll(eq(1), eq(SortBy.ASC));
        }

        @Test
        @DisplayName("기본 조회 [page: null, sort: ASC]")
        void test4() throws Exception {
            ToDoResponse.ToDoFindAll response = new ToDoResponse.ToDoFindAll(1L, "Test ToDo", PriorityType.HIGH, Status.NOT_STARTED, 1004L);

            BDDMockito.given(toDoService.findAll(anyInt(), any()))
                    .willReturn(new PageImpl<>(List.of(response)));

            mockMvc.perform(get(URL)
                            .param("sort", SortBy.ASC.toString())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content[0].id").value(1L))
                    .andExpect(jsonPath("$.content[0].title").value("Test ToDo"))
                    .andExpect(jsonPath("$.content[0].priority").value(PriorityType.HIGH.toString()))
                    .andExpect(jsonPath("$.content[0].status").value(Status.NOT_STARTED.toString()))
                    .andExpect(jsonPath("$.content[0].unixTime").value(1004L));

            verify(toDoService).findAll(eq(0), eq(SortBy.ASC));
        }

        @Test
        @DisplayName("기본 조회 [page: null, sort: ASC]")
        void test5() throws Exception {
            ToDoResponse.ToDoFindAll response = new ToDoResponse.ToDoFindAll(1L, "Test ToDo", PriorityType.HIGH, Status.IN_PROGRESS, 1004L);

            BDDMockito.given(toDoService.findAll(anyInt(), any()))
                    .willReturn(new PageImpl<>(List.of(response)));

            mockMvc.perform(get(URL)
                            .param("sort", SortBy.ASC.toString())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content[0].id").value(1L))
                    .andExpect(jsonPath("$.content[0].title").value("Test ToDo"))
                    .andExpect(jsonPath("$.content[0].priority").value(PriorityType.HIGH.toString()))
                    .andExpect(jsonPath("$.content[0].status").value(Status.IN_PROGRESS.toString()))
                    .andExpect(jsonPath("$.content[0].unixTime").value(1004L));

            verify(toDoService).findAll(eq(0), eq(SortBy.ASC));
        }

        @Test
        @DisplayName("기본 조회 [page: null, sort: ASC]")
        void test6() throws Exception {
            ToDoResponse.ToDoFindAll response = new ToDoResponse.ToDoFindAll(1L, "Test ToDo", PriorityType.HIGH, Status.COMPLETED, 1004L);

            BDDMockito.given(toDoService.findAll(anyInt(), any()))
                    .willReturn(new PageImpl<>(List.of(response)));

            mockMvc.perform(get(URL)
                            .param("sort", SortBy.ASC.toString())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content[0].id").value(1L))
                    .andExpect(jsonPath("$.content[0].title").value("Test ToDo"))
                    .andExpect(jsonPath("$.content[0].priority").value(PriorityType.HIGH.toString()))
                    .andExpect(jsonPath("$.content[0].status").value(Status.COMPLETED.toString()))
                    .andExpect(jsonPath("$.content[0].unixTime").value(1004L));

            verify(toDoService).findAll(eq(0), eq(SortBy.ASC));
        }

        @Test
        @DisplayName("기본 조회 [page: 1, sort: ASC]")
        void test7() throws Exception {
            ToDoResponse.ToDoFindAll response = new ToDoResponse.ToDoFindAll(1L, "Test ToDo", PriorityType.HIGH, Status.IN_PROGRESS, 1004L);

            BDDMockito.given(toDoService.findAll(anyInt(), any()))
                    .willReturn(new PageImpl<>(List.of(response)));

            mockMvc.perform(get(URL)
                            .param("page", "2")
                            .param("sort", SortBy.ASC.toString())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content[0].id").value(1L))
                    .andExpect(jsonPath("$.content[0].title").value("Test ToDo"))
                    .andExpect(jsonPath("$.content[0].priority").value(PriorityType.HIGH.toString()))
                    .andExpect(jsonPath("$.content[0].status").value(Status.IN_PROGRESS.toString()))
                    .andExpect(jsonPath("$.content[0].unixTime").value(1004L));

            verify(toDoService).findAll(eq(1), eq(SortBy.ASC));
        }

        @Test
        @DisplayName("실패 Sort가 Null일 때 [page: null, sort: null]")
        void test8() throws Exception {
            mockMvc.perform(get(URL)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패 Page가 1 이하 일 때 [page: 0, sort: ASC]")
        void test9() throws Exception {
            mockMvc.perform(get(URL)
                            .param("page", "0")
                            .param("sort", SortBy.ASC.toString())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패 잘못된 Sort 값 [page: null, sort: TEST]")
        void test10() throws Exception {
            mockMvc.perform(get(URL)
                            .param("sort", "TEST")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("FindById")
    class FindById {
        @Test
        @DisplayName("기본 조회 [id: 1L]")
        void test1() throws Exception {
            ToDoResponse.ToDoFindById response = new ToDoResponse.ToDoFindById(1L, "Test ToDo", "Content", PriorityType.MEDIUM, Status.NOT_STARTED, 1004L, List.of());

            BDDMockito.given(toDoService.findById(anyLong()))
                    .willReturn(response);

            mockMvc.perform(get(URL + "/{id}", 1L)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1L))
                    .andExpect(jsonPath("$.title").value("Test ToDo"))
                    .andExpect(jsonPath("$.content").value("Content"))
                    .andExpect(jsonPath("$.priority").value(PriorityType.MEDIUM.toString()))
                    .andExpect(jsonPath("$.status").value(Status.NOT_STARTED.toString()))
                    .andExpect(jsonPath("$.unixTime").value(1004L));

            verify(toDoService).findById(eq(1L));
        }
    }

    @Nested
    @DisplayName("Save")
    class Save {
        @Test
        @DisplayName("기본 생성 [Title: a, content: b, priority: LOW, unixTime: 0L]")
        void test1() throws Exception {
            ToDoRequest.ToDoSave request = new ToDoRequest.ToDoSave("a", "b", PriorityType.LOW, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated());

            verify(toDoService).save(request);
        }

        @Test
        @DisplayName("기본 생성 [Title: a, content: b, priority: HIGH, unixTime: 0L]")
        void test2() throws Exception {
            ToDoRequest.ToDoSave request = new ToDoRequest.ToDoSave("a", "b", PriorityType.HIGH, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated());

            verify(toDoService).save(request);
        }

        @Test
        @DisplayName("기본 생성 [Title: a, content: b, priority: MEDIUM, unixTime: 0L]")
        void test3() throws Exception {
            ToDoRequest.ToDoSave request = new ToDoRequest.ToDoSave("a", "b", PriorityType.MEDIUM, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated());

            verify(toDoService).save(request);
        }

        @Test
        @DisplayName("기본 생성 [Title: a, content: b, priority: NONE, unixTime: 0L]")
        void test4() throws Exception {
            ToDoRequest.ToDoSave request = new ToDoRequest.ToDoSave("a", "b", PriorityType.NONE, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated());

            verify(toDoService).save(request);
        }

        @Test
        @DisplayName("기본 생성 [Title: a, content: b, priority: null, unixTime: 0L]")
        void test5() throws Exception {
            ToDoRequest.ToDoSave request = new ToDoRequest.ToDoSave("a", "b", null, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isCreated());

            verify(toDoService).save(request);
        }

        @Test
        @DisplayName("실패 Title 누락 [Title: null, content: b, priority: null, unixTime: 0L]")
        void test6() throws Exception {
            ToDoRequest.ToDoSave request = new ToDoRequest.ToDoSave(null, "b", null, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패 Content 누락 [Title: a, content: null, priority: null, unixTime: 0L]")
        void test7() throws Exception {
            ToDoRequest.ToDoSave request = new ToDoRequest.ToDoSave("a", null, null, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패 UnixTime 누락 [Title: a, content: b, priority: null, unixTime: null]")
        void test8() throws Exception {
            ToDoRequest.ToDoSave request = new ToDoRequest.ToDoSave("a", "b", null, null);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패 Title 크기 초과 [Title: a * 51, content: b, priority: null, unixTime: 0L]")
        void test9() throws Exception {
            ToDoRequest.ToDoSave request = new ToDoRequest.ToDoSave("a".repeat(51), "b", null, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패 Content 크기 초과 [Title: a, content: b * 3001, priority: null, unixTime: 0L]")
        void test10() throws Exception {
            ToDoRequest.ToDoSave request = new ToDoRequest.ToDoSave("a", "b".repeat(3001), null, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(post(URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Put")
    class Put {
        @Test
        @DisplayName("기본 수정 [Title: a, content: b, priority: LOW, unixTime: 0L]")
        void test1() throws Exception {
            ToDoRequest.ToDoPut request = new ToDoRequest.ToDoPut("a", "b", PriorityType.LOW, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(put(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoService).put(1L, request);
        }

        @Test
        @DisplayName("기본 수정 [Title: a, content: b, priority: HIGH, unixTime: 0L]")
        void test2() throws Exception {
            ToDoRequest.ToDoPut request = new ToDoRequest.ToDoPut("a", "b", PriorityType.HIGH, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(put(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoService).put(1L, request);
        }

        @Test
        @DisplayName("기본 수정 [Title: a, content: b, priority: MEDIUM, unixTime: 0L]")
        void test3() throws Exception {
            ToDoRequest.ToDoPut request = new ToDoRequest.ToDoPut("a", "b", PriorityType.MEDIUM, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(put(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoService).put(1L, request);
        }

        @Test
        @DisplayName("기본 수정 [Title: a, content: b, priority: NONE, unixTime: 0L]")
        void test4() throws Exception {
            ToDoRequest.ToDoPut request = new ToDoRequest.ToDoPut("a", "b", PriorityType.NONE, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(put(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoService).put(1L, request);
        }

        @Test
        @DisplayName("기본 수정 [Title: a, content: b, priority: null, unixTime: 0L]")
        void test5() throws Exception {
            ToDoRequest.ToDoPut request = new ToDoRequest.ToDoPut("a", "b", null, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(put(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoService).put(1L, request);
        }

        @Test
        @DisplayName("기본 수정 [Title: null, content: b, priority: null, unixTime: 0L]")
        void test6() throws Exception {
            ToDoRequest.ToDoPut request = new ToDoRequest.ToDoPut(null, "b", null, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(put(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoService).put(1L, request);
        }

        @Test
        @DisplayName("기본 수정 [Title: a, content: null, priority: null, unixTime: 0L]")
        void test7() throws Exception {
            ToDoRequest.ToDoPut request = new ToDoRequest.ToDoPut("a", null, null, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(put(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoService).put(1L, request);
        }

        @Test
        @DisplayName("기본 수정 [Title: a, content: b, priority: null, unixTime: null]")
        void test8() throws Exception {
            ToDoRequest.ToDoPut request = new ToDoRequest.ToDoPut("a", "b", null, null);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(put(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoService).put(1L, request);
        }
        @Test
        @DisplayName("실패 Title 크기 초과 [Title: a * 51, content: b, null, unixTime: 0L]")
        void test9() throws Exception {
            ToDoRequest.ToDoPut request = new ToDoRequest.ToDoPut("a".repeat(51), "b", null, 0L);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(put(URL + "/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("실패 Content 크기 초과 [Title: a, content: b * 3001, null, unixTime: 0L]")
        void test10() throws Exception {
            ToDoRequest.ToDoPut request = new ToDoRequest.ToDoPut("a", "b".repeat(3001), null, 0L);
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
            ToDoRequest.ToDoPatchStatus request = new ToDoRequest.ToDoPatchStatus(Status.NOT_STARTED);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(patch(URL + "/status/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoService).patchStatus(1L, request);
        }

        @Test
        @DisplayName("기본 수정 [Status: IN_PROGRESS]")
        void test2() throws Exception {
            ToDoRequest.ToDoPatchStatus request = new ToDoRequest.ToDoPatchStatus(Status.IN_PROGRESS);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(patch(URL + "/status/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoService).patchStatus(1L, request);
        }

        @Test
        @DisplayName("기본 수정 [Status: COMPLETED]")
        void test3() throws Exception {
            ToDoRequest.ToDoPatchStatus request = new ToDoRequest.ToDoPatchStatus(Status.COMPLETED);
            String json = objectMapper.writeValueAsString(request);

            mockMvc.perform(patch(URL + "/status/{id}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());

            verify(toDoService).patchStatus(1L, request);
        }

        @Test
        @DisplayName("실패 Status 누락 [Status: null]")
        void test4() throws Exception {
            ToDoRequest.ToDoPatchStatus request = new ToDoRequest.ToDoPatchStatus(null);
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

            verify(toDoService).delete(1L);
        }
    }
}
