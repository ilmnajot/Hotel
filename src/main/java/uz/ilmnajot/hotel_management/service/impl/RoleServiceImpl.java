package uz.ilmnajot.hotel_management.service.impl;

import org.springframework.stereotype.Service;
import uz.ilmnajot.hotel_management.entity.Role;
import uz.ilmnajot.hotel_management.exception.ResourceNotFoundException;
import uz.ilmnajot.hotel_management.repository.RoleRepository;
import uz.ilmnajot.hotel_management.service.RoleService;
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

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findByNameAndDeletedFalse(roleName).orElseThrow(
                () -> new ResourceNotFoundException(ErrorMessage.ROLE_NOT_FOUND));
    }
}
