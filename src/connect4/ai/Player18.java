/*Matthew Kennedy
 * mkennedymsm at gmail dot com
 * mkennedm at bu dot edu
 * This is the class for the artificial intelligence in Connect 4.
 * The code for the graphics can be found in Connect4.java
 * It looks at possible moves and generates a tree that will be used to decide the best move to make.
 * */
package connect4.ai;
import java.util.Random;

//the human is 1 the computer is 10
public class Player18 implements PlayerClass {
    public int [] [] board = new int [8][8];
    private Random r = new Random();
    private int leaf = 0;
    private int mmf = 1;
    private int um = 0;
    private int t = 1;
    public int maxDepth = 6;
    private int moveChose = 0;
    public Win compWin = new Win();
    public Win humWin = new Win();
    
    
    public int move(int [][] board){
        int a  = chooseMove();
        return a;
    }
    
    
    
    public int eval() {
        
        if (isWin()) {
            return Integer.MAX_VALUE;
        }
        else if (isWinHum()){
            return -10000;
        }
        
        return row3s() + row2s() + column3s() + column2s() + 
            diagOne3s() + diagOne2s() + diagTwo3s() + diagTwo2s();
    }
    

    //computer wins
    public boolean isWin () {
        if (rowWin()) {
            return true;
        }
        else if (columnWin()) {
            return true;
        }
        else if (diagOneWin()) {
            return true;
        }
        else if (diagWin2()) {
            return true;
        } 
        
        return false;
    }
    
    //human wins
    public boolean isWinHum () {
        if (rowWinHum()) {
            return true;
        }
        else if (columnWinHum()) {
            return true;
        }
        else if (diagOneWinHum()) {
            return true;
        }
        else if (diagWin2Hum()) {
            return true;
        } 
        
        return false;
    }
    
