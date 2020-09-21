//package FileHandling;
import java.util.Scanner;
import java.lang.Math;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
public class tictactoe
{
    public static final int EMPTY = 0;
      public static final int CROSS = 1;
      public static final int NOUGHT = 2;
      public static final int PLAYING = 0;
      public static final int DRAW = 1;
      public static final int CROSS_WON = 2;
      public static final int NOUGHT_WON = 3;
      public static final int ROWS = 3, COLS = 3;
      public static int[][] board = new int[ROWS][COLS];
      public static int currentState;
      public static int currentPlayer;
      public static int currntRow, currentCol;
    public static int theSeed;
    public static Scanner in = new Scanner(System.in);

    
    public static void main(String[] args)
{
    int i=1,j;
    while(i!=0)
    {
        
        System.out.println("Enter the serial number you want to carry out\n");
        System.out.println("1.Play VS computer");
        System.out.println("2.Play 2 player");
        System.out.println("3.Check Records");
        j=in.nextInt();
        boolean validInput = false;
        do {
        switch(j)
        {
            case 1:
            {
                validInput = true;
                initGame();
                do {
                    currentPlayer = CROSS;
                    player1Move(currentPlayer);
                    printBoard();
                    updateGame(currentPlayer, currntRow, currentCol);
                    if(currentState == DRAW)
                    {
                        System.out.println("It's a Draw! Bye!");
                        break;
                    }
                    if (currentState == CROSS_WON)
                    {
                        System.out.println("'X' won! Bye!");
                        break;
                    }
                    System.out.println("The computer's move");
                    currentPlayer = NOUGHT;
                    computerMove(currentPlayer);
                    printBoard();
                    updateGame(currentPlayer, currntRow, currentCol);
                    if (currentState == NOUGHT_WON)
                    {
                        System.out.println("'O' won! Bye!");
                    }
                } while (currentState == PLAYING);
                break;
            }
            case 2:
            {
                validInput = true;
                initGame();
                do
                {
                    playerMove(currentPlayer);
                    updateGame(currentPlayer, currntRow, currentCol);
                    printBoard();
                    if (currentState == CROSS_WON)
                    {
                        System.out.println("'X' won! Bye!");
                    }
                    else if (currentState == NOUGHT_WON)
                    {
                        System.out.println("'O' won! Bye!");
                    }
                    else if (currentState == DRAW)
                    {
                        System.out.println("It's a Draw! Bye!");
                    }
                    currentPlayer = (currentPlayer == CROSS) ? NOUGHT : CROSS;
                } while (currentState == PLAYING);
                break;
            }
            case 3:
            {
                validInput = true;
                System.out.println("The latest records are:-");
                 {
                 BufferedWriter bw = null;
                try{
                bw = new BufferedWriter(new FileWriter("New.txt", true));
                if (currentState == CROSS_WON)
                {
                    bw.write("'X' won");
                }
                else if (currentState == NOUGHT_WON)
                {
                    bw.write("'O' won");
                }
                else if (currentState == DRAW)
                {
                    bw.write("Draw");
                }
                bw.newLine();
                bw.flush();
                bw.close();
                }
                catch (IOException e)
                {
                System.out.println("An error occurred.");
                e.printStackTrace();
                }
                try
                {
                File myobj1 = new File("New.txt");
                Scanner myReader = new Scanner(myobj1);
                while (myReader.hasNextLine())
                {
                String data = myReader.nextLine();
                System.out.println(data);
                }
                myReader.close();
                }
                catch (FileNotFoundException e)
                {
                System.out.println("An error occurred.");
                e.printStackTrace();
                }
                validInput = true;
                break;
                }
            }
            default:
            {
                System.out.println("Enter a valid input");
                validInput=true;
                break;
            }
        }
        
        } while(!validInput);
            System.out.println("Enter 0 to exit");
        i=in.nextInt();
    }
}
public static void initGame() {
     for (int row = 0; row < ROWS; ++row)
     {
        for (int col = 0; col < COLS; ++col)
        {
           board[row][col] = EMPTY;
        }
     }
     currentState = PLAYING;
     currentPlayer = CROSS;
  }

public static void playerMove(int theSeed)
{
boolean validInput = false;
do {
    if (theSeed == CROSS)
    {
        System.out.print("Player 'X', enter your move (row[1-3] column[1-3]): ");
    }
    else
    {
        System.out.print("Player 'O', enter your move (row[1-3] column[1-3]): ");
    }
    String s1=in.nextLine();
    String s2=in.nextLine();
    int row=0;
    int col=0;
    try{
        row = Integer.parseInt(s1.trim())-1;
        col = Integer.parseInt(s2.trim())-1;
        if(row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == EMPTY)
           {
                      currntRow = row;
                      currentCol = col;
                      board[currntRow][currentCol] = theSeed;
                      validInput = true;
           }
    }
    catch(NumberFormatException ex)
    {
        System.out.println("This move at (" + (s1) + "," + (s2)
                     + ") is not valid. Try again...");
    }
    } while (!validInput);
}

public static void player1Move(int theSeed)
{
    boolean validInput = false;
    do
    {
        System.out.print("Player 'X', enter your move (row[1-3] column[1-3]): ");
        theSeed=CROSS;
        String s1=in.nextLine();
        String s2=in.nextLine();
        int row=0;
        int col=0;
        try{
            row = Integer.parseInt(s1.trim())-1;
            col = Integer.parseInt(s2.trim())-1;
            if(row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == EMPTY)
               {
                          currntRow = row;
                          currentCol = col;
                          board[currntRow][currentCol] = theSeed;
                          validInput = true;
               }
        }
        catch(NumberFormatException ex)
        {
            System.out.println("This move at (" + (s1) + "," + (s2)
                         + ") is not valid. Try again...");
        }
        
    } while (!validInput);
}
   
public static void computerMove(int theSeed)
{
    boolean validInput = false;
    theSeed=NOUGHT;
    do
    {
        if(board[0][0]==NOUGHT && board[0][1]==NOUGHT && board[0][2]==EMPTY)
        {
            board[0][2]=theSeed;
            currntRow = 0;
            break;
        }
        else if(board[0][2]==NOUGHT && board[0][1]==NOUGHT && board[0][0]==EMPTY)
        {
            board[0][0]=theSeed;
            currntRow = 0;
            break;
        }
        else if(board[0][0]==NOUGHT && board[0][2]==NOUGHT && board[0][1]==EMPTY)
        {
            board[0][1]=theSeed;
            currntRow = 0;
            break;
        }
        else if(board[1][0]==NOUGHT && board[1][1]==NOUGHT && board[1][2]==EMPTY)
        {
            board[1][2]=theSeed;
            currntRow = 1;
            break;
        }
        else if(board[1][0]==NOUGHT && board[1][2]==NOUGHT && board[1][1]==EMPTY)
        {
            board[1][1]=theSeed;
            currntRow = 1;
            break;
        }
        else if(board[1][1]==NOUGHT && board[1][2]==NOUGHT && board[1][0]==EMPTY)
        {
            board[1][0]=theSeed;
            currntRow = 1;
            break;
        }
        else if(board[2][0]==NOUGHT && board[2][1]==NOUGHT && board[2][2]==EMPTY)
        {
            board[2][2]=theSeed;
            currntRow = 2;
            break;
        }
        else if(board[2][0]==NOUGHT && board[2][2]==NOUGHT && board[2][1]==EMPTY)
        {
            board[2][1]=theSeed;
            currntRow = 2;
            break;
        }
        else if(board[2][1]==NOUGHT && board[2][2]==NOUGHT && board[2][0]==EMPTY)
        {
            board[2][0]=theSeed;
            currntRow = 2;
            break;
        }
        else if(board[0][0]==NOUGHT && board[1][0]==NOUGHT && board[2][0]==EMPTY)
        {
            board[2][0]=theSeed;
            currentCol=0;
            break;
        }
        else if(board[1][0]==NOUGHT && board[2][0]==NOUGHT && board[0][0]==EMPTY)
        {
            board[0][0]=theSeed;
            currentCol=0;
            break;
        }
        else if(board[0][0]==NOUGHT && board[2][0]==NOUGHT && board[1][0]==EMPTY)
        {
            board[1][0]=theSeed;
            currentCol=0;
            break;
        }
        else if(board[0][1]==NOUGHT && board[1][1]==NOUGHT && board[2][1]==EMPTY)
        {
            board[2][1]=theSeed;
            currentCol=1;
            break;
        }
        else if(board[0][1]==NOUGHT && board[2][1]==NOUGHT && board[1][1]==EMPTY)
        {
            board[0][2]=theSeed;
            currentCol=1;
            break;
        }
        else if(board[1][1]==NOUGHT && board[2][1]==NOUGHT && board[0][1]==EMPTY)
        {
            board[0][1]=theSeed;
            currentCol=1;
            break;
        }
        else if(board[0][2]==NOUGHT && board[1][2]==NOUGHT && board[2][2]==EMPTY)
        {
            board[2][2]=theSeed;
            currentCol=2;
            break;
        }
        else if(board[0][2]==NOUGHT && board[2][2]==NOUGHT && board[1][2]==EMPTY)
        {
            board[1][2]=theSeed;
            currentCol=2;
            break;
        }
        else if(board[1][2]==NOUGHT && board[2][2]==NOUGHT && board[0][2]==EMPTY)
        {
            board[0][2]=theSeed;
            currentCol=2;
            break;
        }
        else if(board[0][0]==NOUGHT && board[1][1]==NOUGHT && board[2][2]==EMPTY)
        {
            board[2][2]=theSeed;
            break;
        }
        else if(board[0][0]==NOUGHT && board[2][2]==NOUGHT && board[1][1]==EMPTY)
        {
            board[1][1]=theSeed;
            break;
        }
        else if(board[1][1]==NOUGHT && board[2][2]==NOUGHT && board[0][0]==EMPTY)
        {
            board[0][0]=theSeed;
            break;
        }
        else if(board[0][2]==NOUGHT && board[1][1]==NOUGHT && board[2][0]==EMPTY)
        {
            board[2][0]=theSeed;
            break;
        }
        else if(board[0][2]==NOUGHT && board[2][0]==NOUGHT && board[1][1]==EMPTY)
        {
            board[1][1]=theSeed;
            break;
        }
        else if(board[2][0]==NOUGHT && board[1][1]==NOUGHT && board[0][2]==EMPTY)
        {
            board[0][2]=theSeed;
            break;
        }
        else if(board[0][0]==CROSS && board[0][1]==CROSS && board[0][2]==EMPTY)
        {
            board[0][2]=theSeed;
            break;
        }
        else if(board[0][2]==CROSS && board[0][1]==CROSS && board[0][0]==EMPTY)
        {
            board[0][0]=theSeed;
            break;
        }
        else if(board[0][0]==CROSS && board[0][2]==CROSS && board[0][1]==EMPTY)
        {
            board[0][1]=theSeed;
            break;
        }
        else if(board[1][0]==CROSS && board[1][1]==CROSS && board[1][2]==EMPTY)
        {
            board[1][2]=theSeed;
            break;
        }
        else if(board[1][0]==CROSS && board[1][2]==CROSS && board[1][1]==EMPTY)
        {
            board[1][1]=theSeed;
            break;
        }
        else if(board[1][1]==CROSS && board[1][2]==CROSS && board[1][0]==EMPTY)
        {
            board[1][0]=theSeed;
            break;
        }
        else if(board[2][0]==CROSS && board[2][1]==CROSS && board[2][2]==EMPTY)
        {
            board[2][2]=theSeed;
            break;
        }
        else if(board[2][0]==CROSS && board[2][2]==CROSS && board[2][1]==EMPTY)
        {
            board[2][1]=theSeed;
            break;
        }
        else if(board[2][1]==CROSS && board[2][2]==CROSS && board[2][0]==EMPTY)
        {
            board[2][0]=theSeed;
            break;
        }
        else if(board[0][0]==CROSS && board[1][0]==CROSS && board[2][0]==EMPTY)
        {
            board[2][0]=theSeed;
            break;
        }
        else if(board[1][0]==CROSS && board[2][0]==CROSS && board[0][0]==EMPTY)
        {
            board[0][0]=theSeed;
            break;
        }
        else if(board[0][0]==CROSS && board[2][0]==CROSS && board[1][0]==EMPTY)
        {
            board[1][0]=theSeed;
            break;
        }
        else if(board[0][1]==CROSS && board[1][1]==CROSS && board[2][1]==EMPTY)
        {
            board[2][1]=theSeed;
            break;
        }
        else if(board[0][1]==CROSS && board[2][1]==CROSS && board[1][1]==EMPTY)
        {
            board[0][2]=theSeed;
            break;
        }
        else if(board[1][1]==CROSS && board[2][1]==CROSS && board[0][1]==EMPTY)
        {
            board[0][1]=theSeed;
            break;
        }
        else if(board[0][2]==CROSS && board[1][2]==CROSS && board[2][2]==EMPTY)
        {
            board[2][2]=theSeed;
            break;
        }
        else if(board[0][2]==CROSS && board[2][2]==CROSS && board[1][2]==EMPTY)
        {
            board[1][2]=theSeed;
            break;
        }
        else if(board[1][2]==CROSS && board[2][2]==CROSS && board[0][2]==EMPTY)
        {
            board[0][2]=theSeed;
            break;
        }
        else if(board[0][0]==CROSS && board[1][1]==CROSS && board[2][2]==EMPTY)
        {
            board[2][2]=theSeed;
            break;
        }
        else if(board[0][0]==CROSS && board[2][2]==CROSS && board[1][1]==EMPTY)
        {
            board[1][1]=theSeed;
            break;
        }
        else if(board[1][1]==CROSS && board[2][2]==CROSS && board[0][0]==EMPTY)
        {
            board[0][0]=theSeed;
            break;
        }
        else if(board[0][2]==CROSS && board[1][1]==CROSS && board[2][0]==EMPTY)
        {
            board[2][0]=theSeed;
            break;
        }
        else if(board[0][2]==CROSS && board[2][0]==CROSS && board[1][1]==EMPTY)
        {
            board[1][1]=theSeed;
            break;
        }
        else if(board[2][0]==CROSS && board[1][1]==CROSS && board[0][2]==EMPTY)
        {
            board[0][2]=theSeed;
            break;
        }
        else
        {
            int max = 3;
            int min = 1;
            int range = max - min + 1;
            int rand = (int)(Math.random() * range) + min;
            theSeed=NOUGHT;
            int row = (int)(Math.random() * range) + min - 1;
            int col = (int)(Math.random() * range) + min - 1;
            if (row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == EMPTY)
            {
                currntRow = row;
                currentCol = col;
                board[currntRow][currentCol] = theSeed;
                validInput = true;
            }
        }
    }while (!validInput);
}
    
public static void updateGame(int theSeed, int currentRow, int currentCol)
{
    if (hasWon(theSeed, currentRow, currentCol))
    {
        currentState = (theSeed == CROSS) ? CROSS_WON : NOUGHT_WON;
    }
    else if (isDraw())
    {
          currentState = DRAW;
    }
}

public static boolean isDraw()
{
    for (int row = 0; row < ROWS; ++row)
    {
        for (int col = 0; col < COLS; ++col)
        {
             if (board[row][col] == EMPTY)
             {
                return false;
             }
        }
    }
    return true;
}
    
public static boolean hasWon(int theSeed, int currentRow, int currentCol)
{
    return(board[currentRow][0] == theSeed && board[currentRow][1] == theSeed && board[currentRow][2] == theSeed || board[0][currentCol] == theSeed && board[1][currentCol] == theSeed && board[2][currentCol] == theSeed || board[0][0] == theSeed && board[1][1] == theSeed && board[2][2] == theSeed || board[0][2] == theSeed && board[1][1] == theSeed && board[2][0] == theSeed);
    
}

public static void printBoard()
{
    for (int row = 0; row < ROWS; ++row)
    {
        for (int col = 0; col < COLS; ++col)
        {
                printCell(board[row][col]);
                if (col != COLS - 1)
                {
                   System.out.print("|");
                }
        }
        System.out.println();
        if (row != ROWS - 1)
        {
            System.out.println("-----------");
        }
    }
    System.out.println();
}
     
public static void printCell(int content)
{
    switch (content)
    {
             case EMPTY:  System.out.print("   "); break;
             case NOUGHT: System.out.print(" O "); break;
             case CROSS:  System.out.print(" X "); break;
    }
}
}

