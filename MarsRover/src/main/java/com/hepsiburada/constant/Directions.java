package com.hepsiburada.constant;

import java.util.HashMap;
import java.util.Map;

public class Directions {

	private Directions() {

	}

	public static Map<String, String> getCoordinat() {

		HashMap<String, String> directions = new HashMap<>();

		directions.put("WL", "S");

		directions.put("WR", "N");

		directions.put("EL", "N");

		directions.put("ER", "S");

		directions.put("NR", "E");

		directions.put("NL", "W");

		directions.put("SR", "W");

		directions.put("SL", "E");

		directions.put("WM", "W");

		directions.put("EM", "E");

		directions.put("NM", "N");

		directions.put("SM", "S");

		return directions;

	}

}
