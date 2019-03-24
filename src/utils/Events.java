package utils;

public enum Events {
    
	USER_INCIDENCE, USER_LOGIN, USER_URGENT_INCIDENCE;
	
	public static Events getEventByString(String event) {
		switch(event.toUpperCase()){
		  case "USER_INCIDENCE":
			  return Events.USER_INCIDENCE;
		  case "USER_LOGIN":
			  return Events.USER_LOGIN;
		  case "USER_URGENT_INCIDENCE":
			  return Events.USER_URGENT_INCIDENCE;
		}
		return null;
	}
	
}
