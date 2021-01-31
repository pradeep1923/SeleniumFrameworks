package Tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Smoketest1 {

	public static void main(String[] args) 
	{
		RestAssured.baseURI="http://api.weatherapi.com/v1/current.json";
		Response res= RestAssured.given().queryParam("key","e9c3b0195be341c795281747202311").
				queryParam("q", "Hyderabad").get();
		System.out.println(res.statusLine());
		System.out.println(res.getContentType());
		System.out.println(res.getBody().asString());

	}

}
