package com.tuvarna.geo.repository;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tuvarna.geo.entity.UserType;

@Repository
public class UserTypeRepository implements DAORepository<UserType> {

    @SuppressWarnings("unchecked")
    public UserType findByType(String type) {

        String sql = "SELECT * FROM UserType WHERE type = '?'";
        RowMapper<UserType> rowMapper = geoDBConfig.getRowMapper(UserType.class);

        List<UserType> userTypes = geoDBConfig.executeSql(sql, rowMapper, type);

        return userTypes.get(0);
    }

    @Override
    public void save(UserType object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(UserType object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(UserType object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public UserType getById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public Iterable findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public void verify(String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verify'");
    }
}