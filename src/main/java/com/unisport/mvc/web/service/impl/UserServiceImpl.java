package com.unisport.mvc.web.service.impl;

import com.unisport.mvc.web.dao.UserDao;
import com.unisport.mvc.web.model.User;
import com.unisport.mvc.web.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.Collection;
import java.util.List;

@Named
@Service
@Transactional
public class UserServiceImpl implements UserService,
//        UserDetailsService,
        AuthenticationProvider {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public User merge(User user) {
        if (user.getUserId() == null) {
            user = userDao.create(user);
        } else {
            user = userDao.update(user);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User create(User u) {
        return userDao.create(u);
    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public User find(Object id) {
        return null;
    }

    @Override
    public User update(User u) {
        return null;
    }

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Query query = sessionFactory.getCurrentSession().createQuery("from user u where u.user_name=:username");
//        query.setParameter("username", username);
//        User result = (User) query.uniqueResult();
//
//        if (result == null){
//            throw new UsernameNotFoundException("username: " + username + " not found");
//        }
//        return result;
//    }

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = userDao.findByName(username);

        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }

        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, password, authorities);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);

    }
}
