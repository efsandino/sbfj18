package bo.edusoft.sbf18.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO implements Serializable {

    private static final long serialVersionUID = -7338562254451522610L;

    private String name;
    private String email;
    private String password;
    public PhoneRequestDTO[] phones;

}
