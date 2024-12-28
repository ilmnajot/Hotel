package uz.ilmnajot.hotel_management.service;

import org.springframework.stereotype.Service;
import uz.ilmnajot.hotel_management.entity.Role;
import uz.ilmnajot.hotel_management.exception.ErrorResponse;
import uz.ilmnajot.hotel_management.exception.ResourceNotFoundException;
import uz.ilmnajot.hotel_management.repository.RoleRepository;
import uz.ilmnajot.hotel_management.utils.ErrorMessage;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.findByIdAndDeletedFalse(roleId).orElseThrow(
                () -> new ResourceNotFoundException(ErrorMessage.ROLE_NOT_FOUND));

    }
}
