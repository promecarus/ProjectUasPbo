package fileIO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToFile extends FileIO {
    private String line_to_write;

    private BufferedWriter writer = null;
    private String border_line;
    private int[] data_length;

    public WriteToFile(String file_name, String[] columns) throws IOException {
        super(file_name);
        setColumn(columns);

        super.row_data = new ArrayList<ArrayList<String>>();

        this.data_length = new int[getColumn().length];
        for (int i = 0; i < getColumn().length; i++) {
            this.data_length[i] = getColumn()[i].length();
        }
    }

    // set Line Border
    void setBorder() {
        this.border_line = " +";
        for (int i = 0; i < getColumn().length; i++) {
            this.border_line += "-".repeat(this.data_length[i] + 2) + "+";
        }
    }

    void setTableBorderSize() {
        if (getAmountOfData() > 0) {
            for (int i = 0; i < getAmountOfData(); i++) {
                for (int j = 0; j < getColumn().length; j++) {
                    if (getRowData().get(i).get(j).length() > this.data_length[j]) {
                        this.data_length[j] = getRowData().get(i).get(j).length();
                    }
                }
            }
        }
    }

    void placeHeader() throws IOException {

        this.line_to_write = " | ";
        for (int i = 0; i < getColumn().length; i++) {
            this.line_to_write += getColumn()[i] + " ".repeat(this.data_length[i] - getColumn()[i].length())
                    + " | ";
        }

        // File Allocation
        this.writer = new BufferedWriter(new FileWriter(getFileName()));

        this.writer.write(this.border_line);
        this.writer.newLine();

        this.writer.write(this.line_to_write);

        this.writer.newLine();
        this.writer.write(this.border_line);
        this.writer.newLine();

        // write in
        this.writer.flush();
    }

    public void insertData(String[] arrayOfData) throws IOException {
        super.data = new ArrayList<>();
        for (int i = 0; i < getColumn().length; i++) {
            super.data.add(arrayOfData[i]);
        }
        addRowData(super.data);
        incrementAmountOfData();
        setTableBorderSize();
        setBorder();
    }

    public void printTable() throws IOException {
        placeHeader();
        if (getAmountOfData() > 0) {
            for (int i = 0; i < getAmountOfData(); i++) {
                this.line_to_write = " | ";
                for (int j = 0; j < getColumn().length; j++) {
                    this.line_to_write += getRowData().get(i).get(j)
                            + " ".repeat(this.data_length[j] - getRowData().get(i).get(j).length()) + " | ";
                }
                this.writer.write(this.line_to_write);
                this.writer.newLine();
                this.writer.write(this.border_line);
                this.writer.newLine();
            }
        }
        this.writer.flush();
        this.writer.close();
    }

}

class Test {

    public static void main(String[] args) throws IOException {
        String[] userAdmin = { "USERNAME", "PASSWORD" };
        WriteToFile fileUserAdmin = new WriteToFile("userAdmin.txt", userAdmin);
        String[] test = { "Ayam Gaming", "Naga Indosayur" };
        fileUserAdmin.insertData(test);
        fileUserAdmin.insertData(test);
        fileUserAdmin.insertData(test);
        fileUserAdmin.insertData(test);
        fileUserAdmin.insertData(test);
        fileUserAdmin.printTable();
    }
}
