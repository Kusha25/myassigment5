package com.company;

import com.company.controllers.PreciousController;
import com.company.enteties.Precious;
import com.company.repositories.interfaces.IPreciousRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class StonesFrontEnd {
    private final PreciousController controller;
    private final Scanner scanner;
    private Throwable e;

    public StonesFrontEnd(IPreciousRepository preciousRepository) {
        controller = new PreciousController(preciousRepository);
        scanner = new Scanner(System.in);
    }


    public void start() {

        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Welcome to My Application");
            System.out.println();
            System.out.println("Choose one of options:\n\nGet all stones-1\nGet stone by id-2\nAdd stone-3\nCreate necklace-4\nExit-0");
            choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1 -> getAllPreciousMenu();
                    case 2 -> getPreciousByIdMenu();
                    case 3 -> createPreciousMenu();
                    case 4 -> createNecklaceMenu();
                    case 5 -> exit();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void exit() {
        System.exit(0);
    }


        public void error () {
            System.out.println("That wasn't a choice");
        }





            public void getAllPreciousMenu() {
        String response = controller.getAllPrecious();
        System.out.println(response);
    }

    public void getPreciousByIdMenu(){
        System.out.println("Please enter id");

        int id = scanner.nextInt();
        Precious precious = controller.getPrecious(id);
        System.out.println((precious == null ? "Stone was not found!" : precious.toString()));
    }

    public void createPreciousMenu() {
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter weight");
        int weight = scanner.nextInt();
        System.out.println("Please enter cost");
        int cost = scanner.nextInt();
        System.out.println("Is it precious? (true/false)");
        boolean precious = scanner.nextBoolean();

        String response = controller.createPrecious(name, weight, cost, precious);
        System.out.println(response);
    }

    public void createNecklaceMenu() {
        List<Precious> stones = new ArrayList<>();
        while (true){
            System.out.println();
            System.out.println("Welcome to necklace creation");
            System.out.println("Select option:");
            System.out.println("1. Add stone to the necklace");
            System.out.println("2. Go back");
            System.out.println("3. Start again");
            System.out.println("4. My necklace");
            System.out.println("0. Finish");
            System.out.println();

            try {
                System.out.print("Enter option (1-6): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    addStoneToNecklaceMenu(stones);
                } else if (option == 2) {
                    stones.remove(stones.size()-1);
                    System.out.println("Successfully removed last stone!");
                } else if(option == 3){
                    stones.clear();
                    System.out.println("Successfully removed necklace");
                } else if(option == 4){
                    myNecklace(stones);
                }

                else {
                    System.out.println(controller.calculateNecklace(stones));
                    myNecklace(stones);
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next(); // to ignore incorrect input
            }

            System.out.println("*************************");
        }
    }

    public void addStoneToNecklaceMenu(List<Precious> stones){
        getAllPreciousMenu();
        System.out.println("Choose id of stones from the list: ");

        int choice = scanner.nextInt();
        stones.add(controller.getPrecious(choice));
    }


    public void myNecklace(List<Precious> stones){
        System.out.println("Your necklace consist of: ");
        for(int i=0;i<stones.size();i++){
            System.out.println(stones.get(i).getName() + ", ");
        }
    }
}
