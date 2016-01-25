package connect4;



import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import connect4.gui.*;
import connect4.ai.*;

public class Connect4 extends JFrame{
    private static void compTurn(Player18 p, JButton [][] spot) {
        if (!p.isWinHum()) {
            int  col = p.chooseMove();
            for (int row = 0; row < 8; row ++) {
                if(spot[row][col].getBackground() != Color.red && spot[row][col].getBackground() != Color.blue) {
                    spot[row][col].setBackground(Color.blue);
                    spot[row][col].setOpaque(true);
                    spot[row][col].setBorderPainted(false);
                    if (p.board[row][col] == 0) {
                        //copied the sleep code from here 
                        //http://stackoverflow.com/questions/3342651/how-can-i-delay-a-java-program-for-a-few-seconds
                        try {
                            Thread.sleep(100);
                        } catch(InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        spot[row][col].setBackground(Color.lightGray);
                        spot[row][col].setOpaque(true);
                        spot[row][col].setBorderPainted(false);
                    }
                }
            }
            p1t =!p1t;
        }
    }
    
    
    private static void playerOne(Player18 p, JButton [][] spot, int col, JRadioButton pvp) {
        //Edit the board, put in -1 and turn it blue r
        if (!pvp.isSelected() || (pvp.isSelected() && p1t == true)){//p1t means player 1 turn
            p.board = p.makeMove(col, 1);
            //p1t = !p1t;
            for (int row = 0; row < 8; row ++) {
                if(spot[row][col].getBackground() != Color.red && spot[row][col].getBackground() != Color.blue) {
                    spot[row][col].setBackground(Color.red);
                    spot[row][col].setOpaque(true);
                    spot[row][col].setBorderPainted(false);
                    if (p.board[row][col] == 0) {
                        try {
                            Thread.sleep(100);
                        } catch(InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        spot[row][col].setBackground(Color.lightGray);
                        spot[row][col].setOpaque(true);
                        spot[row][col].setBorderPainted(false);
                    }
                }
            }
            p1t =!p1t;
        }
        else if (pvp.isSelected() && p1t == false) {
            p.board = p.makeMove(col, 10); 
            //p1t = !p1t;
            for (int row = 0; row < 8; row ++) {
                if(spot[row][col].getBackground() != Color.red && spot[row][col].getBackground() != Color.blue) {
                    spot[row][col].setBackground(Color.blue);
                    spot[row][col].setOpaque(true);
                    spot[row][col].setBorderPainted(false);
                    if (p.board[row][col] == 0) {
                        try {
                            Thread.sleep(100);
                        } catch(InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        spot[row][col].setBackground(Color.lightGray);
                        spot[row][col].setOpaque(true);
                        spot[row][col].setBorderPainted(false);
                    }
                }
            }
            p1t =!p1t;
        }
        if (!pvp.isSelected()){
            change(p, spot, pvp);
        }
        
    }
    
    
    /*sets the color of every button to gray and sets every index of the int board to 0*/
    private static void gray (JButton [][] button) {
        for (int row = 0; row < 8; row ++) {
            for (int col = 0; col < 8; col++) {
                //p.board[row][col] = 0;
                button[row][col].setBackground(Color.lightGray);
                button[row][col].setOpaque(true);
                button[row][col].setBorderPainted(false);
            }
        }
    }
    
    private static void change (Player18 p, JButton [][] spot, JRadioButton pvp) {
        if (!pvp.isSelected()) {
            compTurn(p, spot);
        }
    }
    
    private static boolean gameOver (Player18 p) {
        if (p.isWin() || p.isWinHum()) {
            return true;
        }
        
        boolean row = true;
        
        for (int col = 0; col < 8; col++) {
            if(p.board[0][col] == 0) {
                row = false;
            }
        }
        return row;
        
    }

    
    /*returns a 2d 8x8 array of JButtons*/
    private static JButton [][] createButtons(){
        JButton [][] buttons = new JButton [8][8];
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                 buttons[row][col] = new JButton();
            }
        }
        return buttons;
    }
    
    static JButton [][] buttons = createButtons();
    
    static JLabel title = new JLabel("Connect Four");
    static Font font1 = new Font("Times New Roman", Font.BOLD, 30);
    static JButton quit = new JButton("Quit");
    static JButton reset = new JButton("Reset");
    
    static JRadioButton pvp = new JRadioButton("2 Players");
    static JSlider diff = new JSlider (SwingConstants.HORIZONTAL, 1, 3, 2);
    static JLabel diffic = new JLabel ("Difficulty");
    
    static JEventQueue events = new JEventQueue();
    static JFrame frame = new JFrame ("Connect Four");
    
    static boolean p1t = true; //player 1 turn
    
    static JBox body =  
        JBox.vbox(JBox.vspace(10), 
                  JBox.hbox(JBox.hglue(), title, JBox.hglue()), 
                  JBox.vspace(10), 
                  JBox.hbox(JBox.hglue(), buttons[0][0], JBox.hspace(1), buttons[0][1], JBox.hspace(1), buttons[0][2], JBox.hspace(1), buttons[0][3], JBox.hspace(1), buttons[0][4], JBox.hspace(1), buttons[0][5], JBox.hspace(1), buttons[0][6], JBox.hspace(1), buttons[0][7], JBox.hglue()), 
                  JBox.vspace(1), 
                  JBox.hbox(JBox.hglue(), buttons[1][0], JBox.hspace(1), buttons[1][1], JBox.hspace(1), buttons[1][2], JBox.hspace(1), buttons[1][3], JBox.hspace(1), buttons[1][4], JBox.hspace(1), buttons[1][5], JBox.hspace(1), buttons[1][6], JBox.hspace(1), buttons[1][7], JBox.hglue()), 
                  JBox.vspace(1), 
                  JBox.hbox(JBox.hglue(), buttons[2][0], JBox.hspace(1), buttons[2][1], JBox.hspace(1), buttons[2][2], JBox.hspace(1), buttons[2][3], JBox.hspace(1), buttons[2][4], JBox.hspace(1), buttons[2][5], JBox.hspace(1), buttons[2][6], JBox.hspace(1), buttons[2][7], JBox.hglue()), 
                  JBox.vspace(1), 
                  JBox.hbox(JBox.hglue(), buttons[3][0], JBox.hspace(1), buttons[3][1], JBox.hspace(1), buttons[3][2], JBox.hspace(1), buttons[3][3], JBox.hspace(1), buttons[3][4], JBox.hspace(1), buttons[3][5], JBox.hspace(1), buttons[3][6], JBox.hspace(1), buttons[3][7], JBox.hglue()), 
                  JBox.vspace(1), 
                  JBox.hbox(JBox.hglue(), buttons[4][0], JBox.hspace(1), buttons[4][1], JBox.hspace(1), buttons[4][2], JBox.hspace(1), buttons[4][3], JBox.hspace(1), buttons[4][4], JBox.hspace(1), buttons[4][5], JBox.hspace(1), buttons[4][6], JBox.hspace(1), buttons[4][7], JBox.hglue()), 
                  JBox.vspace(1), 
                  JBox.hbox(JBox.hglue(), buttons[5][0], JBox.hspace(1), buttons[5][1], JBox.hspace(1), buttons[5][2], JBox.hspace(1), buttons[5][3], JBox.hspace(1), buttons[5][4], JBox.hspace(1), buttons[5][5], JBox.hspace(1), buttons[5][6], JBox.hspace(1), buttons[5][7], JBox.hglue()), 
                  JBox.vspace(1), 
                  JBox.hbox(JBox.hglue(), buttons[6][0], JBox.hspace(1), buttons[6][1], JBox.hspace(1), buttons[6][2], JBox.hspace(1), buttons[6][3], JBox.hspace(1), buttons[6][4], JBox.hspace(1), buttons[6][5], JBox.hspace(1), buttons[6][6], JBox.hspace(1), buttons[6][7], JBox.hglue()), 
                  JBox.vspace(1), 
                  JBox.hbox(JBox.hglue(), buttons[7][0], JBox.hspace(1), buttons[7][1], JBox.hspace(1), buttons[7][2], JBox.hspace(1), buttons[7][3], JBox.hspace(1), buttons[7][4], JBox.hspace(1), buttons[7][5], JBox.hspace(1), buttons[7][6], JBox.hspace(1), buttons[7][7], JBox.hglue()), 
                  JBox.vspace(30), 
                  JBox.vspace(30), 
                  JBox.hbox(JBox.hglue(), quit, JBox.hspace(70), reset, JBox.hspace(100), pvp, JBox.hglue()), 
                  JBox.vspace(5), 
                  JBox.hbox(JBox.hglue(), diffic, JBox.hglue()), 
                  JBox.vspace(5), 
                  JBox.hbox(diff), 
                  JBox.vglue()); 
    
    /*sets the size of every button in the array*/
    private static void setSize(JButton [][] buttons){
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                JBox.setSize(buttons[row][col], 70, 70);
            }
        }
    }
    
    /*sets the size of every button in the array*/
    private static void listenTo(){
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                events.listenTo(buttons[row][col], Integer.toString(row) + Integer.toString(col));
            }
        }
        
        events.listenTo(quit, "Quit");
        events.listenTo(reset, "Reset");
      //  events.listenTo(pvp,);
    }
    
    private static void ready(Player18 p){
        setSize(buttons);
        gray(buttons);
        p.reset();
        listenTo();
        
        title.setFont(font1);
        
        diff.setMajorTickSpacing(1);
        diff.setPaintTicks(true);
        diff.setPaintLabels(true);
        
        frame.setSize(900,850);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private static void runGame(){
        Player18 p = new Player18();
        ready(p);
        frame.add(body);
        frame.setVisible(true);
        
        p1t = true; //player 1 turn
        
        while(true){
            //human is red comp is blue 
            if(p.isWin()) {
                buttons[p.compWin.r1][p.compWin.c1].setBackground(Color.cyan);
                buttons[p.compWin.r1][p.compWin.c1].setOpaque(true);
                buttons[p.compWin.r1][p.compWin.c1].setBorderPainted(false);
                buttons[p.compWin.r2][p.compWin.c2].setBackground(Color.cyan);
                buttons[p.compWin.r2][p.compWin.c2].setOpaque(true);
                buttons[p.compWin.r2][p.compWin.c2].setBorderPainted(false);
                buttons[p.compWin.r3][p.compWin.c3].setBackground(Color.cyan);
                buttons[p.compWin.r3][p.compWin.c3].setOpaque(true);
                buttons[p.compWin.r3][p.compWin.c3].setBorderPainted(false);
                buttons[p.compWin.r4][p.compWin.c4].setBackground(Color.cyan);
                buttons[p.compWin.r4][p.compWin.c4].setOpaque(true);
                buttons[p.compWin.r4][p.compWin.c4].setBorderPainted(false);
            }
            else if (p.isWinHum()) {
                buttons[p.humWin.r1][p.humWin.c1].setBackground(Color.magenta);
                buttons[p.humWin.r1][p.humWin.c1].setOpaque(true);
                buttons[p.humWin.r1][p.humWin.c1].setBorderPainted(false);
                buttons[p.humWin.r2][p.humWin.c2].setBackground(Color.magenta);
                buttons[p.humWin.r2][p.humWin.c2].setOpaque(true);
                buttons[p.humWin.r2][p.humWin.c2].setBorderPainted(false);
                buttons[p.humWin.r3][p.humWin.c3].setBackground(Color.magenta);
                buttons[p.humWin.r3][p.humWin.c3].setOpaque(true);
                buttons[p.humWin.r3][p.humWin.c3].setBorderPainted(false);
                buttons[p.humWin.r4][p.humWin.c4].setBackground(Color.magenta);
                buttons[p.humWin.r4][p.humWin.c4].setOpaque(true);
                buttons[p.humWin.r4][p.humWin.c4].setBorderPainted(false);
            }
            
            if (diff.getValue() == 1) {
                p.maxDepth = 2;
            }
            else if (diff.getValue() == 2) {
                p.maxDepth = 4;
            }
            else if (diff.getValue() == 3) {
                p.maxDepth  = 6;
            }
            
            
            
            EventObject event = events.waitEvent();
            String name = events.getName(event);
            
            if (name.equals("Quit")) {
                frame.dispose();
            }
            
            if (name.equals("Reset")) {
                p.reset();
                gray(buttons);
            }
            
            
            if (!gameOver(p)) {
                
                
                if ((name.equals("00")) || (name.equals("10"))|| (name.equals("20")) || (name.equals("30"))
                        || (name.equals("40")) || (name.equals("50")) || (name.equals("60") || (name.equals("70")))) {  
                    int col = 0;
                    if (p.board[0][col] == 0){
                        playerOne(p, buttons, col, pvp);
                        //p1t =!p1t;
                    }
                }
                
                else if ((name.equals("01")) || (name.equals("11"))|| (name.equals("21")) || (name.equals("31")) 
                             || (name.equals("41")) || (name.equals("51")) || (name.equals("61")) || (name.equals("71"))) {
                    int col = 1;
                    if (p.board[0][col] == 0){
                        playerOne(p, buttons, col, pvp);
                        //p1t =!p1t;
                    }
                }
                
                else if ((name.equals("02")) || (name.equals("12"))|| (name.equals("22")) || (name.equals("32")) || (name.equals("42")) || (name.equals("52")) || (name.equals("62")) || (name.equals("72"))) {
                    int col = 2;
                    if (p.board[0][col] == 0){
                        playerOne(p, buttons, col, pvp);
                        //p1t =!p1t;
                    } 
                }
                
                else if ((name.equals("03")) || (name.equals("13"))|| (name.equals("23")) || (name.equals("33")) || (name.equals("43")) || (name.equals("53")) || (name.equals("63")) || (name.equals("73"))) {
                    int col = 3;
                    if (p.board[0][col] == 0){
                        playerOne(p, buttons, col, pvp);
                        //p1t =!p1t;
                    }  
                }
                
                else if ((name.equals("04")) || (name.equals("14"))|| (name.equals("24")) || (name.equals("34")) || (name.equals("44")) || (name.equals("54")) || (name.equals("64")) || (name.equals("74"))) {
                    int col = 4;
                    if (p.board[0][col] == 0){
                        playerOne(p, buttons, col, pvp);
                        //p1t =!p1t;
                    }
                }
                
                else if ((name.equals("05")) || (name.equals("15"))|| (name.equals("25")) || (name.equals("35"))
                             || (name.equals("45")) || (name.equals("55")) || (name.equals("65")) || (name.equals("75"))) {
                    int col = 5;
                    if (p.board[0][col] == 0){
                        playerOne(p, buttons, col, pvp);
                        //p1t =!p1t;
                    }  
                }
                
                else if ((name.equals("06")) || (name.equals("16"))|| (name.equals("26")) || (name.equals("36")) || (name.equals("46")) || (name.equals("56")) || (name.equals("66")) || (name.equals("76"))) {
                    int col = 6;
                    if (p.board[0][col] == 0){
                        playerOne(p, buttons, col, pvp);
                        //p1t =!p1t;
                    }
                }
                
                else if ( (name.equals("07")) || (name.equals("17"))|| (name.equals("27")) || (name.equals("37")) || (name.equals("47")) || (name.equals("57")) || (name.equals("67")) || (name.equals("77"))) {
                    int col = 7;
                    if (p.board[0][col] == 0){
                        playerOne(p, buttons, col, pvp);
                        //p1t =!p1t;
                    }
                }
            }
        }
    }
    
    /*prints the code for the JBox body*/
    public static void printBody(){
        System.out.println("JBox body = ");
        System.out.println("JBox.vbox(JBox.vspace(10),");
        System.out.println("JBox.hbox(JBox.hglue(), title, JBox.hglue()),");
        System.out.println("JBox.vspace(10),");
        
        for(int row = 0; row < 8; row++){
            System.out.print("JBox.hbox(JBox.hglue(), ");
            for (int col = 0; col < 8; col++){
                System.out.print("buttons[" + row + "]" + "[" + col + "], ");
                if (col != 7){
                    System.out.print("JBox.hspace(1), ");
                }
                else {
                    System.out.println("JBox.hglue()),");
                }
            }
            if (row != 7){
            System.out.println("JBox.vspace(1),");
            }
            else {
                System.out.println("JBox.vspace(30),");
            }
        }
        
        System.out.println("JBox.vspace(30),");
        System.out.println("JBox.hbox(JBox.hglue(), Quit, JBox.hspace(70), Reset, JBox.hspace(100), pvp, JBox.hglue()),");
        System.out.println("JBox.vspace(5),");
        System.out.println("JBox.hbox(JBox.hglue(), diffic, JBox.hglue()),");
        System.out.println("JBox.vspace(5),");
        System.out.println("JBox.hbox(diff),");
        System.out.println("JBox.vglue());");
    }
    
    public static void main (String []args) {
        runGame();
    }   
}