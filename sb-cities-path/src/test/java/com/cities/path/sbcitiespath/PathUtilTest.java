package com.cities.path.sbcitiespath;

import org.junit.Assert;
import org.junit.Test;

public class PathUtilTest extends MainTest {

	@Test
	public void testCityMap() {
		Assert.assertNotEquals("Citi Map Can't be Empty", pathutil.getCityMap().size(), 0);
		Assert.assertEquals("Citi Map Size is 6 ", pathutil.getCityMap().size(), 6);
	}

	@Test
	public void testisPathFound() {
		City Boston = pathutil.getCityMap().get("Boston");
		City Newark = pathutil.getCityMap().get("Newark");
		City Philadelphia = pathutil.getCityMap().get("Philadelphia");
		City Albany = pathutil.getCityMap().get("Albany");
		Assert.assertEquals("Boston is connected to Newark ", pathutil.isPathFound(Boston, Newark), true);
		Assert.assertEquals("Boston is connected to Newark ", pathutil.isPathFound(Boston, Philadelphia), true);
		Assert.assertEquals("Boston is not connected to Newark ", pathutil.isPathFound(Philadelphia, Albany), false);
	}

}
