package miu.edu.dentalsurgery.service;

import miu.edu.dentalsurgery.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long roleId);
    Role updateRole(Role role);
    void deleteRole(Role role);
    Role addNewRole(Role role);
}
