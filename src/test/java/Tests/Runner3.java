package Tests;

import java.util.Scanner;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import Pojos.Root;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Runner3 {

	public static void main(String[] args) throws Exception 
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter city name");
		String c=sc.nextLine();
		System.out.println("Enter city criteria");
		String cc=sc.nextLine();
		sc.close();
		RestAssured.baseURI="http://api.weatherapi.com/v1/current.json";
		Response res1=RestAssured.given().queryParam("key","e9c3b0195be341c795281747202311")
				.queryParam("q",c).get();
		if(cc.equalsIgnoreCase("invalid") && res1.getBody().asString().contains("No matching location found."))
		{
			System.out.println("Test Passed for invalid city name");
		}
		else if(cc.equalsIgnoreCase("valid") && res1.getStatusCode()==200)
		{
			JsonPath jp=res1.jsonPath();
			Root r1=jp.getObject("",Root.class);
			RestAssured.baseURI="http://api.weatherapi.com/v1/current.xml";
			Response res2=RestAssured.given().queryParam("key","e9c3b0195be341c795281747202311")
					.queryParam("q",c).get();
			String s=res2.getBody().asString();
			XmlMapper xm=new XmlMapper();
			Root r2=xm.readValue(s,Root.class);
			System.out.println(r1.current.humidity);
			System.out.println(r2.current.humidity);
			if(r1.current.humidity.equals(r2.current.humidity))
			{
				System.out.println("Test passed with valid city");
			}
			else
			{
				System.out.println("Test failed with valid city");
			}
		}
		else
		{
			System.out.println("Test failed with given test data");
		}
	}
}
