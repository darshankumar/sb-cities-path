package com.cities.path.sbcitiespath;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class CityTest {

	@Test
	public void testCity() {
		City city = new City("Pune");
		Assert.assertEquals("Pune", city.getName());
	}

	@Test
	public void testGetConnection() {
		City city = new City("Pune");
		city.getConnection().add(new City("Mumbai"));
		city.getConnection().add(new City("Delhi"));

		Set<City> connection = city.getConnection();
		Assert.assertEquals(2, connection.size());
		Assert.assertTrue(connection.contains(new City("Delhi")));
	}

	@Test
	public void testSetConnection() {
		City city = new City("Pune");
		city.getConnection().add(new City("Mumbai"));
		city.getConnection().add(new City("Delhi"));

		Set<City> connection = city.getConnection();
		Assert.assertEquals(2, connection.size());

	}

	@Test
	public void testSetSameConnection() {
		City city = new City("Pune");
		city.getConnection().add(new City("Mumbai"));
		city.getConnection().add(new City("Delhi"));
		city.getConnection().add(new City("Delhi"));
		city.getConnection().add(new City("Delhi"));
		city.getConnection().add(new City("Delhi"));

		Set<City> connection = city.getConnection();
		Assert.assertEquals(2, connection.size());

	}

}