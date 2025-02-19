package todo.app.domain.todo.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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

    public Page<ToDo> findAll(Pageable pageable, SortBy sort) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ToDo> cq = cb.createQuery(ToDo.class);
        Root<ToDo> root = cq.from(ToDo.class);

        if(sort == SortBy.ASC) {
            cq.orderBy(cb.asc(root.get("unixTime")));
        } else {
            cq.orderBy(cb.desc(root.get("unixTime")));
        }
        TypedQuery<ToDo> query = em.createQuery(cq);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<ToDo> toDoList = query.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<ToDo> countRoot = countQuery.from(ToDo.class);
        countQuery.select(cb.count(countRoot));

        Long count = em.createQuery(countQuery).getSingleResult();
        return new PageImpl<>(toDoList, pageable, count);
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
