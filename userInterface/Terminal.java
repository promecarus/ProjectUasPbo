package userInterface;

import java.util.Scanner;

import roomManagement.Room;
import appAccess.User;

public class Terminal {

    public static void main(String[] args) {
        new Terminal();
    }

    public static Scanner sc = new Scanner(System.in);

    Terminal() {
        Room.add("Ruang default 1", 100, 100);
        Room.add("Ruang default 2", 100, 100);
        boolean closeRoomMenu = false;
        while (closeRoomMenu != true) {
            System.out.print("Room menu:\n[1] Add new room\n[2] Show a room\n[3] Show all room\n[4] Modify a room\n[5] Remove a room\n[6] Remove all room\n[E] Exit\nYour option: ");
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
                case "E":
                    System.out.println("Goodbye");
                    closeRoomMenu = true;
                    break;
                default:
                    System.out.println("Invalid option\n");
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

    public static void tableHeader(String border, String content){
        System.out.printf(border);
        System.out.printf(content);
        System.out.printf(border);
    }
}
