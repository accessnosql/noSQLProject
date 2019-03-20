package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class EmployeeListModel extends AbstractListModel  {
	
	private List<Employee> eList = new ArrayList<>();

	@Override
	public int getSize() {
		return eList.size();
	}

	@Override
	public Object getElementAt(int index) {
		Employee e = eList.get(index);
		return e;
	}
	
	
	public void deleteUser(int index) {
		eList.remove(index);
		this.fireIntervalRemoved(this, getSize(), getSize()+1);
	}
	
	public Employee getEmployee(int index) {
		return eList.get(index);
	}

	public void addEmployee(Employee e) {
		// TODO Auto-generated method stub
		eList.add(e);
		this.fireIntervalAdded(this, getSize(), getSize()+1);
	}

	public List<Employee> getEmployeesList() {
		return eList;
	}

	public void setEmployeesList(List<Employee> eList) {
		this.eList = eList;
	}
	

}
