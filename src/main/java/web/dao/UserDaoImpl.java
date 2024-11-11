package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void removeUserById(Integer userId) {
        User user = em.find(User.class, userId);
        em.remove(user);
    }

    @Override
    public User getUserById(Integer userId) {
        return em.find(User.class, userId);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }
}
