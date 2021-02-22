package ru.javamentor.web.dao;

import org.springframework.stereotype.Repository;
import ru.javamentor.web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext(name = "EntityManager")
    EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public User getUser(long id) {
        String hql = "select u from User as u where u.id=?1";
        TypedQuery<User> query = em.createQuery(hql, User.class);
        query.setParameter(1, id);
        return query.getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery("select u from User as u", User.class);
        return query.getResultList();
    }

    @Override
    public void deleteUser(long id) {
        em.remove(getUser(id));
    }

    @Override
    public void edit(User user) {
        em.merge(user);
    }
}
