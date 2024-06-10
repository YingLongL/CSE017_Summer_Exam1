/***********************************
 * Course: Lehigh CSE017-SU2024
 * Assignment: Midterm (v01)
 * Name: Yinglong Lin
 * UID: yile22
 * ********************************* */
import java.util.Comparator;

public class CompareByPrice implements Comparator<Food> {
    @Override
    public int compare(Food f1, Food f2) {
        return Double.compare(f1.getPrice(), f2.getPrice());
    }
}