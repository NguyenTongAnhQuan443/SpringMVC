package vn.nguyenquan.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.nguyenquan.laptopshop.domain.Role;
import vn.nguyenquan.laptopshop.domain.User;
import vn.nguyenquan.laptopshop.domain.dto.RegisterDTO;
import vn.nguyenquan.laptopshop.repository.IRoleRepository;
import vn.nguyenquan.laptopshop.repository.IUserRepository;

@Service
public class UserSevice {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    public UserSevice(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String handleHello() {
        return "hello from service";
    }

    // Save
    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
    }

    // Find All
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    // Get All User By Email
    public List<User> getAllUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    // Find By ID
    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    // Delete
    public void deleteAUser(long id) {
        this.userRepository.deleteById(id);
    }

    // Get Role By Name
    public Role getRoleByName(String roleName) {
        return this.roleRepository.findByName(roleName);
    }

    // Mapper
    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }
}