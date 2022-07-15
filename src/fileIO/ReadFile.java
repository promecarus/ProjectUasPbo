package fileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile extends FileIO {
    private String line_read;
    private BufferedReader reader = null;

    public ReadFile(String file_name) throws IOException {
        super(file_name);

        this.readAllData();
    }

    // indexOf with regex
    int indexOfRegEx(String strSource, String strRegExPattern) {
        int index = -1;
        Pattern pattern = Pattern.compile(strRegExPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(strSource);

        // if pattern is found in the source string
        if (matcher.find()) {
            index = matcher.start();
        }
        return index;
    }

    void checkColumnHeader() throws IOException {
        int amountOfColumn = -1;
        reader = new BufferedReader(new FileReader(getFileName()));

        reader.mark(500);

        this.line_read = reader.readLine();
        this.line_read = reader.readLine();
        while (this.line_read.indexOf("|") > 0) {
            this.line_read = this.line_read.substring(this.line_read.indexOf("|") + 1, this.line_read.length());
            amountOfColumn++;
        }

        String[] columnHeader = new String[amountOfColumn];

        reader.reset();

        this.line_read = reader.readLine();
        this.line_read = reader.readLine();
        for (int i = 0; i < amountOfColumn; i++) {
            this.line_read = this.line_read.substring(this.line_read.indexOf("|") + 2, this.line_read.length());
            columnHeader[i] = this.line_read.substring(0, indexOfRegEx(this.line_read, "[a-z] \\W") + 1);
        }

        setColumn(columnHeader);
        reader.reset();
        reader.close();
    }

    void readAllData() throws IOException {
        checkColumnHeader();

        super.row_data = new ArrayList<ArrayList<String>>();

        reader = new BufferedReader(new FileReader(getFileName()));
        this.line_read = reader.readLine();
        this.line_read = reader.readLine();
        this.line_read = reader.readLine();

        while (this.line_read != null) {
            if (!this.line_read.contains("-")) {
                super.data = new ArrayList<>();
                for (int i = 0; i < getColumn().length; i++) {
                    this.line_read = this.line_read.substring(this.line_read.indexOf("|") + 2,
                            this.line_read.length());
                    // save data to arraylist
                    super.data.add(this.line_read.substring(0, indexOfRegEx(this.line_read, "[a-z0-9] \\W") + 1));
                }
                // add data from one dimensional arrayList to two dimensional arrayList
                super.row_data.add(data);
                incrementAmountOfData();
            }
            this.line_read = reader.readLine();

        }
        reader.close();
    }

    void display() throws IOException {
        reader = new BufferedReader(new FileReader(getFileName()));
        this.line_read = reader.readLine();
        while (this.line_read != null) {
            System.out.println(this.line_read);
            this.line_read = reader.readLine();
        }
        reader.close();
    }

    // public static void main(String[] args) throws IOException {
    // ReadFile adminUser = new ReadFile("userAdmin.txt");
    // }
}
