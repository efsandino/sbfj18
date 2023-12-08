package bo.edusoft.sbf18.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequestDTO implements Serializable {

    private static final long serialVersionUID = -3987669016602714760L;
    private Integer number;
    private Integer cityCode;
    private Integer countryCode;
}
