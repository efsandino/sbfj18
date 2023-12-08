package bo.edusoft.sbf18.repository;

import bo.edusoft.sbf18.model.entities.ESUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<ESUserEntity, Long> {
    @Query("Select user From ESUserEntity user WHERE user.email = :email")
    List<ESUserEntity> searchUserEntitiesByEmail(@Param("email") String email);


    @Query("Select user From ESUserEntity user WHERE user.uuid = :uuid and user.token = :token")
    ESUserEntity getESUserEntitiesByUuidAndToken(@Param("uuid") UUID uuid,
                                                 @Param("token") UUID token);

}
