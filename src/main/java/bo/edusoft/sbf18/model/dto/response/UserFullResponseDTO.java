package bo.edusoft.sbf18.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFullResponseDTO extends UserResponseDTO implements Serializable {
    private static final long serialVersionUID = 5120013369603466836L;

    private String email;
}
