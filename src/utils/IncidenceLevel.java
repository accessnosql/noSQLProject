package utils;

public enum IncidenceLevel {

	LOW, MID, HIGH;
	
	public static String[] getIncidenceLevels() {
		String[] s = {IncidenceLevel.LOW.toString(), IncidenceLevel.MID.toString(), IncidenceLevel.HIGH.toString()};
		return s;
	}
	
	public static IncidenceLevel getIncidenceByInt(int s) {
		switch(s) {
		   case 0 : return IncidenceLevel.LOW; 
		   case 1 : return IncidenceLevel.MID; 
		   case 2: return IncidenceLevel.HIGH;
		}
		return null;
	}
	
	public static IncidenceLevel getIncidenceByString(String s) {
		switch(s) {
		   case "LOW" : return IncidenceLevel.LOW; 
		   case "MID" : return IncidenceLevel.MID; 
		   case "HIGH": return IncidenceLevel.HIGH;
		}
		return null;
	}
}
