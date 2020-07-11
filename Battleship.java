
//Sean He
//This project simulates the game of battleship, where the player has 20 attempts at hitting all of the ships

import java.util.*;

public class Battleship
{
    static char[][] userView = {
            {'~','~','~','~','~','~'},
            {'~','~','~','~','~','~'},
            {'~','~','~','~','~','~'},
            {'~','~','~','~','~','~'},
            {'~','~','~','~','~','~'},
            {'~','~','~','~','~','~'}
    };
    static char[][] board = {
            {'A','~','~','~','~','~'},
            {'B','B','~','~','~','~'},
            {'C','C','C','~','~','~'},
            {'D','D','D','D','~','~'},
            {'E','E','E','E','E','~'},
            {'~','~','~','~','~','~'}
    };
    //the two 2d arrays are the game board and the defaut board
    static char[] letters = {'A','B','C','D','E','F'};
    static int[] nums = {1,2,3,4,5,6};
    static HashMap<Character, Integer> ships = new HashMap<Character, Integer>();

    public static void printDirections()
    {
        System.out.println("Welcome to the game of Battleship!");
        System.out.println("");
        System.out.println("Your job is to uncover all of the hidden ships on the board.");
        System.out.println("You will enter a letter for a row (A-F) and a number for");
        System.out.println("the column (1-6) to undicate which spot you want to hit.");
        System.out.println("");
        System.out.println("There are 5 ships you will need to find:");
        System.out.println("    The Aircraft Carrier (A) -- covers 1 slots");
        System.out.println("    The Battleship (B) -- covers 2 slots");
        System.out.println("    The Cruiser (C) -- covers 3 slots");
        System.out.println("    The Destroyer (D) -- covers 4 slots");
        System.out.println("    The Elephant (E) -- covers 5 slot");
        System.out.println("");
        System.out.println("    You have 20  attempts to uncover all of the enemies ships.");
        System.out.println("    Good luck!");
        System.out.println("");
        System.out.println("");
        //this method must print the directions on how to play the game
    }

    public static void printUserBoard()
    {
        for(int b = -1; b <=5; b++){
            if(b == -1){
                System.out.print("\t");
            }
            else{
                System.out.print(nums[b] + "\t");
            }
        }
        System.out.println("");
        for(int i = 0; i<6; i++){
            System.out.print(letters[i]);
            System.out.print("\t");
            for(int a = 0; a<6; a++){
                System.out.print(userView[i][a]);
                System.out.print("\t");
            }
            System.out.println("");
        }
        //this method must print the game board that the user will see (userView)

    }

    public static void printBoard(){
        for(int b = -1; b <=5; b++){
            if(b == -1){
                System.out.print("\t");
            }
            else{
                System.out.print(nums[b] + "\t");
            }
        }
        System.out.println("");
        for(int i = 0; i<6; i++){
            System.out.print(letters[i]);
            System.out.print("\t");
            for(int a = 0; a<6; a++){
                System.out.print(board[i][a]);
                System.out.print("\t");
            }
            System.out.println("");
        }
        //this method must print the detaut board that the user will see (userView)

    }

    public static boolean checkSlot(char row, char col, char[] shipLetters, int[] shipHits)
    {
        if(row !='A' && row !='B' &&row !='C' &&row !='D' &&row !='E'&& row!= 'F'){
            return false;
        }
        if(col != '1' && col != '2' && col != '3' && col != '4' && col != '5' &&col != '6'){
            return false;
        }
        int co = Character.getNumericValue(col);
        int temp =0;
        co--;
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != row) {
                temp++;
            }
            else{
                break;
            }
        }
        if(userView[temp][co] != '~'){
            return false;
        }

        if(board[temp][co] == '*'){
            return false;
        }
        if(board[temp][co]=='~'){
            board[temp][co] = '*';
            userView[temp][co] = '*';
            System.out.println("\n I'm sorry. You missed :(\n");
            return true;
        }
        System.out.println("\n Yay! You hit something!\n");
        userView[temp][co] = board[temp][co];
        for(int i = 0; i < 5; i++){
            if(shipLetters[i]==board[temp][co]){
                shipHits[i]++;
            }
        }
        return true;

        //1.  This method will check for the correct input for the row and the col variables
        //2.  The method will also check the slot in the static board 2D array
    }

    public static boolean checkWinner(char[ ] shipLetters, int[ ] shipHits)
    {
        if((shipHits[0] >= ships.get('A'))&&(shipHits[1] >= ships.get('B'))&&(shipHits[2] >= ships.get('C'))&&(shipHits[3] >= ships.get('D'))&&(shipHits[4] >= ships.get('E'))){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args)
    {
        //This is the run method, where everything runs and it takes
        //the imputs from multiple methods
        ships.put('A', 1);
        ships.put('B', 2);
        ships.put('C', 3);
        ships.put('D', 4);
        ships.put('E', 5);
        printDirections();
        printUserBoard();
        int[] shipHits = {0, 0, 0, 0, 0};
        char[] shipLetters = {'A', 'B', 'C', 'D', 'E'};
        for(int t = 0; t <20; t++){
            char ans1;
            char ans2;
            System.out.print("Please enter in a letter to indicate the row: ");
            while(true) {
                if (StdDraw.hasNextKeyTyped()) {
                    ans1 = Character.toUpperCase(StdDraw.nextKeyTyped());
                    break;
                }
            }
            System.out.println(ans1);
            System.out.print("Please enter in a number to indicate the column: ");
            while(true) {
                if (StdDraw.hasNextKeyTyped()) {
                    ans2 = Character.toUpperCase(StdDraw.nextKeyTyped());
                    break;
                }
            }
            System.out.println(ans2);
            while(!checkSlot(ans1, ans2, shipLetters, shipHits)){
                System.out.println("Wrong input. Please try again");
                System.out.print("Please enter in a letter to indicate the row: ");
                while(true) {
                    if (StdDraw.hasNextKeyTyped()) {
                        ans1 = Character.toUpperCase(StdDraw.nextKeyTyped());
                        break;
                    }
                }
                System.out.println(ans1);
                System.out.print("Please enter in a number to indicate the column: ");
                while(true) {
                    if (StdDraw.hasNextKeyTyped()) {
                        ans2 = Character.toUpperCase(StdDraw.nextKeyTyped());
                        break;
                    }
                }
                System.out.println(ans2);
            }
            printUserBoard();
            if(checkWinner(shipLetters, shipHits)){
                t = 20;
                //this ends the game early if the player hits all ships
            }
        }

        //At the end of the 20 turns, this checks to see if the player won
        if(checkWinner(shipLetters, shipHits)){
            System.out.println("\nYou Won");
            System.out.println("Here's the winning board: \n");
            printBoard();
        }
        else{
            System.out.println("\n~~I'm sorry, but you lost.~~");
            System.out.println("Here's the winning board: \n");
            printBoard();
        }
        System.out.println("thanks for playing!!! ");
    }


}
