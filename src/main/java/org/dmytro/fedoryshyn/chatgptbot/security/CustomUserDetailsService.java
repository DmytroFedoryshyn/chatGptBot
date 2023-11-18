package org.dmytro.fedoryshyn.chatgptbot.security;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.dmytro.fedoryshyn.chatgptbot.model.User;
import org.dmytro.fedoryshyn.chatgptbot.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByLogin(username);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }
}
