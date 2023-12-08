package bo.edusoft.sbf18.rest;

import bo.edusoft.sbf18.model.dto.request.UserRequestDTO;
import bo.edusoft.sbf18.model.dto.response.UserResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public interface UserApi {

    @GetMapping(value = "/demoUser", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value ="Returns a demo user", notes="This api doesn't require login",response = List.class)
    @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
    ResponseEntity getDemoUser();

    @GetMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value ="Returns all users from db.", notes="This api doesn't require login",response = List.class)
    @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
    ResponseEntity getListOfUsers();

    @PostMapping(value = "/private/user/{uuid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value ="Returns specific user of the db.", notes="This api requires login.",response = List.class)
    @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
    ResponseEntity getPersonalUserInformation(@NotNull @RequestHeader("token") String token,
                                              @PathVariable("uuid") String uuid);



    @PostMapping(value = "/user",
            produces= {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value="Stores a user in the database.", notes="This is a public API", response= UserResponseDTO.class)
    @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success")
   ResponseEntity saveUser(@RequestBody UserRequestDTO userRequest);


}
