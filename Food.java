/***********************************
 * Course: Lehigh CSE017-SU2024
 * Assignment: Midterm (v01)
 * Name: Yinglong Lin
 * UID: yile22
 * ********************************* */
public class Food implements Comparable<Food> {
    private String name;
    private int calories;
    private double price;

    public Food(String name, int calories, double price) {
        this.name = name;
        this.calories = calories;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Food other) {
        return Integer.compare(this.calories, other.calories);
    }

    @Override
    public String toString() {
        return String.format("name: %s, calories: %d, price: $%.2f", name, calories, price);
    }
}
