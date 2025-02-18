package todo.app.domain.todo.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import todo.app.exception.NotFoundDataException;

@Repository
public class ToDoSubRepository {

    @PersistenceContext
    private EntityManager em;

    public ToDoSub findById(Long id) {
        ToDoSub entity = em.find(ToDoSub.class, id);
        if(entity == null) throw new NotFoundDataException("[ToDoSub] 데이터가 존재하지 않습니다. ID: " + id);
        return entity;
    }

    public void save(ToDoSub entity) {
        if(entity.getId() == null) {
            em.persist(entity);
            return;
        }
        em.merge(entity);
    }

    public void deleteById(Long id) {
        ToDoSub entity = findById(id);
        em.remove(entity);
    }
}
