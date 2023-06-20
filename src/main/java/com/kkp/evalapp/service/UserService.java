package com.kkp.evalapp.service;

import java.util.List;
import java.util.Optional;

import com.kkp.evalapp.model.User;

public interface UserService {
    public User save(User entity);
    public User update(User entity);
    public void delete(User entity);
    public void delete(Long id) ;
    public Optional<User> find(Long id);
    public List<User> findAll();
    public boolean authenticate(String username, String password);
    public Optional<User> findByEmail(String email);
    public void deleteInBatch(List<User> users);
}
