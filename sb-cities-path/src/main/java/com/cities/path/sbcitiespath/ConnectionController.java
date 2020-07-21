package com.cities.path.sbcitiespath;

import java.io.IOException;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Darshan Bhatia
 *
 */
@RestController
public class ConnectionController {
	private final static Log logger = LogFactory.getLog(ConnectionController.class);

	@Autowired
	private PathUtil pathUtil;
	
	/**
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	@ApiOperation(value = "Find if a path exists between two cities", notes = "Returns true if cites connected and false otherwise ", response = String.class)
	@ApiResponses({
			@ApiResponse(code = 404, message = "Either destination or origin city does not exist or invalid", response = NullPointerException.class),
			@ApiResponse(code = 500, message = "Generic error", response = Exception.class) })
	@GetMapping(value = "/connected", produces = "text/plain")
	public String isConnected(@RequestParam String origin, @RequestParam String destination) {
		if (pathUtil.getCityMap().size() == 0) {
			logger.error("Data is not available");
			return "no data exist";
		}
		City start = pathUtil.getCityMap().get(origin);
		City end = pathUtil.getCityMap().get(destination);

		Objects.requireNonNull(start, "Unknown City " + origin);
		Objects.requireNonNull(end, "Unknown City " + destination);

		return PathUtil.isPathFound(start, end) ? "yes" : "no";
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	@GetMapping(value = "/loaddata", produces = "text/plain")
	public String loadConutryMapFromFile() throws IOException {
		pathUtil.loadConutryMapFromFile(); // load from file externally no restart needed
		return "OK";
	}
	
	/**
	 * 
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String cityError() {
		return "No Data Found";
	}
}
