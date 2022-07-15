package roomRecord;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static userInterface.Terminal.tableHeader;
import static userInterface.Terminal.cls;

public class Record {

    private int Id_person;
    private String name_person;
    private String description;

    private static ArrayList<Record> record_list = new ArrayList<>();
    public static Scanner input = new Scanner(System.in);

    public Record(int id, String name, String desc) {
        this.Id_person = id;
        this.name_person = name;
        this.description = desc;
        record_list.add(this);
    }

    public void setRecord(int newId, String newName, String newDesc) {
        this.Id_person = newId;
        this.name_person = newName;
        this.description = newDesc;
    }

    public String getNamePerson() {
        return this.name_person;
    }

    public int getIdPerson() {
        return this.Id_person;
    }

    public String getDescription() {
        return this.description;
    }

    public static void addRecord() {
        System.out.print("Input ID Person : ");
        int id_person = Integer.parseInt(input.next());
        System.out.print("Input Person Name : ");
        String name_person = input.next();
        System.out.print("Input Description : ");
        String desc = input.next();

        Record temp = new Record(id_person, name_person, desc);
        record_list.add(temp);
    }

    public static void removeRecordByName() {
        System.out.print("Choose Person Name to be deleted : ");
        String name = input.next();
        for (Record Person : record_list) {
            if (Person.getNamePerson().equals(name)) {
                record_list.remove(Person);
            } 
            else{
                System.out.println("Person Name Iin'\' on the Record");
            }
        }
    }

    public static void removeAllRecord() {
        record_list.clear();
    }

    public static void dateRecord() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        String formattedDate = date.format(formatDate);
        System.out.println(formatDate);
    }

    public static void displayRecord() {
        dateRecord();
        String line = "+-----+-----------+--------------------+%n",
                content = "| ID  | Name      | Description        |%n";
        tableHeader(line, content);
        String leftAlignFormat = "| %-3s | %-9s | %-18s |%n";
        for (Record People : record_list) {
            System.out.format(leftAlignFormat, People.getIdPerson(), People.getNamePerson(), People.getDescription());
        }
        System.out.printf(line);
        System.out.println();
    }

    public static void Menu() {
        do {
            System.out.print("[1] Add Record\n [2] Show all List\n [3] Remove Record\n Your option: ");
            int option = Integer.parseInt(input.next());

            cls();
            switch (option) {
                case 1:
                    addRecord();
                    break;
                case 2:
                    displayRecord();
                    break;
                case 3:
                    removeRecordByName();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (true);
    }

    public static void main(String[] args) {
        Menu();
    }
}
