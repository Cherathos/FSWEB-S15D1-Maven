
package org.example.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Grocery {

    public static ArrayList<String> groceryList = new ArrayList<>();

    public static void startGrocery() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- GROCERY MENU ---");
            System.out.println("0 - Exit");
            System.out.println("1 - Add items");
            System.out.println("2 - Remove items");
            System.out.print("Seçiminiz: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "0":
                    System.out.println("Uygulamadan çıkılıyor...");
                    return;

                case "1":
                    System.out.println("Eklenmesini istediğiniz elemanları giriniz.");
                    String addInput = scanner.nextLine();

                    addItems(addInput);
                    printSorted();
                    break;

                case "2":
                    System.out.println("Çıkarılmasını istediğiniz elemanları giriniz.");
                    String removeInput = scanner.nextLine();

                    removeItems(removeInput);
                    printSorted();
                    break;

                default:
                    System.out.println("Geçersiz seçim! Tekrar deneyiniz.");
            }
        }
    }

    public static void addItems(String input) {

        String[] items = input.split(",");

        for (String item : items) {

            String product = item.trim();

            if (product.isEmpty()) {
                continue;
            }

            if (!checkItemIsInList(product)) {
                groceryList.add(product);
                System.out.println(product + " listeye eklendi.");
            } else {
                System.out.println(product + " zaten listede mevcut.");
            }
        }

        Collections.sort(groceryList, String.CASE_INSENSITIVE_ORDER);
    }

    public static void removeItems(String input) {

        String[] items = input.split(",");

        for (String item : items) {

            String product = item.trim();

            if (product.isEmpty()) {
                continue;
            }

            if (checkItemIsInList(product)) {

                groceryList.removeIf(
                        existing -> existing.equalsIgnoreCase(product)
                );

                System.out.println(product + " listeden çıkarıldı.");

            } else {
                System.out.println(product + " listede bulunamadı.");
            }
        }

        Collections.sort(groceryList, String.CASE_INSENSITIVE_ORDER);
    }

    public static boolean checkItemIsInList(String product) {

        for (String item : groceryList) {
            if (item.equalsIgnoreCase(product.trim())) {
                return true;
            }
        }

        return false;
    }

    public static void printSorted() {

        Collections.sort(groceryList, String.CASE_INSENSITIVE_ORDER);

        System.out.println("\n--- GROCERY LIST ---");

        for (String item : groceryList) {
            System.out.println(item);
        }

        if (groceryList.isEmpty()) {
            System.out.println("Liste boş.");
        }
    }
}
