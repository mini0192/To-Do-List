package todo.app.domain.todo.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import todo.app.domain.todo.pressentation.SortBy;
import todo.app.domain.todo.pressentation.Status;
import todo.app.exception.NotFoundDataException;

import java.util.List;

@Repository
public class ToDoRepository {
    @PersistenceContext
    private EntityManager em;

    public Page<ToDo> findAll(Pageable pageable, SortBy sort, Status status) {
        // 기본 쿼리
        String baseQueryStr = "SELECT t FROM ToDo t";
        String statusQueryStr = getStatusQuery(status);
        String orderByQueryStr = getOrderByQuery(sort);
        String finalQueryStr = baseQueryStr + statusQueryStr + orderByQueryStr;

        // 필요한 값만 읽기
        TypedQuery<ToDo> query = em.createQuery(finalQueryStr, ToDo.class);
        if(status != null) {
            query.setParameter("status", status);
        }

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<ToDo> toDoList = query.getResultList();

        // 전체 데이터 개수
        String countQueryStr = "SELECT COUNT(t) FROM ToDo t" + statusQueryStr;
        TypedQuery<Long> countQuery = em.createQuery(countQueryStr, Long.class);
        if(status != null) {
            countQuery.setParameter("status", status);
        }

        Long count = countQuery.getSingleResult();
        return new PageImpl<>(toDoList, pageable, count);
    }

    /**
     * 정렬 적용 쿼리 생성
     * @Param sort: 정렬 값
     * */
    private String getOrderByQuery(SortBy sort) {
        if(sort == SortBy.DESC) return " ORDER BY t.unixTime DESC";
        return " ORDER BY t.unixTime ASC";
    }

    /**
     * 상태 필터 적용 쿼리 생성
     * @Param status: 상태
     * */
    private String getStatusQuery(Status status) {
        if(status == null) return "";
        return " WHERE t.status = :status";
    }


    public ToDo findById(Long id) {
        ToDo entity = em.find(ToDo.class, id);
        if(entity == null) throw new NotFoundDataException("[ToDo] 데이터가 존재하지 않습니다. ID: " + id);
        return entity;
    }

    public void save(ToDo entity) {
        if(entity.getId() == null) {
            em.persist(entity);
            return;
        }
        em.merge(entity);
    }

    public void deleteById(Long id) {
        ToDo entity = findById(id);
        em.remove(entity);
    }
}
