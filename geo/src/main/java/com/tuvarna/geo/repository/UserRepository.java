
package com.tuvarna.geo.repository;

import org.springframework.stereotype.Repository;
import com.tuvarna.geo.entity.User;

@Repository
public class UserRepository implements DAORepository<User> {

    @SuppressWarnings("unchecked")
    @Override
    public void save(User user) {
        String sql = "INSERT INTO user (username, email, password, is_blocked, user_type) VALUES (?, ?, ?, ?, ?)";

        geoDBConfig.executeSql(sql, null, user.getUsername(), user.getEmail(), user.getPassword(), user.isBlocked(),
                user.getUserType().toString());

    }

    @Override
    public void update(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public User getById(int id) {
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
        // geoDBConfig.loadSqlFile("geo/src/sql/read/get_user.sql");
    }

}