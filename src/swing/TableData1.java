package swing;

import java.util.List;

import javax.swing.table.AbstractTableModel;



public class TableData1 extends AbstractTableModel {

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	private int columnCount;
	private int rowcount;
	private List<Score> scores;
    private final static String[] columnNames= new String[]{"DATE","SCORE"};
	
    public TableData(List<Score> scores){
        this.scores = scores;
    }

    @Override
    public String getColumnName(int columnIndex){
        return columnNames[columnIndex];
    }
    
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		
		return scores.size();
	}

	 @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        Score score =  scores.get(rowIndex);

	            switch(columnIndex){
	                case 0:
	                    return score.actualTimeString();
	                case 1:
	                    return score.getPointsFormatted();
	            }
	            return null;
	    }
	 
	 @Override
	    public Class<?> getColumnClass(int columnIndex){
	        switch (columnIndex){
	            case 0:
	                return String.class;
	            case 1:
	                return String.class;
	        }
	        return null;
	    }

}

/*
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PropertiesTableModel extends AbstractTableModel{
    private List<Field> fieldList;
    private final static String[] columnNames= new String[]{"Field","Value"};

    public PropertiesTableModel(List<Field> list){
        this.fieldList=list;
    }

    @Override
    public String getColumnName(int columnIndex){
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return fieldList.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    //this method is called to set the value of each cell
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Field field= (Field) fieldList.get(rowIndex);

            switch(columnIndex){
                case 0:
                    return field.getFieldDef().getfName();
                case 1:
                    return field.getDefaultValue();
            }
            return null;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
            case 0:
                return String.class;
            case 1:
                return Object.class;
        }
        return null;
    }
*/

}
