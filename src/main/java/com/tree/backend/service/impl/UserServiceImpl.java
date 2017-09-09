
package com.tree.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tree.backend.dao.UserDao;
import com.tree.backend.model.User;
import com.tree.backend.service.UserService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

}
