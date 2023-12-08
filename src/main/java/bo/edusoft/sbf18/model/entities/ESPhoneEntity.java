package bo.edusoft.sbf18.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "es_phone")
@Data
public class ESPhoneEntity implements Serializable {
    private static final long serialVersionUID = -7251861076186422170L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;
    private Integer cityCode;
    private Integer countryCode;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private ESUserEntity user;
}
