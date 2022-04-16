package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

public class GenericDao {
    private final SessionFactory sessionFactory;

    public GenericDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> T get(Class<T> clazz, Serializable id) {
        return getSession().get(clazz, id);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}