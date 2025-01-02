package uz.ilmnajot.hotel_management.service;

import uz.ilmnajot.hotel_management.entity.Role;

public interface RoleService {

    Role getRoleById(Long roleId);
    Role getRoleByName(String roleName);
}
