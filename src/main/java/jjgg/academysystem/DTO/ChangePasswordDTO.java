package jjgg.academysystem.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChangePasswordDTO {

    @NotBlank(message = "The current password cannot be empty")
    private String currentPassword;

    @NotBlank(message = "The new password cannot be empty")
    @Size(min = 6, max = 20 , message = "The new password must be between 6 and 20 characters")
    private String newPassword;

    public ChangePasswordDTO() {}
    public ChangePasswordDTO(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
