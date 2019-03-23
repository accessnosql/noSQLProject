package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class IncidenceListModel extends AbstractListModel{

	private List<Incidence> eList = new ArrayList<>();

	@Override
	public int getSize() {
		return eList.size();
	}

	@Override
	public Object getElementAt(int index) {
		Incidence e = eList.get(index);
		return e;
	}
	
	
	public void deleteIncidence(int index) {
		eList.remove(index);
		this.fireIntervalRemoved(this, getSize(), getSize()+1);
	}
	
	public Incidence getEmployee(int index) {
		return eList.get(index);
	}

	public void addIncidence(Incidence e) {
		// TODO Auto-generated method stub
		eList.add(e);
		this.fireIntervalAdded(this, getSize(), getSize()+1);
	}

	public List<Incidence> getIncidenceList() {
		return eList;
	}

	public void setIncidenceList(List<Incidence> eList) {
		this.eList = eList;
	}
	

}
