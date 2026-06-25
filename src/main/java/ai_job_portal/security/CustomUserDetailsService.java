package ai_job_portal.security;


import ai_job_portal.entity.User;
import ai_job_portal.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;


    public CustomUserDetailsService(
            UserRepository userRepository) {

        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {


        User user =
                userRepository.findByEmail(email)

                        .orElseThrow(() ->
                                new UsernameNotFoundException(
                                        "User not found"
                                )
                        );
        System.out.println("USER ROLE = " + user.getRole());

        return org.springframework.security.core.userdetails.User
                .builder()

                .username(user.getEmail())

                .password(user.getPassword())

                .roles(user.getRole())

                .build();
    }
}