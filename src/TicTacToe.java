import java.util.Scanner;
public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static final String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {
        clearBoard();
        char currentPlayer = 'X';
        int moveCounter = 0;
        boolean gameWon = false;
        boolean gameTie = false;

        while (!gameWon && !gameTie) {
            int row = getPlayerMove("row") - 1;
            int col = getPlayerMove("col") - 1;

            while (!isValidMove(row, col)) {
                row = getPlayerMove("row") - 1;
                col = getPlayerMove("col") - 1;
            }

            board[row][col] = String.valueOf(currentPlayer);
            moveCounter++;
            display();

            if (moveCounter >= 5) {
                gameWon = isWin(currentPlayer);
                if (!gameWon && moveCounter == 9) {
                    gameTie = isTie();
                }
            }

            if (gameWon) {
                System.out.println(currentPlayer + " wins!");
            }
            else if (gameTie) {
                System.out.println("It's a tie!");
            }
            else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("  1   2  3");
        for (int row = 0; row < ROWS; row++) {
            System.out.print((row + 1) + " ");
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col]);
                if (col < COLS - 1) System.out.print(" | ");
            }
            System.out.println();
            if (row < ROWS - 1) System.out.println("  ---------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            return false;
        }
        return board[row][col].equals(" ");
    }

    private static boolean isWin(char player) {
        String playerStr = String.valueOf(player);
        return isRowWin(playerStr) || isColWin(playerStr) || isDiagonalWin(playerStr);
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        return !isWin('X') && !isWin('O');
    }

    private static int getPlayerMove(String coordinate) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter " + coordinate + " (1-3): ");
        return scanner.nextInt();
    }
}