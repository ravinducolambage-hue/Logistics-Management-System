/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment_endsem;

import java.util.Scanner;


/**
 *
 * @author USER
 */
public class Assignment_endSem {


    static final int MAX_TOWNS = 30;
    static final int MAX_ORDERS = 50;

    static Scanner sc = new Scanner(System.in);

    static String[] townList = new String[MAX_TOWNS];
    static int townCount = 0;

    static int[][] roadDistance = new int[MAX_TOWNS][MAX_TOWNS];

    static String[] vehicleList = {"Van", "Truck", "Lorry"};
    static int[] maxLoad = {1000, 5000, 10000};
    static double[] ratePerKm = {30, 40, 80};
    static double[] avgSpeed = {60, 50, 45};
    static double[] fuelEfficiency = {12, 6, 4};
    static double fuelRate = 310;

    static String[] startCity = new String[MAX_ORDERS];
    static String[] endCity = new String[MAX_ORDERS];
    static double[] loadWeight = new double[MAX_ORDERS];
    static String[] usedVehicle = new String[MAX_ORDERS];
    static double[] totalCharge = new double[MAX_ORDERS];
    static double[] profitList = new double[MAX_ORDERS];
    static double[] timeTaken = new double[MAX_ORDERS];
    static int orderCount = 0;

