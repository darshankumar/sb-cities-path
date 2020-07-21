package com.cities.path.sbcitiespath;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class ConnectionControllerTest extends MainTest {

	private final static Log logger = LogFactory.getLog(ConnectionControllerTest.class);

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void isConnectedIT() {

		Map<String, String> params = new HashMap<>();
		params.put("origin", "Boston");
		params.put("destination", "Newark");

		String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class,
				params);
		Assert.assertEquals("Boston has path to Newark", "yes", body);
	}

	@Test
	public void isNotConnectedIT() {

		Map<String, String> params = new HashMap<>();
		params.put("origin", "Philadelphia");
		params.put("destination", "Albany");

		String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class,
				params);
		Assert.assertEquals("Philadelphia has no path to Albany", "no", body);
	}
	
	@Test
	public void isExceptionConnectedIT() {

		Map<String, String> params = new HashMap<>();
		params.put("origin", "Philadelphia");
		params.put("destination", ""); // end is empty

		String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class,
				params);
		Assert.assertEquals(body, "No Data Found");
		
		Map<String, String> params2 = new HashMap<>();
		params.put("origin", ""); // start is empty
		params.put("destination", "Albany");

		String body2 = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class,
				params);
		Assert.assertEquals(body2, "No Data Found");
		
	}

}
