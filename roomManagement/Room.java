package roomManagement;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;
import static userInterface.Terminal.cls;
import static userInterface.Terminal.tableHeader;

public class Room {
    private static ArrayList<Room> roomList = new ArrayList<>();
    private enum Status { MAINTENANCE, AVAILABLE, BOOKED, USED }
    public static Scanner sc = new Scanner(System.in);
    private static int counter = 1;

    private int id;
    private String name;
    private double length;
    private double width;
    private double area;
    private int capacity;
    private Status status = Status.MAINTENANCE; // maintenance, used, booked, available

    // constructor v1.0
    public Room(String name, double length, double width) {
        this.id = counter;
        this.name = name;
        this.length = length;
        this.width = width;
        this.area = this.length * this.width;
        this.capacity = (int) this.area / 2;
        roomList.add(this);
        counter++;
    }
    
    // constructor v2.0
    public Room(double length, double width) {
        this.id = counter;
        this.name = "not named yet";
        this.length = length;
        this.width = width;
        this.area = this.length * this.width;
        this.capacity = (int) this.area / 2;
        roomList.add(this);
        counter++;
    }

    // setter & getter
    public int getId() { return id; }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setLength(double length) { this.length = length; }
//    public void setLength(String length) { this.length = Double.parseDouble(length); }
    public double getLength() { return length; }

    public void setWidth(double width) { this.width = width; }
//    public void setWidth(String width) { this.length = Double.parseDouble(width); }
    public double getWidth() { return width; }

    public double getArea() {
        return this.area;
    }

    public int getCapacity() {
        return this.capacity;
    }

    // set room status => maintenance, cleaning, booked, av
    public void setStatus(String status) {
        switch (status) {
            case "1":
                this.status = Status.MAINTENANCE;
                break;
            case "2":
                this.status = Status.AVAILABLE;
                break;
            case "3":
                this.status = Status.BOOKED;
                break;
            case "4":
                this.status = Status.USED;
                break;
            default:
                System.out.println("Room Status not yet updated because of unknown status input");
                break;
        }
    }
    public Status getStatus() {
        return this.status;
    }

//    add
    public static void add() {
        System.out.print("Add new room\nName  : ");
        String name = sc.next();
        name += sc.nextLine();

                    System.out.print("Length: ");
        int length = Integer.parseInt(sc.next());

        System.out.print("Width : ");
        int width = Integer.parseInt(sc.next());

        System.out.println();

        new Room(name, length, width);
    }

    public static void add(String name, double length, double width) {
        new Room(name, length, width);
    }

    public static void show() {
        boolean isExists = false;
        System.out.print("Choose a room ID to be seen: ");
        int id = Integer.parseInt(sc.next());

        for (Room room : roomList) {
            if (room.getId() == id) {
                cls();
                System.out.println("ID       : " + room.getId());
                System.out.println("Name     : " + room.getName());
                System.out.println("Length   : " + room.getLength() + " m");
                System.out.println("Width    : " + room.getWidth() + " m");
                System.out.println("Area     : " + room.getArea() + " m\u00B2");
                System.out.println("Capacity : " + room.getCapacity() + " person");
                System.out.println("Status   : " + room.getStatus() +"\n");
                isExists = true;
            }
        }
        isRoomExists(isExists);
    }

    public static void showList() {
        String            line = "+-----+------------------------+-------------+-------------+----------------+%n",
                content = "| ID  | Name                   | Area        | Status      | Capacity       |%n";
        tableHeader(line, content);
        String leftAlignFormat = "| %-3s | %-22s | %-11s | %-11s | %-14s |%n";
        for (Room room : roomList) {
            System.out.format(leftAlignFormat, room.getId(), room.getName(), room.getArea() + "m\u00B2", room.getStatus(), room.getCapacity() + " person");
        }
        System.out.format(line);
        System.out.println();
    }

    public static void modify() {
        if (noRoomGuard()) {
            return;
        } else {
            boolean isExists = false;
            System.out.print("Choose a room ID to be modified: ");
            int id = Integer.parseInt(sc.next());
            
            for (Room room : roomList) {
                if (room.getId() == id) {
                    boolean closemodifyRoomByName = false;
                    isExists = true;
                    while (closemodifyRoomByName != true) {
                        cls();
                        System.out.print(
                                "Room attributes:\n[1] Name\n[2] Length\n[3] Width\n[4] Status\n[E] Exit\nWhich attribute do you want to change?: ");
                        String modifyOptionInput = sc.next();
                        cls();
                        switch (modifyOptionInput) {
                            case "1":
                                System.out.print("Change from \"" + room.getName() + "\" to ");
                                String name = sc.next();
                                name += sc.nextLine();
                                room.setName(name);
                                break;
                            case "2":
                                System.out.print("Change from \"" + room.getLength() + "\" to ");
                                double length = Double.parseDouble(sc.next());
                                room.setLength(length);
                                break;
                            case "3":
                                System.out.print("Change from \"" + room.getWidth() + "\" to ");
                                double width = Double.parseDouble(sc.next());
                                room.setWidth(width);
                                break;
                            case "4":
                                System.out.println("Change from \"" + room.getWidth() + "\" to ");
                                int i = 1;
                                for (Status info : EnumSet.allOf(Status.class)) {
                                    System.out.println("[" + i + "] " + info);
                                    i++;
                                }
                                String status = sc.next();
                                room.setStatus(status);
                                break;
                            case "E":
                                closemodifyRoomByName = true;
                                break;
                            default:
                                System.out.println("Invalid option\n");
                                break;
                        }
                    }
                }
            }
            isRoomExists(isExists);
        }
    }

    public static void remove() {
        if (noRoomGuard()) {
            return;
        } else {
            boolean isExists = false;
            System.out.print("Choose a room ID to be deleted: ");
            int id = Integer.parseInt(sc.next());

            for (Room room : roomList) {
                if (room.getId() == id) {
                    isExists = true;
                    roomList.remove(room);
                }
            }
            isRoomExists(isExists);
        }
    }
    private static void isRoomExists(boolean exists) {
        if (!exists) {
            cls();
            System.out.println("Room not found.\n");
        }
    }

    private static boolean noRoomGuard() {
        boolean checker = false;
        if (roomList.size() == 0) {
            System.out.println("No room found. Consider adding a room first.\n");
            checker = true;
        }
        return checker;
    }
}