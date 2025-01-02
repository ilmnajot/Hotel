package uz.ilmnajot.hotel_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import uz.ilmnajot.hotel_management.exception.ResourceNotFoundException;
import uz.ilmnajot.hotel_management.repository.UserRepository;

@Configuration
public class UserDetailsServiceConfig {

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByEmail(username).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
    }
}
