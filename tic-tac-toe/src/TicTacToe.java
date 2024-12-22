/**
 * Milahd Mansoori -- this is a tic tac toe game with options and it displays the board
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

    static char emptySpaceSymbol = ' ';
    static char playerOneSymbol = 'X';
    static char playerTwoSymbol = 'O';

    public static void main(String[] args)
    {


        // create scanner object and declare variable for do/while
        Scanner scan = new Scanner(System.in);
        String choice;
        String[] playerNames = new String[2];
        ArrayList<char[][]> gameHistory = new ArrayList<>();


        // do this at least once
        do {
            // prompt user(s) for input(choice)
            System.out.println("Welcome to Tic Tac Toe, choose on eof the following options from below:");
            System.out.println("1. Single player");
            System.out.println("2. Two player");
            System.out.println("D. Display last match");
            System.out.println("Q. Quit");
            System.out.print("What do you want to do: ");

            // take user input as a char
            choice = scan.next();

            switch (choice)
            {
                case "1", "2":
                    if(choice.equals("1"))
                    {
                        System.out.print("Enter your name: ");
                        playerNames[0] = scan.next();
                        runOnePlayerGame(playerNames);
                    }
                    else
                    {
                        System.out.print("Enter player 1 name: ");
                        playerNames[0] = scan.next();
                        System.out.print("Enter player 2 name: ");
                        playerNames[1] = scan.next();
                        runTwoPlayerGame(playerNames);
                    }
                    break;

                case "Q":
                    System.out.println("Thanks for playing. I hope you had fun!!!");
                    break;

                case "D":
                    System.out.println();
            }

        }while (!choice.equals("Q"));




    }



    // Given a state, return a String which is the textual representation of the tic-tac-toe board at that state.
    private static String displayGameFromState(char[][] state) {
        // create empty string to get everything in one string at the end
        StringBuilder display = new StringBuilder();

        // loop through rows
        for (int i = 0; i < state.length; i++) {
            // loop through columns
            for (int j = 0; j < state[i].length; j++) {
                // append the symbol at the current position to the display string
                display.append(state[i][j]);

                // append a vertical divider if it's not the last column
                if (j < state[i].length - 1) {
                    display.append("|");
                }
            }

            display.append("\n");

            // append a horizontal divider if it's not the last row
            if (i < state.length - 1) {
                display.append("-+-+-\n");
            }
        }

        // return the final string
        return display.toString();
    }


    // Returns the state of a game that has just started.

    private static char[][] getInitialGameState() {
        return new char[][]{{emptySpaceSymbol, emptySpaceSymbol, emptySpaceSymbol},
                {emptySpaceSymbol, emptySpaceSymbol, emptySpaceSymbol},
                {emptySpaceSymbol, emptySpaceSymbol, emptySpaceSymbol}};
    }

    // Given the player names, run the two-player game.
    // Return an ArrayList of game states of each turn -- in other words, the gameHistory
    private static ArrayList<char[][]> runTwoPlayerGame(String[] playerNames) {

        String player1 = playerNames[0];
        String player2 = playerNames[1];

        // create objects and variables
        // game state double array object
        char[][] currentState = getInitialGameState();
        // creating ArrayList gameHistory
        ArrayList<char[][]> gameHistory = new ArrayList<>();

        // add state to history
        gameHistory.add(currentState);

        System.out.println("\nTossing a coin to decide on who goes first!!!");

        // Generate a random number between 0 and 1 (inclusive)
        int coinToss = (int) (Math.random() * 2);

        // Check if the result is 0 or 1 and print the corresponding message
        if (coinToss == 0)
        {
            System.out.println(player1 + " gets to go first.");
        } else
        {
            System.out.println(player2 + " gets to go first.");
        }

        // main loop for game
        while (true)
        {
            // player one's turn
            if (coinToss == 0)
            {
                // print statements and method calls to allow player 1 to play
                System.out.println(player1 + "'s turn: ");
                currentState = runPlayerMove(player1, playerOneSymbol, currentState);
                gameHistory.add(currentState);
                // display move
                System.out.println(displayGameFromState(currentState));

                // check for win or draw based on move
                if (checkWin(currentState))
                {
                    System.out.println(player1 + " wins!");
                    break;
                } else if (checkDraw(currentState))
                {
                    System.out.println("It's a draw!");
                    break;
                }

                coinToss = 1;
            }
            if (coinToss == 1)
            {
                System.out.println(player2 + "'s turn: ");
                currentState = runPlayerMove(player2, playerTwoSymbol, currentState);
                gameHistory.add(currentState);
                // display move
                System.out.println(displayGameFromState(currentState));


                // check for win or draw
                if (checkWin(currentState))
                {
                    System.out.println(player2 + " wins!");
                    break;
                } else if (checkDraw(currentState)) {
                    System.out.println("It's a draw!");
                    break;
                }

                // switch to player one's turn
                coinToss = 0;
            }
        }



        return gameHistory;
    }

    // Given the player names (where player two is "Computer"),
    // Run the one-player game.
    // Return an ArrayList of game states of each turn -- in other words, the gameHistory
    private static ArrayList<char[][]> runOnePlayerGame(String[] playerNames) {
        // create objects and variables
        // game state double array object
        char[][] currentState = getInitialGameState();
        // creating ArrayList gameHistory
        ArrayList<char[][]> gameHistory = new ArrayList<>();

        // add state to history
        gameHistory.add(currentState);

        // get player names
        String player1 = playerNames[0];
        String player2 = "Computer";
        System.out.println("\nTossing a coin to decide on who goes first!!!");

        // Generate a random number between 0 and 1 (inclusive)
        int coinToss = (int) (Math.random() * 2);

        // Check if the result is 0 or 1 and print the corresponding message
        if (coinToss == 0)
        {
            System.out.println(player1 + " gets to go first.");
        } else
        {
            System.out.println(player2 + " gets to go first.");
        }

        // main loop for game
        while (true)
        {
            // player one's turn
            if (coinToss == 0)
            {
                // print statements and method calls to allow player 1 to play
                System.out.println(player1 + "'s turn: ");
                currentState = runPlayerMove(player1, playerOneSymbol, currentState);
                gameHistory.add(currentState);
                // display move
                System.out.println(displayGameFromState(currentState));

                // check for win or draw based on move
                if (checkWin(currentState))
                {
                    System.out.println(player1 + " wins!");
                    break;
                } else if (checkDraw(currentState))
                {
                    System.out.println("It's a draw!");
                    break;
                }

                coinToss = 1;
            }
            if (coinToss == 1)
            {
                System.out.println(player2 + "'s turn:");
                currentState = getCPUMove(currentState);
                gameHistory.add(currentState);
                System.out.println(displayGameFromState(currentState));

                // check for win or draw
                if (checkWin(currentState))
                {
                    System.out.println(player2 + " wins!");
                    break;
                } else if (checkDraw(currentState)) {
                    System.out.println("It's a draw!");
                    break;
                }

                // switch to player one's turn
                coinToss = 0;
            }
        }

        // return the game history
        return gameHistory;
    }


    // Repeatedly prompts player for move in current state, returning new state after their valid move is made
    private static char[][] runPlayerMove(String playerName, char playerSymbol, char[][] currentState)
    {
        Scanner sc = new Scanner(System.in);

        // variables: array move(row, col)
        // boolean flag to initiate while loop when condition is true(!false to begin)
        int[] move = getInBoundsPlayerMove(playerName);
        boolean validMove = false;
        //
        while (!validMove)
        {
            validMove = checkValidMove(move, currentState);
        }
        // return new state after valid move
        return makeMove(move, playerSymbol, currentState);
    }


    // Repeatedly prompts player for move. Returns [row, column] of their desired move such that row & column are on
    // the 3x3 board, but does not check for availability of that space.
    private static int[] getInBoundsPlayerMove(String playerName) {
        Scanner sc = new Scanner(System.in);
        int[] bounds = new int[2];
        // declaring variables outside so in scope of do while
        int row;
        int col;
        do
        {
            System.out.println(playerName + " enter row: ");
            row = sc.nextInt();

            System.out.println(playerName + " enter col: ");
            col = sc.nextInt();

        } while (row < 1 || row > 3 || col < 1 || col > 3);
        // while loop repeatedly prompts if row or col are less than 0 or more than 2(index of board 0,1,2)

        // return new int array with the players move in bounds(does not check for validity)
        // just if it is on the board
        bounds[0] = row-1;
        bounds[1] = col-1;
        return bounds;
    }

    // Given a [row, col] move, return true if a space is unclaimed.
    private static boolean checkValidMove(int[] move, char[][] state) {
        int row = move[0];
        int col = move[1];
        // was conditional statement, simplified to one return
        // true if space is unclaimed, false otherwise
        return state[row][col] == emptySpaceSymbol;
    }

    // Given a [row, col] move, the symbol to add, and a game state,
    // Return a NEW array with the new game state
    private static char[][] makeMove(int[] move, char symbol, char[][] currentState)
    {


        // new 2d char array to hold updated game
        char[][] updated = new char[currentState.length][currentState[0].length];

        // for loop to loop through array
        for (int i = 0; i < currentState.length; i++)
        {
            // setting new
            updated[i] = Arrays.copyOf(currentState[i], currentState[i].length);
        }

        // Update the symbol at user's desired location
        int row = move[0];
        int col = move[1];
        updated[row][col] = symbol;

        // Return the updated array
        return updated;
    }


    // Given a state, return true if some player has won in that state
    private static boolean checkWin(char[][] state) {
        // for loop to check horizontal rows
        for (int row = 0; row < 3; row++)
        {
            // conditional statement not equal to empty space and if the symbol in the i row and first column are the same
            // as well as if that element at i is equal to the element 2 over from itself
            if (state[row][0] != emptySpaceSymbol && state[row][0] == state[row][1] && state[row][0] == state[row][2])
            {
                return true;
            }
        }

        // for loop to check vertical columns: same logic as above
        for (int col = 0; col < 3; col++)
        {
            if (state[0][col] != emptySpaceSymbol && state[0][col] == state[1][col] && state[0][col] == state[2][col])
            {
                return true;
            }
        }

        // Check diagonals
        // was an if statement but compiler said it could be updated to just a return
        // similar logic to above
        return state[1][1] != emptySpaceSymbol && ((state[0][0] == state[1][1] && state[1][1] == state[2][2]) ||
                (state[0][2] == state[1][1] && state[1][1] == state[2][0]));
    }

    // Given a state, simply checks whether all spaces are occupied. Does not care or check if a player has won.
    private static boolean checkDraw(char[][] state) {
        // nested for loop to check elements of state(board)
        for (int i = 0; i < state.length; i++)
        {
            for (int j = 0; j < state[i].length; j++)
            {
                // conditional statement to check for a white space
                if (state[i][j] == emptySpaceSymbol)
                {
                    // if there is a white space it is not a draw
                    return false;
                }
            }
        }
        // return true if there are no white spaces found
        // it is a draw
        return true;
    }

    // Given a game state, return a new game state with move from the AI
    private static char[][] getCPUMove(char[][] gameState) {

        ArrayList<int[]> validMoves = getValidMoves(gameState);

        // If there is a winning move available, make that move
        // enhanced for loop used to iterate through validMoves array list
        // for each int array object in the list
        for (int[] move : validMoves)
        {
            // create a new version of the board with a move and player2 symbol
            char[][] newState = makeMove(move, playerTwoSymbol, gameState);
            // decision: if that new version of the board with that new move is
            // a winning board -- return the new board
            if (checkWin(newState))
            {
                return newState;
            }
        }

        // If not, check if opponent has a winning move, and if so, make a move there
        // same enhanced for loop
        for (int[] move : validMoves)
        {
            // as before new state of the board
            // this time with player one's symbol to check for a win condition
            char[][] newState = makeMove(move, playerOneSymbol, gameState);
            // if it's a win
            if (checkWin(newState))
            {
                // make that move with ("O") to block a win
                return makeMove(move, playerTwoSymbol, gameState);
            }
        }

        // If not, move on center space if possible
        // if conditional to check if current board's middle spot is empty
        if (gameState[1][1] == emptySpaceSymbol)
        {
            // move created to be middle indexes
            int[] move = {1,1};
            // return the move made which is a 2d char array
            return makeMove(move, playerTwoSymbol, gameState);
        }


        // If not, move on corner spaces if possible
        // create 2D int array with the 4 corner spaces as the elements
        int[][] cornerMoves = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        // loop through those elements(4 elements)
        for (int[] move : cornerMoves)
        {
            // if the current board at the location of the 2 elements in move
            // (the first({0,0} and then successive elements in corner moves)
            if (gameState[move[0]][move[1]] == emptySpaceSymbol)
            {
                // make the move to the first empty space encountered
                return makeMove(move, playerTwoSymbol, gameState);
            }
        }

        // Otherwise, move in any available spot
        // by creating an int[] move to send to the makeMove function
        // just get the first item in valid moves
        int[] move = validMoves.get(0);
        // return that move
        return makeMove(move, playerTwoSymbol, gameState);
    }


    // Given a game state, return an ArrayList of [row, column] positions that are unclaimed on the board
    private static ArrayList<int[]> getValidMoves(char[][] gameState) {

        ArrayList<int[]> validMoves = new ArrayList<>();

        // nested for loop to iterate through game state 2d array
        for (int row = 0; row < gameState.length; row++)
        {
            for (int col = 0; col < gameState[row].length; col++)
            {
                // checks whether the symbol in the current game state is blank or not
                if (gameState[row][col] == emptySpaceSymbol)
                {
                    // if it is, add it to the new array list
                    // a new int array 2 length created with values of row and col
                    validMoves.add(new int[]{row, col});
                }
            }
        }

        return validMoves;
    }

    // Given a game history, replay the game by displaying each state
    private static void runGameHistory(ArrayList<char[][]> gameHistory) {

        for (int i = 0; i < gameHistory.size(); i++) {
            char[][] state = gameHistory.get(i);

            // Display the current state of the game
            System.out.println(displayGameFromState(state));

            // Check if it's the last state in the game history
            if (i == gameHistory.size() - 1) {
                System.out.println("End of game history");
            } else {
                // Print a separator between game states
                System.out.println("---- Next Move ----");
            }
        }
    }

}