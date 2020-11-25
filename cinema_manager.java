package cinema;
import java.util.*;

public class Cinema {

    static Scanner scanner = new Scanner(System.in);
    private static int purchasedTickets;
    private static int currentIncome;
    private static float percentOfPurchasedTickets;
    private static int capacity;
    private static String[][] scheme;

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row");
        int columns = scanner.nextInt();

        boolean exit = false;
        int totalIncome;

        capacity = rows * columns;
        if (capacity <= 60) {
            totalIncome = capacity * 10;
        } else {
            totalIncome = (rows / 2) * columns * 10 + (rows / 2 + 1) * columns * 8;
        }

        scheme = new String[rows + 1][columns + 1];
        createCinema(scheme);

        while (!exit) {
            System.out.println("\n1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            String action = scanner.next();
            System.out.println();

            switch (action) {
                case "1":
                    showSeats(scheme);
                    break;
                case "2":
                    buyTicket(scheme, rows, columns);
                    break;
                case "3":
                    System.out.println("Number of purchased tickets: " + purchasedTickets);
                    String percentage = String.format("Percentage: %.2f", percentOfPurchasedTickets);
                    System.out.println(percentage + "%");
                    System.out.println("Current income: $" + currentIncome);
                    System.out.println("Total income: $" + totalIncome);
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }

    public static void createCinema(String[][] arr) {
        arr[0][0] = " ";
        for (int i = 1; i < arr.length; i++) {
            arr[i][0] = String.valueOf(i);
            for (int j = 1; j < arr[i].length; j++) {
                arr[0][j] = String.valueOf(j);
                arr[i][j] = "S";
            }
        }
    }

    public static void buyTicket(String[][] arr, int rows, int columns) {
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        int seat;

        int ticketPrice;
        if (rows * columns > 60) {
            if (row > rows / 2) {
                ticketPrice = 8;
            } else {
                ticketPrice = 10;
            }
        } else {
            ticketPrice = 10;
        }

        if (row > rows || row < 1) {
            System.out.println("Wrong input!");
            buyTicket(scheme, rows, columns);
        } else {
            System.out.println("Enter a seat number in that row:");
                seat = scanner.nextInt();
                if (seat > columns || seat < 1) {
                    System.out.println("Wrong input!");
                    buyTicket(scheme, rows, columns);
                } else if (arr[row][seat].equals("B")) {
                    System.out.println("That ticket has already been purchased");
                    buyTicket(scheme, rows, columns);
                } else {
                    System.out.println("Ticket price: $" + ticketPrice);
                    arr[row][seat] = "B";
                    purchasedTickets++;
                    percentOfPurchasedTickets = (float) 100 * purchasedTickets / capacity;
                    currentIncome += ticketPrice;
                }
        }
    }

    public static void showSeats(String[][] arr) {
        System.out.println("Cinema: ");
        for (String[] rows : arr) {
            for (String seats : rows) {
                System.out.print(seats + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
