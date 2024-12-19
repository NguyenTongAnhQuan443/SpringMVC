package vn.nguyenquan.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.nguyenquan.laptopshop.domain.User;
import vn.nguyenquan.laptopshop.repository.IUserRepository;

@Service
public class UserSevice {

    private IUserRepository userRepository;

    public UserSevice(IUserRepository userRepository) {
        this.userRepository = userRepository;
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
}