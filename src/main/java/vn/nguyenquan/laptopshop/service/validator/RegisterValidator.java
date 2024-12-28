package vn.nguyenquan.laptopshop.service.validator;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.nguyenquan.laptopshop.domain.dto.RegisterDTO;
import vn.nguyenquan.laptopshop.service.UserSevice;

@Service
public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {

    private UserSevice userSevice;

    public RegisterValidator(UserSevice userSevice) {
        this.userSevice = userSevice;
    }

    @Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {
        boolean valid = true;

        // Check if password fields match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Confirm Password không chính xác !")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        // Additional validations can be added here

        // Check Email
        if (this.userSevice.checkEmailExist(user.getEmail())) {
            context.buildConstraintViolationWithTemplate("Email đã tồn tại !")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }
        return valid;
    }
}
