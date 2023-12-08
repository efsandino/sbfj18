package bo.edusoft.sbf18.model.entities;

import bo.edusoft.sbf18.converter.CustomerDateAndTimeDeserialize;
import bo.edusoft.sbf18.converter.CustomerDateAndTimeSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "es_user", uniqueConstraints = {@UniqueConstraint(columnNames = "uuid")})
@Data
public class ESUserEntity implements Serializable {

    private static final long serialVersionUID = -1498791773292474092L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="uuid")
    private UUID uuid;

    private String name;

    @Email
    private String email;

    @CreatedDate
	@LastModifiedDate
	@JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
	@JsonSerialize(using= CustomerDateAndTimeSerialize.class)
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    @Column(name = "modified")
    private Date modified;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "token")
    private UUID token;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ESPhoneEntity> phones;
}
