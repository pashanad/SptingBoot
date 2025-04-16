package ru.pavel.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel.springboot.dao.Dao;
import ru.pavel.springboot.model.User;


import java.util.List;

@Service
public class UserService {

    private final Dao dao;

    @Autowired
    public UserService(Dao dao) {
        this.dao = dao;
    }

    public void removeUserById(Long id) {
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public void saveUser(User user) {
        dao.saveUser(user);
    }

    public void updateUser(Long id, User newUser) {
        dao.updateUser(id,newUser);
    }

    public User showUserById(Long id){
        return dao.showUserById(id);
    }
}
