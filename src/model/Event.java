package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import utils.Events;

public class Event {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
	private Events eventTipo;
	private String employeeKey;
	private LocalDateTime dateTime;
	
	public Event() {}
	
	public Event(Events eventTipo, String employeeKey, LocalDateTime dateTime) {
		this.eventTipo = eventTipo;
		this.employeeKey = employeeKey;
		this.dateTime = dateTime;
	}

	public Events getEventTipo() {
		return eventTipo;
	}

	public void setEventTipo(Events eventTipo) {
		this.eventTipo = eventTipo;
	}

	public String getEmployeeKey() {
		return employeeKey;
	}

	public void setEmployeeKey(String employeeKey) {
		this.employeeKey = employeeKey;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public String getDateTimeFormatted() {
		return dateTime.format(formatter);
	}
	
	
}