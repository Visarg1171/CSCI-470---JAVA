import javax.swing.*;
import java.util.ArrayList;

public class AlbumTable extends JTable{
    private String[] columnNames;
    private Object[][] tData;

    public AlbumTable(){}

    public AlbumTable(ArrayList<Album> albums, String[] colName){
        tData = new Object[albums.size()][3];
        albums.toArray();
        int index = 0;
        for (Album a: albums) {
            tData[index][0] = a.getName(); 			// Create a column0 to get a Name 
            tData[index][1] = a.getArtistName(); 	// Create a column1 to artist name
            tData[index][2] = a.getGenre(); 		// Create a column2 to genre
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

    public Class getColumnClass(int c){
        return getValueAt(0, c).getClass();
    }

    public Object getValueAt(int row, int col) {
        return tData[row][col];
    }


}
