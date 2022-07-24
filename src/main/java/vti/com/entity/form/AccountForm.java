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

    @NotBlank(message = "Cant not blank this field")
    @Length(max = 50 , message = "over 50 characters")
    private String userName;

    private String firstName;

    private String lastName;

    private Role role;

    private Long departmentId;
}
