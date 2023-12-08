package bo.edusoft.sbf18.service.impl;


import bo.edusoft.sbf18.exception.DuplicatedEmail;
import bo.edusoft.sbf18.model.dto.request.PhoneRequestDTO;
import bo.edusoft.sbf18.model.dto.request.UserRequestDTO;
import bo.edusoft.sbf18.model.dto.response.UserFullResponseDTO;
import bo.edusoft.sbf18.model.dto.response.UserResponseDTO;
import bo.edusoft.sbf18.model.entities.ESPhoneEntity;
import bo.edusoft.sbf18.model.entities.ESUserEntity;
import bo.edusoft.sbf18.repository.UserRepository;
import bo.edusoft.sbf18.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDTO saveUser(UserRequestDTO inputUser) throws DuplicatedEmail {

        Date currentTime = new Date();

       List<ESUserEntity> users = userRepository.searchUserEntitiesByEmail(inputUser.getEmail());

       if(users.isEmpty()){
           ESUserEntity esUserEntity = new ESUserEntity();
           esUserEntity.setName(inputUser.getName());
           esUserEntity.setEmail(inputUser.getEmail());
           esUserEntity.setCreated(currentTime);
           esUserEntity.setModified(currentTime);
           esUserEntity.setLastLogin(currentTime);
           //esUserEntity.
           //TODO: Improve temporary generation UUID as SpringBoot 2 and JDK 1.8 couldn't autoGenerate the UUID
           esUserEntity.setUuid(UUID.randomUUID());
           esUserEntity.setToken(UUID.randomUUID());
           esUserEntity.setIsActive(true);
           esUserEntity.setPhones(new ArrayList<>());
           for(PhoneRequestDTO phone:inputUser.phones){
               ESPhoneEntity aPhone = new ESPhoneEntity();
               aPhone.setUser(esUserEntity);
               aPhone.setNumber(aPhone.getNumber());
               aPhone.setCityCode(aPhone.getCityCode());
               aPhone.setCountryCode(aPhone.getCountryCode());
               esUserEntity.getPhones().add(aPhone);
           }
           userRepository.save(esUserEntity);
           UserResponseDTO responseDTO = convertESUserEntity(esUserEntity);
           return responseDTO;
       } else {
           throw new DuplicatedEmail();
       }
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<ESUserEntity> users = userRepository.findAll();
        List<UserResponseDTO> finalUsers = new ArrayList<>();
        for(ESUserEntity aUser:users){
            UserResponseDTO dUser = convertESUserEntity(aUser);
            finalUsers.add(dUser);
        }
        return finalUsers;
    }

    private UserResponseDTO convertESUserEntity(ESUserEntity user){
        UserResponseDTO aUser = new UserResponseDTO();
        aUser.setNumericId(user.getId());
        aUser.setId(user.getUuid());
        aUser.setLast_login(user.getLastLogin());
        aUser.setModified(user.getModified());
        aUser.setCreated(user.getCreated());
        aUser.setToken(user.getToken());
        aUser.setIsActive(user.getIsActive());

        return aUser;
    }

    @Override
    public UserFullResponseDTO getUserByUUID(UUID uuid, UUID token)  {
        ESUserEntity user = userRepository.getESUserEntitiesByUuidAndToken(uuid,token);
        if(user!=null){
            //Actualizo la fecha del ultimo login en el momento, dado que la autenticacion fue valida !
            user.setLastLogin(new Date());
            userRepository.save(user);

            UserFullResponseDTO aUser = new UserFullResponseDTO();
            aUser.setCreated(user.getCreated());
            aUser.setId(user.getUuid());
            aUser.setModified(user.getModified());
            aUser.setIsActive(user.getIsActive());
            aUser.setToken(user.getToken());
            aUser.setLast_login(user.getLastLogin());
            aUser.setNumericId(user.getId());
            aUser.setEmail(user.getEmail());

            return  aUser;
        }
        return null;
    }
}
