package exceptions;

import java.util.Arrays;
import java.util.List;

public class EmployeeException extends Exception {

	    public static final int WRONG_LOGIN = 0;
	    public static final int WRONG_PASS_CHECK = 1;
	    public static final int WRONG_PASS = 2;
	    public static final int WRONG_SKILL = 3;
	    public static final int WITHOUT_SKILLS = 4;
	    public static final int DNI_INCORRECT_SIZE = 5;
	    public static final int DNI_INCORRECT_NUM = 6;
	    public static final int ROOM_NUMBER_EXISTS = 7;
	    public static final int WRONG_ROOM_NUM_FORMAT = 8;
	    public static final int INCORRECT_OCCUPANTS_NUM = 9;
	    public static final int NO_ROOMS = 10;
	    public static final int DUPLICATE_WORKER = 11;
	    public static final int NO_SUCH_ROOM = 12;
	    public static final int NOBODY_ON_ROOM = 13;
	    public static final int MILIS_NUMERIC = 14;
	    public static final int DUPLICATE_CUSTOMER = 15;
	    public static final int INCORRECT_MONEY = 16;
	    public static final int INCORRECT_BROKEN_SERVICES = 17;
	    public static final int NUM_13 = 18;
	    
	    private int code;
	    
	    private final List<String> messages = Arrays.asList(
	            "<ERROR 001 : No user with that name/pass found>",
	            "<ERROR 002 : Passwords dont match>",
	            "<ERROR 003 : Wrong pass>",
	            "<ERROR 004 : Skill for worker not recognized>",
	            "<ERROR 005 : This worker has no skills>",
	            "<ERROR 006 : Problem with DNI: incorrect size>",
	            "<ERROR 007 : Problem with DNI: only numbers accepted>",
	            "<ERROR 008 : A room with that number already exists>",
	            "<ERROR 009 : The room number must have 3 digits>",
	            "<ERROR 010 : Incorrect number of ocupants>",
	            "<ERROR 011 : The hotel has no rooms yet>",
			    "<ERROR 012 : There is another worker with that DNI>",
			    "<ERROR 013 : Wrong room number. No such number found>",
			    "<ERROR 014 : No customers found on that room>",
			    "<ERROR 015 : Miliseconds must be a number!>",
			    "<ERROR 016 : There is another customer with that DNI>",
			    "<ERROR 017 : Money error. Must be of format XXXE, whith X = number>",
			    "<ERROR 018 : Incorrect broken services for this room. Check.>",
			    "<ERROR 019 : Number 13 not allowed for room number>",
			    "<ERROR 016 : >"
			    );
	    
	           
	            
	    public EmployeeException(int code) {
	        this.code = code;
	    }

	    public String getMessage() {
	        return messages.get(code);
	    }
	    
}
