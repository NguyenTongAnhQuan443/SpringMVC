package vn.nguyenquan.laptopshop.service;

import org.springframework.stereotype.Service;

import vn.nguyenquan.laptopshop.domain.Role;
import vn.nguyenquan.laptopshop.repository.RoleRepository;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String roleName) {
        return this.roleRepository.findByName(roleName);
    }
}
