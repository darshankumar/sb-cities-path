package com.cities.path.sbcitiespath;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 
 * @author Darshan Bhatia
 *
 */
@Component
public class PathUtil {
	private static final Log logger = LogFactory.getLog(PathUtil.class);
	private Map<String, City> cityMap = new HashMap<>();

	@Value("${data.file}")
	private String citidata;

	@Autowired
	private ResourceLoader resourceLoader;
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @return boolean
	 */
	public static boolean isPathFound(City start, City end) {

		logger.info("start city : " + start.getName() + ", end city: " + end.getName());

		if (start.equals(end))
			return true; // if same city return true

		if (start.getConnection().contains(end))
			return true; // if origin is direct connection to destination

		Set<City> visited = new HashSet<>();
		visited.add(start); // visiting to start

		Deque<City> bucketlist = new ArrayDeque<>();
		bucketlist.addAll(start.getConnection());

		// Now we need to check in connection of connection
		while (!bucketlist.isEmpty()) {

			City city = bucketlist.getLast(); // take each city

			if (city.equals(end))
				return true; // if it end city then return true

			if (!visited.contains(city)) { // do we already verified it
				visited.add(city); //
				bucketlist.addAll(city.getConnection()); // need to check in connection of connection
				bucketlist.removeAll(visited); // remove already visited one
			} else {
				bucketlist.remove(city); // if verified then remove it
			}
		}

		return false;
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public String loadConutryMapFromFile() throws IOException {
		cityMap.clear(); // remove old file data
		logger.info("Reading data");

		Resource resource = resourceLoader.getResource(citidata);

		InputStream is;

		if (!resource.exists()) {
			// file on the filesystem path
			is = new FileInputStream(new File(citidata));
		} else {
			// file is a classpath resource
			is = resource.getInputStream();
		}

		Scanner scanner = new Scanner(is);

		while (scanner.hasNext()) {

			String line = scanner.nextLine();
			if (StringUtils.isEmpty(line))
				continue;

			logger.info(line);

			String[] split = line.split(",");
			String start = split[0].trim();
			String end = split[1].trim();

			if (!start.equals(end)) {
				City A = cityMap.getOrDefault(start, new City(start));
				City B = cityMap.getOrDefault(end, new City(end));

				A.getConnection().add(B);
				B.getConnection().add(A);

				cityMap.put(A.getName(), A);
				cityMap.put(B.getName(), B);
			}
		}

		logger.info("Map: " + cityMap);

		return "OK";
	}

	public Map<String, City> getCityMap() {
		return cityMap;
	}
}
