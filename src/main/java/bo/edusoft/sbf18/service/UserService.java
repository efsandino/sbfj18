package bo.edusoft.sbf18.service;

import bo.edusoft.sbf18.exception.DuplicatedEmail;
import bo.edusoft.sbf18.model.dto.request.UserRequestDTO;
import bo.edusoft.sbf18.model.dto.response.UserFullResponseDTO;
import bo.edusoft.sbf18.model.dto.response.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService  {
    UserResponseDTO saveUser(UserRequestDTO inputUser) throws DuplicatedEmail;

    List<UserResponseDTO> getAllUsers();

    UserFullResponseDTO getUserByUUID(UUID uuid, UUID token);
}
