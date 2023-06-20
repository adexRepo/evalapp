package com.kkp.evalapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kkp.evalapp.model.User;
import com.kkp.evalapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User save(User entity) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public User update(User entity) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(User entity) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<User> find(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public boolean authenticate(String username, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public void deleteInBatch(List<User> users) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteInBatch'");
    }
}
