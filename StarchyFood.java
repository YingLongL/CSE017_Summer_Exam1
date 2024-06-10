/***********************************
 * Course: Lehigh CSE017-SU2024
 * Assignment: Midterm (v01)
 * Name: Yinglong Lin
 * UID: yile22
 * ********************************* */
public abstract class StarchyFood extends Food implements HasStarch {
    protected StarchLocation starchLoc;

    public StarchyFood(String name, int calories, double price, StarchLocation loc) {
        super(name, calories, price);
        this.starchLoc = loc;
    }

    @Override
    public StarchLocation getStarchLocation() {
        return starchLoc;
    }

    @Override
    public String toString() {
        return super.toString() + ", StarchLocation: " + getStarchLocation();
    }
}