    public static void main(String[] args) {
        int option;

        do {
            System.out.println("\n===== LOGISTICS MANAGEMENT SYSTEM =====");
            System.out.println("1. City Management");
            System.out.println("2. Distance Management");
            System.out.println("3. New Delivery Request");
            System.out.println("4. Performance Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    manageCities();
                    break;
                case 2:
                    manageDistances();
                    break;
                case 3:
                    recordDelivery();
                    break;
                case 4:
                    showPerformance();
                    break;
                case 5:
                    System.out.println("Program exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (option != 5);
    }

    public static void manageCities() {
        int choice;
        do {
            System.out.println("\n--- CITY MANAGEMENT ---");
            System.out.println("1. Add City");
            System.out.println("2. Rename City");
            System.out.println("3. Remove City");
            System.out.println("4. View Cities");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addTown();
                    break;
                case 2:
                    renameTown();
                    break;
                case 3:
                    deleteTown();
                    break;
                case 4:
                    listTowns();
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        } while (choice != 5);
    }

    public static void addTown() {
        if (townCount >= MAX_TOWNS) {
            System.out.println("City limit reached!");
            return;
        }
        System.out.print("Enter city name: ");
        String name = sc.nextLine();

        for (int i = 0; i < townCount; i++) {
            if (townList[i].equalsIgnoreCase(name)) {
                System.out.println("City already exists!");
                return;
            }
        }
        townList[townCount] = name;
        townCount++;
        System.out.println("City added successfully!");
    }

    public static void renameTown() {
        listTowns();
        if (townCount == 0) return;
        System.out.print("Enter city number to rename: ");
        int n = sc.nextInt();
        sc.nextLine();

        if (n < 1 || n > townCount) {
            System.out.println("Invalid selection!");
            return;
        }

        System.out.print("Enter new name: ");
        String newName = sc.nextLine();
        townList[n - 1] = newName;
        System.out.println("City renamed successfully!");
    }

    public static void deleteTown() {
        listTowns();
        if (townCount == 0) return;

        System.out.print("Enter city number to remove: ");
        int n = sc.nextInt();
        if (n < 1 || n > townCount) {
            System.out.println("Invalid selection!");
            return;
        }

        for (int i = n - 1; i < townCount - 1; i++) {
            townList[i] = townList[i + 1];
        }
        townCount--;
        System.out.println("City removed!");
    }

    public static void listTowns() {
        System.out.println("\n--- CITY LIST ---");
        if (townCount == 0) {
            System.out.println("No cities available!");
            return;
        }
        for (int i = 0; i < townCount; i++) {
            System.out.println((i + 1) + ". " + townList[i]);
        }
    }

    public static void manageDistances() {
        if (townCount < 2) {
            System.out.println("Add at least two cities first!");
            return;
        }

        int ch;
        do {
            System.out.println("\n--- DISTANCE MANAGEMENT ---");
            System.out.println("1. Enter Distance");
            System.out.println("2. Show Distance Table");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            if (ch == 1) {
                setDistance();
            } else if (ch == 2) {
                showDistanceTable();
            } else if (ch == 3) {
                System.out.println("Returning...");
            } else {
                System.out.println("Invalid choice!");
            }
        } while (ch != 3);
    }

    public static void setDistance() {
        listTowns();
        System.out.print("From city number: ");
        int from = sc.nextInt() - 1;
        System.out.print("To city number: ");
        int to = sc.nextInt() - 1;

        if (from == to) {
            System.out.println("Cannot assign same city!");
            return;
        }

        System.out.print("Enter distance (km): ");
        int dist = sc.nextInt();

        roadDistance[from][to] = dist;
        roadDistance[to][from] = dist;
        System.out.println("Distance recorded successfully!");
    }

    public static void showDistanceTable() {
        System.out.println("\n--- DISTANCE TABLE ---");
        System.out.print("          ");
        for (int i = 0; i < townCount; i++) {
            System.out.printf("%10s", townList[i]);
        }
        System.out.println();

        for (int i = 0; i < townCount; i++) {
            System.out.printf("%10s", townList[i]);
            for (int j = 0; j < townCount; j++) {
                System.out.printf("%10d", roadDistance[i][j]);
            }
            System.out.println();
        }
    }

    public static void recordDelivery() {
        if (townCount < 2) {
            System.out.println("Add at least 2 cities first!");
            return;
        }

        listTowns();
        System.out.print("Enter source city number: ");
        int from = sc.nextInt() - 1;
        System.out.print("Enter destination city number: ");
        int to = sc.nextInt() - 1;

        if (from == to) {
            System.out.println("Source and destination cannot be the same!");
            return;
        }

        System.out.print("Enter weight (kg): ");
        double weight = sc.nextDouble();

        System.out.println("Select Vehicle: 1.Van  2.Truck  3.Lorry");
        int vehicle = sc.nextInt() - 1;

        if (weight > maxLoad[vehicle]) {
            System.out.println("Weight exceeds vehicle capacity!");
            return;
        }

        int dist = roadDistance[from][to];
        if (dist == 0) {
            System.out.println("Distance not entered yet!");
            return;
        }

        double baseCost = dist * ratePerKm[vehicle] * (1 + weight / 10000);
        double fuelUsed = dist / fuelEfficiency[vehicle];
        double fuelCost = fuelUsed * fuelRate;
        double totalOpCost = baseCost + fuelCost;
        double profit = baseCost * 0.25;
        double customerCharge = totalOpCost + profit;
        double duration = dist / avgSpeed[vehicle];

        System.out.println("\n--- DELIVERY SUMMARY ---");
        System.out.println("From: " + townList[from]);
        System.out.println("To: " + townList[to]);
        System.out.println("Distance: " + dist + " km");
        System.out.println("Vehicle: " + vehicleList[vehicle]);
        System.out.println("Base Cost: " + baseCost + " LKR");
        System.out.println("Fuel Cost: " + fuelCost + " LKR");
        System.out.println("Total Operational Cost: " + totalOpCost + " LKR");
        System.out.println("Profit: " + profit + " LKR");
        System.out.println("Customer Charge: " + customerCharge + " LKR");
        System.out.println("Estimated Time: " + duration + " hours");

        startCity[orderCount] = townList[from];
        endCity[orderCount] = townList[to];
        loadWeight[orderCount] = weight;
        usedVehicle[orderCount] = vehicleList[vehicle];
        totalCharge[orderCount] = customerCharge;
        profitList[orderCount] = profit;
        timeTaken[orderCount] = duration;
        orderCount++;

        System.out.println("Delivery record saved!");
    }

    public static void showPerformance() {
        System.out.println("\n--- PERFORMANCE REPORT ---");
        System.out.println("Total Deliveries: " + orderCount);

        double totalProfit = 0, totalRevenue = 0, totalTime = 0;
        for (int i = 0; i < orderCount; i++) {
            totalProfit += profitList[i];
            totalRevenue += totalCharge[i];
            totalTime += timeTaken[i];
        }

        if (orderCount > 0) {
            System.out.println("Average Delivery Time: " + (totalTime / orderCount) + " hours");
            System.out.println("Total Revenue: " + totalRevenue + " LKR");
            System.out.println("Total Profit: " + totalProfit + " LKR");
        } else {
            System.out.println("No deliveries available yet!");
        }
    }
}
