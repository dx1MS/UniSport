package com.unisport.mvc.web.dao.impl;

import com.unisport.mvc.web.dao.UserDao;
import com.unisport.mvc.web.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findDevelopers() {
        //return em.createQuery("select u from USER u where u.role = 'DEVELOPER'", User.class).getResultList();
        //TODO WTF!???
//        return em.createQuery("select u from User u JOIN u.userRoles ur where ur.userRoleId = (SELECT r.userRoleId from ROLE r where r.ROLE_NAME = 'DEVELOPER')", User.class).getResultList();
        return em.createQuery("select u from User u where 'DEVELOPER' in elements(u.userRoles)", User.class).getResultList();
    }

    @Override
    public List<User> findAll() {
        Query query = em.createQuery("SELECT u FROM User u");
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public User findByName(String username) {
        List<User> users;

        users = em.createQuery("SELECT u from User u where userName=?")
                .setParameter(1, username).getResultList();

        if (users != null && users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }


}
