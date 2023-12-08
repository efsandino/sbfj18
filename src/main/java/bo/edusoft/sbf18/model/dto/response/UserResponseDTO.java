package bo.edusoft.sbf18.model.dto.response;

import bo.edusoft.sbf18.converter.CustomerDateAndTimeDeserialize;
import bo.edusoft.sbf18.converter.CustomerDateAndTimeSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO implements Serializable {

    private static final long serialVersionUID = 5223529787030973625L;
    private UUID id;
    private Long numericId;
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    @JsonSerialize(using= CustomerDateAndTimeSerialize.class)
    private Date created;
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    @JsonSerialize(using= CustomerDateAndTimeSerialize.class)
    private Date modified;
    @JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
    @JsonSerialize(using= CustomerDateAndTimeSerialize.class)
    private Date last_login;
    private UUID token;
    private Boolean isActive;


}
