package ru.bbnshp.services;

import jakarta.transaction.Transactional;
import ru.bbnshp.entities.User;
import ru.bbnshp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository users;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByLogin(username).
                orElseThrow(() -> new UsernameNotFoundException("Not found with login: " + username));
        return UserDetailsImpl.build(user);
    }
}
