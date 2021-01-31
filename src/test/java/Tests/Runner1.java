package Tests;

import Pojos.Root;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Runner1 {

	public static void main(String[] args) 
	{
		RestAssured.baseURI="http://api.weatherapi.com/v1/current.json";
		Response res=RestAssured.given().queryParam("key","e9c3b0195be341c795281747202311")
				.queryParam("q", "Hyderabad").get();
		JsonPath jp=res.jsonPath();
		Root r=jp.getObject("",Root.class);
		System.out.println(r.location.country);
		System.out.println(r.current.humidity);
		System.out.println(r.current.condition.code);
	}
}
