package Tests;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import Pojos.Root;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Runner2 {

	public static void main(String[] args) throws Exception
	{
		RestAssured.baseURI="http://api.weatherapi.com/v1/current.xml";
		Response res=RestAssured.given().queryParam("key","e9c3b0195be341c795281747202311")
				.queryParam("q", "Hyderabad").get();
		String o=res.getBody().asString();
		XmlMapper xm= new XmlMapper();
		Root r=xm.readValue(o,Root.class);
		System.out.println(r.location.country);
	}

}
