/***********************************
 * Course: Lehigh CSE017-SU2024
 * Assignment: Midterm (v01)
 * Name: Yinglong Lin
 * UID: yile22
 * ********************************* */
public class Sandwich extends StarchyFood {
    public Sandwich(String name, int calories, double price) {
        super(name, calories, price, StarchLocation.BOTTOM_AND_TOP);
    }

    @Override
    public String toString() {
        return "Sandwich(" + super.toString() + ")";
    }
}