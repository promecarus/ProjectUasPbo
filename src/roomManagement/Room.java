package roomManagement;

import roomManagement.Indoor;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;
import static userInterface.Terminal.cls;
import static userInterface.Terminal.tableHeader;

public class Room {
    private static ArrayList<Room> roomList = new ArrayList<>();

    private enum Status {
        Maintenance, Available, Booked, Used
    }

    public static Scanner sc = new Scanner(System.in);
    private static int counter = 1;

    private int id;
    private String name;
    private String specified = null;
    private double length;
    private double width;
    private double height = 0;
    private double area;
    public double volume;
    private int capacity;
    private String furniture = null;
    private Status status = Status.Available; // Maintenance, Used, Booked, Available
    private String BookedBy = null;

    // constructor v1.0
    public Room(int id, String name, double length, double width) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.width = width;
        this.area = this.length * this.width;
        this.capacity = (int) this.area / 2;
        roomList.add(this);
    }

    // constructor v2.0
    public Room(double length, double width) {
        // this.id = counter;
        this.name = "Not Named Yet";
        this.length = length;
        this.width = width;
        this.area = this.length * this.width;
        this.capacity = (int) this.area / 2;
        roomList.add(this);
        // counter++;
    }

    // setter & getter
    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSpecified(String specified) {
        this.specified = specified;
    }

    public String getSpecified() {
        String result = "";
        if (this.specified == null) {
            result = "Default";
        } else {
            result = this.specified;
        }
        return result;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getLength() {
        return this.length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setArea(double length, double width) {
        this.area = length * width;
    }

    public double getArea() {
        return this.area;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getVolume() {
        return this.volume;
    }

    public double getVolume(double area, double height) {
        return area * height;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public String getFurniture() {
        return this.furniture;
    }

    // set room status => Maintenance, Used, Booked, Available
    public void setStatus(String status) {
        switch (status) {
            case "1":
                this.status = Status.Maintenance;
                break;
            case "2":
                this.status = Status.Available;
                break;
            case "3":
                this.status = Status.Booked;
                break;
            case "4":
                this.status = Status.Used;
                break;
            default:
                System.out.println("Room Status not yet updated because of unknown status input");
                break;
        }
    }

    public Status getStatus() {
        return this.status;
    }

    public String getBookedBy() {
        return BookedBy;
    }

    public void setBookedBy(String BookedBy) {
        this.BookedBy = BookedBy;
    }

    // add
    public static void add() {
        System.out.print("Add new room\nName   : ");
        // String name = sc.nextLine();
        String name = sc.next();
        name += sc.nextLine();

        System.out.print("Length : ");
        int length = Integer.parseInt(sc.next());

        System.out.print("Width  : ");
        int width = Integer.parseInt(sc.next());

        System.out.println();

        new Room(counter, name, length, width);
        counter++;
    }

    // Overloading void add
    public static void add(String name, double length, double width) {
        new Room(counter, name, length, width);
        counter++;
    }

    public static void show() {
        if (noRoomGuard()) {
            return;
        } else {

            boolean isExists = false;
            System.out.print("Choose a room ID to be seen: ");
            int id = Integer.parseInt(sc.next());

            for (Room room : roomList) {
                if (room.getId() == id) {
                    cls();
                    System.out.println("ID        : " + room.getId());
                    System.out.println("Name      : " + room.getName());
                    if (room.getSpecified() != null) {
                        System.out.println("Specified : " + room.getSpecified());
                    }
                    System.out.println("Length    : " + room.getLength() + " m");
                    System.out.println("Width     : " + room.getWidth() + " m");
                    if (room.getSpecified() != null && room.getHeight() > 0) {
                        System.out.println("Height    : " + room.getHeight() + " m");
                    }
                    System.out.println("Area      : " + room.getArea() + " m\u00B2");
                    if (room.getSpecified() != null && room.getHeight() > 0) {
                        System.out.println("Volume    : " + room.getVolume() + " m\u00B3");
                    }
                    System.out.println("Capacity  : " + room.getCapacity() + " person");
                    System.out.println("Status    : " + room.getStatus());
                    if (room.getBookedBy() != null) {
                        System.out.println("Booked by : " + room.getBookedBy());
                    }
                    System.out.println();
                    isExists = true;
                }
            }
            isRoomExists(isExists);
            ;
        }
    }

    public static void showList() {
        if (noRoomGuard()) {
            return;
        } else {
            String line = "+-----+------------------------+-----------+-------------+-------------+----------------+%n",
                    content = "| ID  | Name                   | Specified | Area        | Status      | Capacity       |%n";
            tableHeader(line, content);
            String leftAlignFormat = "| %-3s | %-22s | %-9s | %-11s | %-11s | %-14s |%n";
            for (Room room : roomList) {
                System.out.format(leftAlignFormat, room.getId(), room.getName(), room.getSpecified(),
                        room.getArea() + "m\u00B2",
                        room.getStatus(), room.getCapacity() + " person");
            }
            System.out.format(line);
            System.out.println();
        }
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
                    boolean closeModifyRoomByName = false;
                    isExists = true;
                    while (closeModifyRoomByName != true) {
                        System.out.print(
                                "Room Attributes:\n[1] Name\n[2] Length\n[3] Width\n[4] Status\n[5] Specified\n[E] Exit\nWhich Attribute Do You Want to Change?: ");
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
                                System.out.println("Change from \"" + room.getStatus() + "\" to ");
                                int i = 1;
                                for (Status info : EnumSet.allOf(Status.class)) {
                                    System.out.println("[" + i + "] " + info);
                                    i++;
                                }
                                System.out.print("Your input: ");
                                String status = sc.next();
                                room.setStatus(status);
                                if (status.equals("3")) {
                                    System.out.print("By: ");
                                    String by = sc.next();
                                    room.setBookedBy(by);
                                    System.out.println();
                                }
                                break;
                            case "5":
                                System.out.print("Specified room to:\n[1] Indoor\n[2] Outdoor\nYour input: ");
                                int specified = sc.nextInt();
                                if (specified == 1) {
                                    System.out.print("Height : ");
                                    double height = sc.nextDouble();
                                    // room.specifiedIndoor(room, height);
                                    // Indoor temp = new Indoor(room.getId(), room.getName(), room.getLength(),
                                    // room.getWidth(), height);
                                    // room.setHeight(temp.getHeight());
                                    // room.setVolume(temp.getVolume());
                                    room.setSpecified("Indoor");
                                    room.setHeight(height);
                                    room.setVolume(room.getVolume(room.getArea(), height));
                                } else if (specified == 2) {
                                    System.out.print("Furniture : ");
                                    String furniture = sc.next();
                                    furniture += sc.nextLine();
                                    // room.specifiedOutdoor(room, furniture);
                                    room.setSpecified("Outdoor");
                                    room.setFurniture(furniture);
                                } else {
                                    System.out.println("Invalid option\n");
                                }
                                System.out.println();
                                break;
                            case "E":
                                closeModifyRoomByName = true;
                                break;
                            default:
                                System.out.println("Invalid option\n");
                                break;
                        }
                        room.setArea(room.getLength(), room.getWidth());
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

    public static void removeList() {
        if (noRoomGuard()) {
            return;
        } else {
            roomList.clear();
        }
    }

    public void specifiedIndoor(Room room, double height) {
        // construct to indoor
        Indoor temp = new Indoor(room.getId(), room.getName(), room.getLength(), room.getWidth(), height);
        room.setHeight(temp.getHeight());
        room.setVolume(temp.getVolume());
    }

    public void specifiedOutdoor(Room room, String furniture) {
        room.setFurniture(furniture);
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