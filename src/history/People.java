package history;

public class People {
    private int person_id;
    private String person_name;

    public People(int id, String name) {
        this.person_id = id;
        this.person_name = name;
    }

}

class Custodian extends People {
    public Custodian(int id, String name) {
        super(id, name);
    }
}

// class Lecturer extends People {
// public Lecturer(int id, String name) {
// super(id, name);
// }
// }