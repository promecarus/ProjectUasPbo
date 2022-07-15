package roomManagement;

public class Indoor extends Room {
    private double z_width;
    private double volume;

    public Indoor(String name, double firstWidth, double secondWidth, double thirdWidth) {
        super(name, firstWidth, secondWidth);
        this.z_width = thirdWidth;
        this.volume = super.getArea() * this.z_width;
    }

    public Indoor(int firstWidth, int secondWidth, int thirdWidth) {
        super(firstWidth, secondWidth);
        this.z_width = thirdWidth;
        this.volume = super.getArea() * this.z_width;
    }

//    @Override
//    public void showDetails() {
//        System.out.println("Room Name   : " + super.getName());
//        System.out.println("Room Area   : " + super.getArea() + " m^2");
//        System.out.println("Room Volume : " + this.volume + " m^3");
//        System.out.println("Room Status : " + super.getStatus());
//        System.out.println("Room Capacity : " + super.getCapacity() + " Person");
//    }
}
