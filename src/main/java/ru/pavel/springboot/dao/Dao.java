package ru.pavel.springboot.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.pavel.springboot.model.User;

import java.util.List;

@Repository
public class Dao {

    private final EntityManager entityManager;

    @Autowired
    public Dao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void removeUserById(Long id) {
//        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(User.class,id));
//        entityManager.getTransaction().commit();
    }
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Transactional(readOnly = true)
    public User showUserById(Long id){
        return entityManager.find(User.class,id);
    }

    @Transactional
    public void saveUser(User user) {
//        entityManager.getTransaction().begin();
        entityManager.persist(user);
//        entityManager.getTransaction().commit();
        System.out.println("User saved: " + user.getName());
    }

    @Transactional
    public void updateUser(Long id, User newUser) {
//        entityManager.getTransaction().begin();
        User existingUser = entityManager.find(User.class, id);
        if (existingUser != null) {
            existingUser.setName(newUser.getName());
            existingUser.setLastName(newUser.getLastName());
            existingUser.setAge(newUser.getAge());
            entityManager.merge(existingUser);
//            entityManager.getTransaction().commit();
        }
    }
}
