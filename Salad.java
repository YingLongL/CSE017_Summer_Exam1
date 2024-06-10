/***********************************
 * Course: Lehigh CSE017-SU2024
 * Assignment: Midterm (v01)
 * Name: Yinglong Lin
 * UID: yile22
 * ********************************* */
public class Salad extends Food {
    public Salad(String name, int calories, double price) {
        super(name, calories, price);
    }

    @Override
    public String toString() {
        return "Salad(" + super.toString() + ")";
    }
}