package userInterface;

import java.util.Scanner;

import roomManagement.Room;
// import appAccess.User;

public class Terminal {

    public static void main(String[] args) {
        new Terminal();
    }

    public static Scanner sc = new Scanner(System.in);

    Terminal() {
        Room.add("Ruang default 1", 100, 100);
        Room.add("Ruang default 2", 100, 100);

        // Indoor indoor = new Indoor(1, "as", 10, 10, 10);
        // System.out.println(indoor.getRoomVolume());

        boolean closeRoomMenu = false;
        while (closeRoomMenu != true) {
            System.out.print("Room Menu:\n[1] Add New Room\n[2] Show a Room\n[3] Show All Room\n[4] Modify a Room\n[5] Remove a Room\n[6] Remove All Room\n[E] Exit\nYour Input: ");
            String optionInputRoomMenu = sc.next();
            cls();
            switch (optionInputRoomMenu) {
                case "1":
                    Room.add();
                    break;
                case "2":
                    Room.show();
                    break;
                case "3":
                    Room.showList();
                    break;
                case "4":
                    Room.modify();
                    break;
                case "5":
                    Room.remove();
                    break;
                case "6":
                    Room.removeList();
                    break;
                case "E":
                    System.out.println("Goodbye");
                    closeRoomMenu = true;
                    break;
                default:
                    System.out.println("Invalid Input\n");
                    break;
            }
        }
    }

    // clear screen
    public static void cls() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception e) {
            System.err.println("CLS CANNOT BE IMPLEMENTED HERE");
        }
    }

    public static void tableHeader(String border, String content) {
        System.out.printf(border);
        System.out.printf(content);
        System.out.printf(border);
    }
}
