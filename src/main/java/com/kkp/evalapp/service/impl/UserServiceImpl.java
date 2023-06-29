package com.kkp.evalapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.kkp.evalapp.mapper.EvalappMapper;
import com.kkp.evalapp.model.ResponseData;
import com.kkp.evalapp.model.Simple;
import com.kkp.evalapp.model.User;
import com.kkp.evalapp.service.UserService;
import com.kkp.evalapp.utils.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SqlSession session;

    /* --------------------------------- Select --------------------------------- */
    @Override
    public List<Simple> getPositions() {
        return session.getMapper(EvalappMapper.class).selectPositionList();
    }

    @Override
    public List<Simple> getDivisions() {
        return session.getMapper(EvalappMapper.class).selectDivisionList();
    }

    @Override
    public List<Simple> getDepartments() {
        return session.getMapper(EvalappMapper.class).selectDepartementList();
    }

    @Override
    public List<Simple> getLevels() {
        return session.getMapper(EvalappMapper.class).selectLevelList();
    }

    /* --------------------------------- insert --------------------------------- */

    @Override
    public void save(User entity) {
        session.getMapper(EvalappMapper.class).insertNewUser(entity);
    }

    @Override
    public ResponseData<?> authenticate(String employeeId, String password) {
        User user = session.getMapper(EvalappMapper.class).selectUserById(employeeId);

        if (user == null) {
            return new ResponseData<>(true, "Invalid Employee ID!", null);
        }

        boolean isPasswordMatch = PasswordEncoder.matches(password, user.getPassword());
        if (!isPasswordMatch) {
            return new ResponseData<>(true, "Incorrect Password!", null);
        }

        return new ResponseData<>(false, "Login Success!", user);
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
    public Optional<User> findByEmail(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public void deleteInBatch(List<User> users) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteInBatch'");
    }

}
