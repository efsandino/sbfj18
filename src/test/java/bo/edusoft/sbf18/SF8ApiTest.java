package bo.edusoft.sbf18;

import bo.edusoft.sbf18.model.dto.request.PhoneRequestDTO;
import bo.edusoft.sbf18.model.dto.request.UserRequestDTO;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootForJava8Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_CLASS)
public class SF8ApiTest {

	@LocalServerPort
	private int port;

	private String getContextBase(){
		return "/spfj8";
	}
	
	@Before
	public void setup() {
		RestAssured.port = port;
	}
	
	@Test
	public void testGetDemoUser() {
		Response response = RestAssured.when().get(  getContextBase() + "/demoUser");
		assertEquals("200 must be returned", HttpStatus.OK.value(), response.statusCode());
	}
	
	@Test
	public void testGetListOfUsers() {
		Response response = RestAssured.when().get(getContextBase() + "/users");
		assertEquals("200 must be returned", HttpStatus.OK.value(), response.statusCode());
	}
	
	@Test
	public void testGetUnknowUser() {
		Map<String, String> mapa = new HashMap<>();

		// Llenar el mapa con datos
		mapa.put("token", "b12cb34a-12ff-4ee6-97a1-b4d91fd04424");


		Response response = RestAssured.given().header("token","b12cb34a-12ff-4ee6-97a1-b4d91fd04424").when().post(getContextBase() +  "/private/user/53ebe00e-3910-4a1b-b45c-9c247980b816");

		
		assertEquals("404 must be returned", HttpStatus.NOT_FOUND.value(), response.statusCode());
	}
	
	@Test
	public void testAddUser() {
		//Headers headers = populateAdminHeaders();
		Response response = RestAssured.given()
				//.headers(headers)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(this.createMockUserRequestDTO())
				.when().post(getContextBase() + "/user/");
		
		assertEquals("200 must be returned", HttpStatus.CREATED.value(), response.statusCode());
	}

	private UserRequestDTO createMockUserRequestDTO(){
		UserRequestDTO uReq = new UserRequestDTO();
		uReq.setName("Eduardo");
		uReq.setEmail("eduardo.sandino@gmail.com");
		uReq.setPhones(new PhoneRequestDTO[1]);
		uReq.getPhones()[0] = new PhoneRequestDTO();

		uReq.getPhones()[0].setCityCode(3);
		uReq.getPhones()[0].setCountryCode(591);
		uReq.getPhones()[0].setNumber(78025840);

		return uReq;

	}
}
