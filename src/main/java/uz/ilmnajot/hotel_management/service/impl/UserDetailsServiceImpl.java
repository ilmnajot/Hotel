package uz.ilmnajot.hotel_management.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.hotel_management.entity.UserDetail;
import uz.ilmnajot.hotel_management.exception.ResourceNotFoundException;
import uz.ilmnajot.hotel_management.repository.UserDetailsRepository;
import uz.ilmnajot.hotel_management.repository.UserRepository;
import uz.ilmnajot.hotel_management.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository, UserRepository userRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.userRepository = userRepository;
    }

    public UserDetail getUserDetails(Long id) {
        return userDetailsRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new ResourceNotFoundException("User details not found"));

    }
}
