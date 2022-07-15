package roomManagement;

public class Indoor extends roomManagement.Room {
    private static int counter = 1;

    private double height;
    private double volume;

    // public Indoor(int id, String name, double length, double width, double
    // height) {
    // super(id, name, length, width);
    // this.height = height;
    // this.volume = super.getArea() * this.height;
    // }

    public Indoor(int id, String name, double length, double width, double height) {
        super(counter, name, length, width);
        this.height = height;
        this.volume = super.getArea() * this.height;
        counter++;
    }

    // public Indoor(double length, double width, double height) {
    // super(length, width);
    // this.height = height;
    // this.volume = super.getArea() * this.height;
    // }
    // private double getHeight() {
    //     return height;
    // }

     public double getRoomVolume() {
        return this.volume;
    }
}
