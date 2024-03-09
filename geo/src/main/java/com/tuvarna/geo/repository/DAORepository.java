package com.tuvarna.geo.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.tuvarna.geo.config.GeoDBConfig;

public interface DAORepository<T> {

    @Autowired
    public static GeoDBConfig geoDBConfig = new GeoDBConfig();

    // Intended for creating a new object in the database
    public void save(T object);

    public void update(T object);

    public void delete(T object);

    public T getById(int id);

    @SuppressWarnings("rawtypes")
    public Iterable findAll();

    public void verify(String email, String password);

}
