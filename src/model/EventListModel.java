package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class EventListModel extends AbstractListModel {

	
	private List<Event> evList = new ArrayList<>();

	@Override
	public int getSize() {
		return evList.size();
	}

	@Override
	public Object getElementAt(int index) {
		Event e = evList.get(index);
		return e;
	}
	
	
	public void deleteEvent(int index) {
		evList.remove(index);
		this.fireIntervalRemoved(this, getSize(), getSize()+1);
	}
	
	public Event getevent(int index) {
		return evList.get(index);
	}

	public void addEvent(Event e) {
		// TODO Auto-generated method stub
		evList.add(e);
		this.fireIntervalAdded(this, getSize(), getSize()+1);
	}

	public List<Event> getEventList() {
		return evList;
	}

	public void setEventList(List<Event> eList) {
		this.evList = eList;
	}
	

}
