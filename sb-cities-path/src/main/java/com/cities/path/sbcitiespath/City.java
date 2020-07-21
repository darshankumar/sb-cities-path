package com.cities.path.sbcitiespath;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Darshan Bhatia
 *
 */
public class City {

	private String name;
	private Set<City> connection = new HashSet<>();

	public City(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<City> getConnection() {
		return connection;
	}

	public void setConnection(Set<City> connection) {
		this.connection = connection;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "City [name=" + name + "]";
	}

}
