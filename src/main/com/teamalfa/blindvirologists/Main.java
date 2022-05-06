package main.com.teamalfa.blindvirologists;

import main.com.teamalfa.blindvirologists.city.fields.Field;
import main.com.teamalfa.blindvirologists.city.fields.Laboratory;
import main.com.teamalfa.blindvirologists.city.fields.SafeHouse;
import main.com.teamalfa.blindvirologists.turn_handler.Game;
import main.com.teamalfa.blindvirologists.virologist.Virologist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MenuFrame menuFrame = new MenuFrame();
        ControllerRefactor controllerRefactor = new ControllerRefactor();

        System.out.println("Would you like to play the game or run tests?");
        System.out.println("1. Play Game");
        System.out.println("2. Run Tests");

        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            try {
                int choice = Integer.parseInt(inputScanner.nextLine());
                if (choice == 1) {
                    controllerRefactor.startProgram();
                    return;
                }
                if (choice == 2) {
                    controllerRefactor.runTest();
                    return;
                } else {
                    throw new NumberFormatException("Invalid input!");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Incorrect number format! Enter either '1' or '2'!");
            }
        }

    }
}