    private boolean rowWin () {
        
        for (int row = 7; row > -1; row--) {
            for (int col = 0; col < 5; col++) {
                if (board[row][col] == 10 &&
                    board[row][col] == board[row][col + 1] &&
                    board[row][col] == board[row][col + 2] &&
                    board[row][col] == board[row][col + 3]) {
                    compWin.r1 = row;
                    compWin.r2 = row;
                    compWin.r3 = row;
                    compWin.r4 = row;
                    compWin.c1 = col;
                    compWin.c2 = col + 1;
                    compWin.c3 = col + 2;
                    compWin.c4 = col + 3;
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean rowWinHum () {
        
        for (int row = 7; row > -1; row--) {
            for (int col = 0; col < 5; col++) {
                if (board[row][col] == 1 &&
                    board[row][col] == board[row][col + 1] &&
                    board[row][col] == board[row][col + 2] &&
                    board[row][col] == board[row][col + 3]) {
                    humWin.r1 = row;
                    humWin.r2 = row;
                    humWin.r3 = row;
                    humWin.r4 = row;
                    humWin.c1 = col;
                    humWin.c2 = col + 1;
                    humWin.c3 = col + 2;
                    humWin.c4 = col + 3;
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private int row3s () {
        //finds places where there are 3 pieces of the same color in a row
        int sum = 0;
        
        for (int row = 7; row > -1; row--) {
            for (int col = 0; col < 6; col++) {
                if (col < 5 && col > 0) {
                    if (board[row][col] == board[row][col + 1] &&
                        board[row][col] == board[row][col + 2] &&
                        (board[row][col + 3] == 0 ||
                         board[row][col - 1] == 0)){
                        if (board[row][col] == 10) {
                            sum = sum + 10;
                        }
                        if (board[row][col] == 1) {
                            sum = sum - 11;
                        }
                    }
                }
                else if (col == 6) {
                    if (board[row][col] == board[row][col + 1] &&
                        board[row][col] == board[row][col + 2] &&
                        board[row][col - 1] == 0){
                        if (board[row][col] == 10) {
                            sum = sum + 10;
                        }
                        if (board[row][col] == 1) {
                            sum = sum - 11;
                        }
                    }  
                }
                else if (col == 0) {
                    if (board[row][col] == board[row][col + 1] &&
                        board[row][col] == board[row][col + 2] &&
                        board[row][col + 3] == 0){
                        if (board[row][col] == 10) {
                            sum = sum + 10;
                        }
                        if (board[row][col] == 1) {
                            sum = sum - 11;
                        }
                    }  
                }
            }
        }
        return sum;
    }
    
    private int row2s () {
        //finds places where there are 2 pieces of the same color in a row
        //does not include 2s that are part of 3s
        int sum = 0;
        
        for (int row = 7; row > -1; row--) {
            for (int col = 0; col < 5; col++) {
                if(board[row][col] == board[row][col + 1] &&
                   board[row][col + 2] == 0 && 
                   (Math.abs(board[row][col] - board[row][col + 3]) != 9)) {
                    if(board[row][col] == 10) {
                        sum = sum + 3;
                    }
                    else if (board[row][col] == 1) {
                        sum = sum - 4;
                    }
                }
            }
            
            if (board[row][4] == 0 && board[row][7] != board[row][5]) {//the special case at 5
                if(board[row][5] == 10) {
                    sum = sum + 3;
                }
                else if (board[row][5] == 1) {
                    sum = sum - 4;
                }
            }
            
            if (board[row][6] == board[row][7] &&
                board[row][5] == 0 &&
                (Math.abs(board[row][4] - board[row][6]) != 9)) {
                if (board[row][6] == 10) {
                    sum = sum + 3;
                }
                else if (board[row][6] == 1) {
                    sum = sum - 4;
                }
            }
        }
        
        return sum;
    }
    
    private boolean columnWin() {
        
        for (int row = 7; row > 2; row--) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == 10 &&
                    board[row][col] == board[row - 1][col] &&
                    board[row][col] == board[row - 2][col] &&
                    board[row][col] == board[row - 3][col]) {
                    compWin.r1 = row;
                    compWin.r2 = row - 1;
                    compWin.r3 = row - 2;
                    compWin.r4 = row - 3;
                    compWin.c1 = col;
                    compWin.c2 = col;
                    compWin.c3 = col;
                    compWin.c4 = col;
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean columnWinHum() {
        
        for (int row = 7; row > 2; row--) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == 1 &&
                    board[row][col] == board[row - 1][col] &&
                    board[row][col] == board[row - 2][col] &&
                    board[row][col] == board[row - 3][col]) {
                    humWin.r1 = row;
                    humWin.r2 = row - 1;
                    humWin.r3 = row - 2;
                    humWin.r4 = row - 3;
                    humWin.c1 = col;
                    humWin.c2 = col;
                    humWin.c3 = col;
                    humWin.c4 = col;
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private int column3s () {
        //finds columns of 3 of the same piece
        int sum = 0;
        
        for (int row = 5; row > 0; row--) {
            for (int col = 0; col < 8; col++) {
                if(board[row][col] == board[row + 1][col] &&
                   board[row][col] == board[row + 2][col] &&
                   board[row - 1][col] == 0) {
                    if(board[row][col] == 10) {
                        sum = sum + 10;
                    }
                    else if(board[row][col] == 1) {
                        sum = sum - 11;
                    }
                }
                
            }
        }
        return sum;
    }
    
    private int column2s () {
        //finds columns of two of the same piecees. excludes 2s that are part of 3s
        int sum = 0;
        
        for (int row = 6; row > 1; row--) {
            for (int col = 0; col < 8; col++) {
                if (row < 6) {
                    if(board[row][col] == board[row + 1][col] &&
                       board[row - 1][col] == 0 && 
                       board[row + 2][col] != board[row][col])// special case at 6
                    {
                        if(board[row][col] == 10) {
                            sum = sum + 3;
                        }
                        else if(board[row][col] == 1) {
                            sum = sum - 4;
                        }
                    }
                }
                else if (row == 6) {
                    if(board[row][col] == board[row + 1][col] &&
                       board[row - 1][col] == 0)
                    {
                        if(board[row][col] == 10) {
                            sum = sum + 3;
                        }
                        else if(board[row][col] == 1) {
                            sum = sum - 4;
                        }
                    }
                }
            }
        }
        return sum;
    }
    
    //checks diagonal / fours
    private boolean diagOneBool (int row, int col) {
        if (board[row][col] == board[row - 1][col + 1] &&
            board[row][col] == board[row - 2][col + 2] &&
            board[row][col] == board[row - 3][col + 3]){
            return true;
        }
        return false;
    }
    
    //looks for diagonal wins like this /
    private boolean diagOneWin () {
        for (int row = 7; row > 2; row--) {
            for (int col = 0; col < 5; col++) {
                if (diagOneBool(row, col) && board[row][col] == 10) {
                    compWin.r1 = row;
                    compWin.r2 = row - 1;
                    compWin.r3 = row - 2;
                    compWin.r4 = row - 3;
                    compWin.c1 = col;
                    compWin.c2 = col + 1; 
                    compWin.c3 = col + 2;
                    compWin.c4 = col + 3;
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean diagOneWinHum () {
        for (int row = 7; row > 2; row--) {
            for (int col = 0; col < 5; col++) {
                if (diagOneBool(row, col) && board[row][col] == 1) {
                    humWin.r1 = row;
                    humWin.r2 = row - 1;
                    humWin.r3 = row - 2;
                    humWin.r4 = row - 3;
                    humWin.c1 = col;
                    humWin.c2 = col + 1; 
                    humWin.c3 = col + 2;
                    humWin.c4 = col + 3;
                    return true;
                }
            }
        }
        return false;
    }
    
    
    
    private int diagOne3s() {
        //adds 11 for every diag 3 / found that is capable of becoming a win
        int sum = 0;
        
        for (int row = 7; row > 2; row --) {
            for (int col = 0; col < 5; col++) {
                if  (row < 7 && row > 2 && col < 5 && col > 0) {
                    if (diag1GoodBool(row, col)) {
                        if (board[row][col] == 10) {
                            sum = sum + 11;
                        }
                        else if (board[row][col] == 1) {
                            sum = sum - 12;
                        }
                    }
                }
                else if (col == 0 || row == 7) {
                    if (row + col != 2 && row + col != 12) {
                        if (diag1ForBool(row, col)) {
                            if (board[row][col] == 10) {
                                sum = sum + 11;
                            }
                            else if (board[row][col] == 1) {
                                sum = sum - 12;
                            }
                        }
                    }
                }
                else if (row == 2 || col == 5) {
                    if (row + col != 2 && row + col != 12) {
                        if (diag1BackBool(row, col)) {
                            if (board[row][col] == 10) {
                                sum = sum + 11;
                            }
                            else if (board[row][col] == 1) {
                                sum = sum - 12;
                            }
                        }
                    }
                }
            }
        }
        
        return sum;
    }
    
    //checks for 3 diag /, looks one forward and one back to make sure 3 can become 4
    private boolean diag1GoodBool(int row, int col) {
        if(board[row][col] == board[row - 1][col + 1] &&
           board[row][col] == board[row - 2][col + 2] &&
           (board[row - 3][col + 3] == 0 || board[row + 1][col - 1] == 0)) {
            return true;
        }
        return false;
    }
    
    //checks for 3 diag / that start at the bottom row. only looks forward past the three
    private boolean diag1ForBool (int row, int col) {
        if (row > 7 || row < 3 || col < 0 || col > 4) {
            return false;
        }
        else if(board[row][col] == board[row - 1][col + 1] &&
                board[row][col] == board[row - 2][col + 2] &&
                board[row - 3][col + 3] == 0) {
            return true;
        }
        return false;
    }
    
    //checks for 3 diag / that start at the bottom row. only looks forward past the three
    private boolean diag1BackBool (int row, int col) {
        if (row > 6 || row < 2 || col > 5 || col < 1) {
            return false;
        }
        else if(board[row][col] == board[row - 1][col + 1] &&
                board[row][col] == board[row - 2][col + 2] &&
                board[row + 1][col - 1] == 0) {
            return true;
        }
        return false;
    }
    
    //adds 3 for every diagonal / 2 found
    private int diagOne2s() {
        int sum = 0;
        
        for (int row = 7; row > 0; row--) {
            for (int col = 0; col < 7; col++) {
                if (diagOneLeft(row, col) || diagOneMid(row, col) || diagOneRight(row, col)) {
                    if (board[row][col] == 10) {
                        sum = sum + 3;
                    }
                    else if (board[row][col] == 1) {
                        sum = sum - 4;
                    }
                }
            }
        }
        
        return sum;
    }
    
    //checks for diagonal / 2s from the bottom left
    private boolean diagOneLeft(int row, int col) {
        if (row < 3 || row > 7 || col > 4 || col < 0) {
            return false;
        }
        else if (board[row][col] == board[row - 1][col + 1] &&
                 board[row - 2][col + 2] == 0 &&
                 (Math.abs(board[row - 3][col + 3] - board[row][col]) != 9)) {
            return true;
        }
        return false;
    }
    
    //checks for diagonal / 2s from the middle
    private boolean diagOneMid(int row, int col) {
        if (row > 6 || row < 2 || col < 1 || col > 5) {
            return false;
        }
        else if (board[row][col] == board[row - 1][col + 1] &&
                 board[row - 2][col + 2] == 0 && board[row + 1][col - 1] == 0) {  
            return true;
        }
        return false;
    }
    
    //checks for diagonal / 2s from the right
    private boolean diagOneRight(int row, int col) {
        if (row < 1 || row > 5 || col < 2 || col > 6) {
            return false;
        }
        else if (board[row][col] == board[row - 1][col + 1] &&
                 board[row + 1][col -1] == 0 && 
                 (Math.abs(board[row + 2][col - 2] - board[row][col]) != 9) &&
                 !diag1BackBool(row, col) && !diag1ForBool(row, col)) {
            return true;
        }
        return false;
    }
    
    //checks for this \ kind of diagonal win
    private boolean diagWin2 () {
        for (int row = 7; row > 2; row--) {
            for (int col = 3; col < 8; col++) {
                if (diagTwo4(row, col) && board[row][col] == 10) {
                    compWin.r1 = row;
                    compWin.r2 = row - 1;
                    compWin.r3 = row - 2;
                    compWin.r4 = row - 3;
                    compWin.c1 = col;
                    compWin.c2 = col - 1;
                    compWin.c3 = col - 2;
                    compWin.c4 = col - 3;
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean diagWin2Hum () {
        for (int row = 7; row > 2; row--) {
            for (int col = 3; col < 8; col++) {
                if (diagTwo4(row, col) && board[row][col] == 1) {
                    humWin.r1 = row;
                    humWin.r2 = row - 1;
                    humWin.r3 = row - 2;
                    humWin.r4 = row - 3;
                    humWin.c1 = col;
                    humWin.c2 = col - 1;
                    humWin.c3 = col - 2;
                    humWin.c4 = col - 3;
                    return true;
                }
            }
        }
        return false;
    }
    
    //helper method for diagWin2
    private boolean diagTwo4 (int row, int col) {
        if (board[row][col] == board[row - 1][col - 1] &&
            board[row][col] == board[row - 2][col - 2] &&
            board[row][col] == board[row - 3][col - 3]) {
            return true;
        }
        return false;
    }
    
    //adds 10 for every \ 3 found
    private int diagTwo3s() {
        int sum = 0;
        
        for (int row = 7; row > 1; row--) {
            for (int col = 2; col < 8; col++) {
                if (diagTwoUp(row, col) || diagTwoDown(row, col)) {
                    if (board[row][col] == 10) {
                        sum = sum + 11;
                    }
                    else if (board[row][col] == 1) {
                        sum = sum - 12;
                    }
                }
            }
        }
        
        return sum;
    }
    
    //looks one forward in a diagonal \ 3
    private boolean diagTwoUp(int row, int col) {
        if (row < 3 || col < 3 || row > 7 || col > 7) {
            return false;
        }
        else if (board[row][col] == board[row - 1][col - 1] &&
                 board[row][col] == board[row - 2][col - 2] &&
                 board[row - 3][col - 3] == 0) {
            return true;   
        }
        return false;
    }
    
    //looks one back in a diagonal \ 3
    private boolean diagTwoDown(int row, int col) {
        if (row > 6 || row < 2 || col  > 6 ||col < 2) {
            return false;
        }
        else if (board[row][col] == board[row - 1][col - 1] &&
                 board[row][col] == board[row - 2][col - 2] &&
                 board[row + 1][col + 1] == 0) {
            return true;   
        }
        return false;
    }
    
    //counts all the worthy diagonal \ 2s
    private int diagTwo2s() {
        int sum = 0;
        
        for (int row = 7; row > 0; row--) {
            for (int col = 1; col < 8; col++) {
                if (diagTwoLeft(row, col) || 
                    diagTwoMid(row, col) || 
                    diagTwoRight(row, col)) {
                    if (board[row][col] == 10) {
                        sum = sum + 3;
                    }
                    else if (board[row][col] == 1) {
                        sum = sum - 4;
                    }
                }
            }
        }
        
        return sum;
    }
    
    //returns true if the diagonalTwo 2 starts at the left and can turn into a 4
    private boolean diagTwoLeft(int row, int col) {
        if (row > 5 || row < 1 || col < 1 || col > 5) {
            return false;
        }
        else if (board[row][col] == board[row - 1][col -1] &&
                 board[row + 1][col + 1] == 0 && 
                 (Math.abs(board[row + 2][col + 2] - board[row][col]) != 9)) {
            return true;
        }
        return false;
    }
    
    //returns true if the diagonalTwo 2 starts at the first piece from the right  
    private boolean diagTwoMid(int row, int col) {
        if (row > 6 || row < 2 || col < 2 || col  > 6) {
            return false;
        }
        else if (board[row][col] == board[row - 1][col - 1] &&
                 board[row + 1][col + 1] == 0 && board[row - 1][col - 2] == 0){
            return true;
        }
        return false;
    }
    
    //returns true if the diagonalTwo 2 starts at the right
    private boolean diagTwoRight(int row, int col) {
        if (row < 3 || col < 3) {
            return false;
        }
        else if (board[row][col] == board[row - 1][col - 1] &&
                 board[row - 2][col - 2] == 0 && 
                 (Math.abs(board[row - 3][col - 3] - board[row][col]) != 9) &&
                 !diagTwoDown(row + 1, col + 1) && !diagTwoUp(row + 1, col + 1)) {
            return true;
        }
        return false;   
    }
    
    public void printBoard() { // shows the current state of the board
        for(int i = 0; i < board[0].length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if (j == board[0].length - 1) {
                    System.out.print(board[i][j]);
                }
                else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    
    public int chooseMove() {//finds the best move for the computer to make and adds it to the board once found
        int max = Integer.MIN_VALUE; 
        int best = -1;
        if (boardEmpty()) {
            best = 4;
        }
        else {
            for(int i = 0; i < 8; i++) { 
                if (board[0][i] == 0 && close(i)) {
                    board = makeMove(i, 10);
                    int val = minMax(1, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    t++;
                    board = undoMove(i);
                    if(val > max) {
                        best = i;
                        max = val;
                    }
                    if (val == Integer.MAX_VALUE) {
                        i = 9;
                    }
                }
            }
        }
        board = makeMove(best, 10);
        //printBoard();
       // System.out.println(best + " board evaluates to: " + eval() + " Leaves evaluated: " + leaf);
        um = 0;
        leaf = 0;
        
        return best;
    } 
    
    public int [][] makeMove(int column, int turn) {//makes a move on the  board once the best one has been found
        
        for(int i = 7; i > -1; i--) {
            if(board[i][column] == 0) {
                if(turn == 10) {
                    board[i][column] = 10;
                    i = -2;
                    
                }
                else if (turn == 1) {
                    board[i][column] = 1;
                    i = -2;
                }
                
            }
        }
        return board;
    }
    
    
    private int [][] undoMove(int column) {
        /*needed to undo the moves after they are tried and evaluated*/
        um++;
        
        for (int row = 0; row < 8; row++) {
            
            if (board[row][column] != 0) {
                board[row][column] = 0;
                row = 9;
            }
        }
        return board;
    }
    
    private int dumbEval(boolean compTurn) {
        /*a method i needed that returns numbers until a proper eval method is finished
         * will get rid of this later*/
        if(compTurn) {
            return -r.nextInt();
        }
        return r.nextInt();
    }
    
    public int minMax(int currDepth, int alpha, int beta) {
        //creates a tree through recursive calls and eventually tries every possible move
        if (boardFull() || isWin() || isWinHum()) {
            currDepth = maxDepth;
        }
        if (currDepth == maxDepth) {
            leaf++;
            
            int move = eval();
            return move;
        }
        else if (currDepth % 2 == 0) {
            //compTurn
            int val = Integer.MIN_VALUE;
            for (int i = 0; i < 8; i++) {
                if (board[0][i] == 0) {
                    alpha = max(alpha, val);
                    if (beta >= alpha){
                        board = makeMove(i, 10);
                        val = max(val, minMax(currDepth + 1, alpha, beta));
                        board = undoMove(i);
                    }
                    else {
                        i = 9;
                    }
                }
            }
            return val;
        }
        else  {
            //human turn
            int val = Integer.MAX_VALUE;
            for (int i = 0; i < 8; i++) {
                if (board[0][i] == 0) {
                    beta = min(beta, val);
                    if (beta >= alpha) {
                        board = makeMove(i, 1);
                        val = min(val, minMax( currDepth + 1, alpha, beta));
                        board = undoMove(i);
                    }
                    else {
                        i = 9;
                    }
                }
            }
            return val;
        }
        
    }
    
    public int max (int val, int minMax) {
        //compares two integers and returns the greater one
        if (minMax > val) { 
            return minMax;
        }
        return val;
    }
    
    public int min (int val, int minMax) {
        //compares two integers and returns the smaller one
        if (minMax < val) { 
            return minMax;
        }
        return val;
    }
    
    private boolean boardEmpty() {
        for (int i = 0; i < 8; i++) {
            if(board[7][i] != 0) {
                return false;
            }
        }
        return true;
    }
    
    //i need this to only look at spots that are close to other pieces
    private boolean close (int col) {
        if (board[7][col] == 0) {
            if (col == 0) {
                if (board[7][1] == 0) {
                return false;
                }
            }
            else if (col == 7) {
                if (board[7][6] == 0) {
                return false;
                }
            }
            else if (board[7][col + 1] == 0 && board[7][col - 1] == 0) {
                return false;
            }
            return true;
        }
        return true;
    }
    
    private boolean bottomFull() {
        for (int col = 0; col < 8; col++) {
            if (board[7][col] == 0) {
                return false;
            }
        }
        return true;
    }

    public int findTurn(int col) {
        int turn = 0;
        for (int row = 0; row < 8; row++) {
            if (board[row][col] != 0) {
                turn = board[row][col];
                row = 9;
            }
        }
        return turn;
    }
    
    //a method that populates the board with row 2s.  used this for testing
    private void popR2 () {
        for (int row = 7; row > -1; row--) {
            for (int col = 0; col < 8; col++) {
                if (col != 2 && col != 5) {
                    board[row][col] = 10;
                }
            }
        }
    }
    
    //a method that populates the board comletely with 1s
    private void populateBoard () {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = 10;
            }
        }
    }
    
    private int findDouble () {
        for (int row = 7; row > 2; row--) {
            board[row][4] = 0;
            if (diagOne3s() == 121 || diagOne3s() == 132) {
                board[row][4] = 10;
            }
            else {
                return row;
            }
        }
        return 0;
    }
    
    //a method i used to test. turns a given column into all zeros
    private void emptyCol(int col) {
        for (int row = 0; row < 8; row++) {
            board[row][col] = 0;
        }
    }
    
    //a method used to test.  turns a given row into all zeros
    private void emptyRow(int row) {
        for (int col = 0; col < 8; col++) {
            board[row][col] = 0;
        }
    }
    //used this to test out my new and improved diag one win method
    private int d1check () {
        int sum = 0;
        
        for (int row = 7; row > 2; row--) {
            for (int col = 0; col < 5; col++) {
                if (diagOneBool(row, col)) {
                    sum = sum + 1;
                }
            }
        }
        
        return sum;
    }
    
    private boolean boardFull () {
        for (int col = 0; col < 8; col ++) {
            if (board[0][col] == 0) {
                return false;
            }
        }
        //System.out.println("Board was full");
        return true;
    }
    
    private void empty (int row, int col) {
        board[row][col] = 0;
    }
    
    private void fillCol (int col) {
        for (int row = 0; row < 8; row++) {
            board[row][col] = 10;
        }
    }
    
    private boolean almost() {
        for (int col = 0; col < 8; col++) {
            if (board[0][col] != 0) {
                return true;   
            }
        }
        return false;
    }
    
    public void reset(){
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[0].length; col++){
                board[row][col] = 0;
            }
        }
    }
    
    public static void main (String []args) {
        
        Player18 p = new Player18();
        
    }
    
    //contains the coordinates for winning pieces
    public class Win {
        public int r1;
        public int r2;
        public int r3;
        public int r4;
        public int c1;
        public int c2;
        public int c3;
        public int c4;
        
        public Win (int r1, int c1, int r2, int c2, int r3, int c3, int r4, int c4) {
            this.r1 = r1;
            this.r2 = r2;
            this.r3 = r3;
            this.r4 = r4;
            this.c1 = c1; 
            this.c2 = c2;
            this.c3 = c3;
            this.c4 = c4;
        }
        
        public Win (){
            this.r1 = 8;
            this.r2 = 8;
            this.r3 = 8;
            this.r4 = 8;
            this.c1 = 8; 
            this.c2 = 8;
            this.c3 = 8;
            this.c4 = 8;
        }
    }
    
}
