package vn.nguyenquan.laptopshop.service;

import org.springframework.stereotype.Service;

import vn.nguyenquan.laptopshop.domain.Role;
import vn.nguyenquan.laptopshop.repository.IRoleRepository;

@Service
public class RoleService {
    private IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String roleName) {
        return this.roleRepository.findByName(roleName);
    }
}
