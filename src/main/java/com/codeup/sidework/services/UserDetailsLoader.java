package com.codeup.sidework.services;


import com.codeup.sidework.daos.UsersRepository;
import com.codeup.sidework.models.User;
import com.codeup.sidework.models.UserWithRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UsersRepository usersDao;

    @Autowired
    public UserDetailsLoader(UsersRepository usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("The username " + username + "could not be found.");
        }

        return new UserWithRoles(user, Collections.emptyList());
    }
}