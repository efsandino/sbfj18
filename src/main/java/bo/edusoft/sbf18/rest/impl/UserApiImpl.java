package bo.edusoft.sbf18.rest.impl;

import bo.edusoft.sbf18.exception.DuplicatedEmail;
import bo.edusoft.sbf18.model.dto.request.PhoneRequestDTO;
import bo.edusoft.sbf18.model.dto.request.UserRequestDTO;
import bo.edusoft.sbf18.model.dto.response.UserFullResponseDTO;
import bo.edusoft.sbf18.model.dto.response.UserResponseDTO;
import bo.edusoft.sbf18.rest.UserApi;
import bo.edusoft.sbf18.service.UserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Component
public class UserApiImpl implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiImpl.class);

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity getDemoUser() {
        PhoneRequestDTO phoneDTO = new PhoneRequestDTO(1234567, 1, 57);
        UserRequestDTO userDTO = new UserRequestDTO();
        userDTO.setName("Juan Rodriguez");
        userDTO.setEmail("juan@rodriguez.org");
        userDTO.setPassword("hunter2");
        userDTO.phones = new PhoneRequestDTO[1];
        userDTO.phones[0] = phoneDTO;
        return new ResponseEntity(userDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getListOfUsers() {
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getPersonalUserInformation(@NotNull @RequestHeader("token")  String token,
                                                     @PathVariable("uuid") String uuid) {

        UserFullResponseDTO user = userService.getUserByUUID(UUID.fromString(uuid),UUID.fromString(token));
        if (user != null) {
            return new ResponseEntity(user, HttpStatus.OK);
        } else {
            String message = new String("Not Found");
            return new ResponseEntity(message, HttpStatus.NOT_FOUND);
        }


    }

    @Override
    public ResponseEntity saveUser(@RequestBody UserRequestDTO userRequest) {
        Gson gsonConverter = new Gson();
        //System.out.println(gsonConverter.toJson(userRequest));
        log.debug(gsonConverter.toJson(userRequest));

        UserResponseDTO responseDTO = null;
        try {
            responseDTO = userService.saveUser(userRequest);
            return new ResponseEntity(responseDTO, HttpStatus.CREATED);
        } catch (DuplicatedEmail e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }

    }


}
