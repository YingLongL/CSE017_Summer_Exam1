/***********************************
 * Course: Lehigh CSE017-SU2024
 * Assignment: Midterm (v01)
 * Name: Yinglong Lin
 * UID: yile22
 * ********************************* */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FoodVendor {
    public static final int MAX_LINES_FILE = 100;
    public static final String DEFAULT_INFILE = "food.txt";

    public static void main(String[] args) {
        String inFname = args.length > 0 ? args[0] : DEFAULT_INFILE;
        System.out.println("Using file: " + inFname);
        Food[] foods = readFood(inFname);
        if (foods == null) {
            throw new RuntimeException("Failed to read food from file.");
        }

        Scanner kbd = new Scanner(System.in);
        int userChoice = -1;
        do {
            printMenu();
            try {
                userChoice = kbd.nextInt();
                kbd.nextLine();
                switch (userChoice) {
                    case 1: // 1. View Foods
                        printFoods(foods);
                        break;
                    case 2: // 2. Search by food name
                        System.out.println("Enter a food name to search for");
                        String fd = kbd.nextLine();
                        System.out.println("Searching for: " + fd);
                        Food found = findFood(fd, foods);
                        System.out.printf("Found food: %s%n", found);
                        break;
                    case 3: // 3. Sort by food natural ordering
                        Arrays.sort(foods);
                        printFoods(foods);
                        break;
                    case 4: // 4. Sort by food price
                        Arrays.sort(foods, new CompareByPrice());
                        printFoods(foods);
                        break;
                    case 5: // 5. Exit
                        System.out.println("Goodbye");
                        break;
                    case 6: // sort by Starch Count.
                        Arrays.sort(foods, (f1, f2) -> {
                            if (f1 instanceof HasStarch && f2 instanceof HasStarch) {
                                return Integer.compare(
                                        ((HasStarch) f1).getStarchLocation().getStarchCount(),
                                        ((HasStarch) f2).getStarchLocation().getStarchCount());
                            } else if (f1 instanceof HasStarch) {
                                return Integer.MIN_VALUE;
                            } else if (f2 instanceof HasStarch) {
                                return Integer.MAX_VALUE;
                            }
                            return 0;
                        });
                        printFoods(foods);
                        break;
                    default:
                        System.err.println("Invalid option");
                }
            } catch (InputMismatchException e) {
                System.err.println("Not an int");
                kbd.nextLine(); // clear out rest of input line
            }
        } while (userChoice != 5);
    }

    public static void printMenu() {
        System.out.printf("%nSelect an Operation%n");
        System.out.println("1. View Foods");
        System.out.println("2. Search by food name ");
        System.out.println("3. Sort by food calories");
        System.out.println("4. Sort by food price");
        System.out.println("5. Exit");
    }

    public static void printFoods(Food[] list) {
        System.out.printf("%nFood:%n");
        String headerFormat = "|%8s|%15s|%8s|%5s|%12s|%12s|%n";
        String[] splitFmt = headerFormat.split("[\\|%sn]+");
        int colSum = Arrays.stream(splitFmt)
                .filter(s -> s.matches("\\d+"))
                .mapToInt(Integer::valueOf)
                .sum();
        System.out.printf(headerFormat,
                "Type", "Name", "Calories", "Price", "Starch count", "Starch loc");
        System.out.println("-".repeat(splitFmt.length + colSum));

        for (Food a : list) {
            HasStarch hs = a instanceof HasStarch ? (HasStarch) a : null;
            System.out.printf("|%8s|%15s|%8s|%5.2f|%12s|%12s|%n",
                    a.getClass().getSimpleName(),
                    a.getName(), a.getCalories(), a.getPrice(),
                    hs == null ? "" : hs.getStarchLocation().getStarchCount(),
                    hs == null ? "" : hs.getStarchLocation().getDescription());
        }
    }

    public static Food findFood(String fName, Food[] list) {
        try {
            checkName(fName);
        } catch (InputMismatchException e) {
            System.out.println("Invalid Food name format; Must " +
                    "begin with a capital letter, contain letters only, " +
                    "and be less than 21 characters.");
            return null;
        }
        fName = fName.toLowerCase();
        System.out.println("Searching for name starting with " + fName);
        for (Food f : list) {
            String against = f.getName().toLowerCase();
            System.out.println("Comparing " + fName + " against: " + against);
            if (against.startsWith(fName)) {
                return f;
            }
        }
        System.out.println("Food " + fName + " not found in list.");
        return null;
    }

    private static Food[] removeNulls(Food[] list) {
        if (list == null) return new Food[0];
        int lastNonNull = 0;
        while (lastNonNull < list.length && list[lastNonNull] != null)
            lastNonNull++;
        lastNonNull--;
        if (lastNonNull >= 0) {
            Food[] answer = new Food[lastNonNull + 1];
            System.arraycopy(list, 0, answer, 0, answer.length);
            return answer;
        }
        return new Food[0];
    }

    public static boolean checkName(String n) throws InputMismatchException {
        if (!n.matches("^[A-Z][a-zA-Z]{0,19}$")) {
            throw new InputMismatchException("Invalid Food name format; Must " +
                    "begin with a capital letter, contain letters only, " +
                    "and be less than 21 characters.");
        }
        return true;
    }

    public static Food[] readFood(String filename) {
        System.out.println("Reading food from file: " + filename);

        List<Food> foodList = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\s+");

                if (parts.length >= 4) {
                    String type = parts[0];
                    String name = parts[1];
                    int calories = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);

                    Food food;
                    switch (type) {
                        case "Sandwich":
                            food = new Sandwich(name, calories, price);
                            break;
                        case "Salad":
                            food = new Salad(name, calories, price);
                            break;
                        case "Toast":
                            food = new Toast(name, calories, price);
                            break;
                        default:
                            System.out.println("Unknown food type: " + type);
                            continue;
                    }

                    foodList.add(food);
                    System.out.println(food);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            System.exit(1);
        }

        return foodList.toArray(new Food[0]);
    }
}