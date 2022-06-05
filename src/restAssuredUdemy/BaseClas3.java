package restAssuredUdemy;

import io.restassured.RestAssured;
import io.restassured.specification.LogSpecification;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;
//import static org.testng.Assert;



public class BaseClas3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//validate if add place APi is working as expected.
		
		//given - all input details
		//when - submit the API - resource and http method
		//then - validate the response
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//method 1
	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
		body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}").when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)");
	
	//method 2
	given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
	body(payload.AddPlace()).when().post("maps/api/place/add/json")
	.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)");
	
	//Add place -> update place with new address-> get place to validate if the new address is present in response
	String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
	body(payload.AddPlace()).when().post("maps/api/place/add/json")
	.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
	System.out.println("The response is :"+ response);
	
		

	}

}
