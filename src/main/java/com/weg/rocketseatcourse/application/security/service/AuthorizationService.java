package com.weg.rocketseatcourse.application.security.service;

import com.weg.rocketseatcourse.application.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException{
        return null;
    }
}
