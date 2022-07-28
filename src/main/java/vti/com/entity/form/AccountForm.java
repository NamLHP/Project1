package vti.com.entity.form;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import vti.com.entity.enumerated.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountForm {
    @NotBlank(message = "{accountForm.userName.notBlank}")
    @Length(max = 50, message = "{accountForm.userName.length}")
    private String userName;

    @NotBlank(message = "{accountForm.firstName.notBlank}")
    private String firstName;

    private String lastName;

    private Role role;

    private Long departmentId;
}
