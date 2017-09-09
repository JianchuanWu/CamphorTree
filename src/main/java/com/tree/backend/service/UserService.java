
package com.tree.backend.service;

import com.tree.backend.model.User;

public interface UserService {

    User save(User user);

    User findByUserName(String userName);

}
