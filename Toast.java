/***********************************
 * Course: Lehigh CSE017-SU2024
 * Assignment: Midterm (v01)
 * Name: Yinglong Lin
 * UID: yile22
 * ********************************* */
public class Toast extends StarchyFood {
    public Toast(String name, int calories, double price) {
        super(name, calories, price, StarchLocation.BOTTOM);
    }

    @Override
    public String toString() {
        return "Toast(" + super.toString() + ")";
    }
}