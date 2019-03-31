import java.util.ArrayList;

import javax.swing.JTable;

public class AlbumTable extends JTable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2231876797160652633L;
	private String[] columnNames;
    private Object[][] tData;

    public AlbumTable(){}

    public AlbumTable(ArrayList<Album> albums, String[] colName){
        tData = new Object[albums.size()][3];
        albums.toArray();
        int index = 0;
        for (Album a: albums) {
            tData[index][0] = a.getName(); 
            tData[index][1] = a.getArtistName(); 
            tData[index][2] = a.getGenre(); 
            ++index;
        }

        columnNames = colName;
    }
    public int getRowCount(){
        return tData.length;
    }

    public int getColumnCount(){
        return columnNames.length;
    }

    public Class<? extends Object> getColumnClass(int c){
        return getValueAt(0, c).getClass();
    }

    public Object getValueAt(int row, int col) {
        return tData[row][col];
    }


}
