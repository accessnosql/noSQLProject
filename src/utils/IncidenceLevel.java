package utils;

public enum IncidenceLevel {

	LOW, MID, HIGH;
	
	public static String[] getIncidenceLevels() {
		String[] s = {IncidenceLevel.LOW.toString(), IncidenceLevel.MID.toString(), IncidenceLevel.HIGH.toString()};
		return s;
	}
}
