package fileIO;

import java.util.ArrayList;

public class FileIO {
    private String file_name;
    private int amount_of_data = 0;
    private String[] columns;

    protected ArrayList<ArrayList<String>> row_data;
    protected ArrayList<String> data;

    public FileIO(String file_name) {
        this.file_name = "./Tables/" + file_name;
    }

    // GET filename
    public String getFileName() {
        return this.file_name;
    }

    // GET column
    public String[] getColumn() {
        return this.columns;
    }

    // SET column
    public void setColumn(String[] columns) {
        this.columns = columns;
    }

    // GET Row of data
    public ArrayList<ArrayList<String>> getRowData() {
        return this.row_data;
    }

    public void addRowData(ArrayList<String> data) {
        this.row_data.add(data);
    }

    // GET Amount of data
    public int getAmountOfData() {
        return this.amount_of_data;
    }

    // Data successfully inserted
    public void incrementAmountOfData() {
        this.amount_of_data++;
    }

}
