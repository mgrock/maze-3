package cli;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Screens {

    public static void splash() {

        System.out.println("LPOO MAZE");
        System.out.println("Taveira 2013");
        System.out.println("MIEIC");
    }

    public static void separator() {

        System.out.println("");
        System.out.println("----------");
        System.out.println("");
    }

    public static int mazeSize() {

        Scanner in = new Scanner(System.in);
        
        System.out.println("Choose:");
        System.out.println("0  - Default Maze");
        System.out.println("4+ - Random maze with custom measures");
        System.out.print("Number: ");

        try {

            int num = in.nextInt();

            if (num == 0) {
                return 0;

            } else if (num > 4) {
                return num;

            } else {

                System.out.println("Pick a number greater than 4...");
                separator();
                return mazeSize();

            }

        } catch (InputMismatchException e) {

            System.out.println();
            System.out.println("That's not a number really...");
            separator();
            return mazeSize();

        }        
    }
    
    public static int dragonCount(int min, int max){
                        
        Scanner in = new Scanner(System.in);        
        
        System.out.println("How many Dragons?");        
        System.out.print("Number (" + min + "-" + max + "):");

        try {

            int num = in.nextInt();

            if (num > max) {
                
                System.out.println("There cannot be more than " + max + " dragons!");
                separator();
                return dragonCount(min, max);

            } else if (num < min){

                System.out.println("There has to be at least 1 dragon!");
                separator();
                return dragonCount(min, max);

            } else {
                
                return num;
            }

        } catch (InputMismatchException e) {

            System.out.println();
            System.out.println("That's not a number really...");
            separator();
            return dragonCount(min, max);

        }        
    }
    
    public static void actionList(){
        
        // TODO Should come from the app
        System.out.println("W - Up, S - Down, A - Left, D - Right, E - Eagle");        
    }
    
    public static char heroAction(){
        
        Scanner in = new Scanner(System.in);        
                
        System.out.print("Move:");
        
        String command = in.next();
        
        if (Pattern.matches("^[wasde]$", command)) {

            return command.charAt(0);
        }

        System.out.print("Invalid command... ");        
        return heroAction();
                                       
    }
    
    public static void gameOver(){
        
        separator();
        System.out.println("Game Over!");
    }
    
    public static void won(){
        
        separator();
        System.out.println("You win, GG!");
    }
}
