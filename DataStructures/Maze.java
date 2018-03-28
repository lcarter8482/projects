import java.util.*;
import java.io.*;

public class Maze
{
    private char[][] m_maze = new char[][]
    {
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
        {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-'}
    };

    private int m_locationX;
    private int m_locationY;
    private IStack<String> m_undoStack;
    private IStack<String> m_redoStack;
    private String m_undoDirection;
    private String m_redoDirection;
    private boolean grog;

    public Maze()
    {
        m_locationX = 0;
        m_locationY = 0;
        m_undoStack = new StackAry<String>();
        m_redoStack = new StackAry<String>();
        m_undoDirection = "";
        m_redoDirection = "";
        grog = false;
    }

    public void go()
    {
        Scanner in = new Scanner(System.in);

        printMaze();
        String userInput = in.next();
        userInput = (userInput.toUpperCase());

        while (!(userInput.equals("Q")))
        {
            if (userInput.equals("N"))
            {
                goNorth();
            }
            else if (userInput.equals("S"))
            {
                goSouth();
            }
            else if (userInput.equals("E"))
            {
                goEast();
            }
            else if (userInput.equals("W"))
            {
                goWest();
            }
            else if (userInput.equals("G"))
            {
                drinkGrog();
            }
            else if (userInput.equals("U"))
            {
                undo();
            }
            else if (userInput.equals("R"))
            {
                redo();
            }
            else
            {
                System.out.println("Enter a valid command.");
            }
            
            printMaze();
            userInput = in.next();
            userInput = (userInput.toUpperCase());
        }
    }
    
    //prints the maze, location of character *, and undo/redo stacks
    public void printMaze()
    {
        System.out.println("\nUndo Stack: " + m_undoStack.toString());
        System.out.println("Redo Stack: " + m_redoStack.toString());
        
        for (int i = 0; i < m_maze.length; i++)
        {
            for (int j = 0; j < m_maze.length; j++)
            {
                if (i == m_locationX && j == m_locationY)
                {
                    System.out.print("* ");
                }
                else
                {
                    System.out.print(m_maze[i][j] + " ");
                }
            }
            System.out.println();
        }

        System.out.println("What have you mage?");
        System.out.println("N: North");
        System.out.println("S: South");
        System.out.println("E: East");
        System.out.println("W: West");
        System.out.println("G: Grog");
        System.out.println("U: Undo");
        System.out.println("R: Redo");
        System.out.println("Q: Quit");
    }

    public void goNorth()
    {
        m_redoStack.clear();
        move("N");
    }

    public void goSouth()
    {
        m_redoStack.clear();
        move("S");
    }

    public void goEast()
    {
        m_redoStack.clear();
        move("E");
    }

    public void goWest()
    {
        m_redoStack.clear();
        move("W");
    }

    private void move(String direction)
    {
        if (!isValidMove(direction)) //checks to see if a boundary is hit
        {
            System.out.println("OUCH!");
            return;
        }

        switch(direction) //moves the *
        {
            case "N":
                --m_locationX;
                break;
            case "S":
                ++m_locationX;
                break;
            case "E":
                ++m_locationY;
                break;
            case "W":
                --m_locationY;
                break;
            default:
                System.out.println("Something \'sploded"); //shouldn't print unless something is massively wrong
        }

        addToUndoStack(direction);
    }

    public void drinkGrog()
    {
        System.out.println("Gulp!");
        grog = true;
    }

    public void undo()
    {
        m_undoDirection = m_undoStack.pop();
        move(m_undoDirection);
        m_undoStack.pop();
        addToRedoStack(m_undoDirection);
        
        if (grog)
        {
            grog = false;
            System.out.println("Blah!");
        }
    }

    public void redo()
    {
        m_redoDirection = m_redoStack.pop();
        move(m_redoDirection);
    }

    private boolean isValidMove(String direction)
    {
        switch(direction)
        {
            case "N":
                return (m_locationX - 1 >= 0);
            case "S":
                return (m_locationX + 1 < m_maze[0].length);
            case "E":
                return (m_locationY + 1 < m_maze.length);
            case "W":
                return (m_locationY - 1 >= 0);
            default:
                return false;
        }
    }

    private void addToUndoStack(String direction)
    {
        switch (direction)
        {
            case "N":
                m_undoStack.push("S");
                break;
            case "S":
                m_undoStack.push("N");
                break;
            case "E":
                m_undoStack.push("W");
                break;
            case "W":
                m_undoStack.push("E");
                break;
            default:
                System.out.println("OH NO!");
                break;
        }
    }

    private void addToRedoStack(String direction)
    {
        switch (direction)
        {
            case "N":
                m_redoStack.push("S");
                break;
            case "S":
                m_redoStack.push("N");
                break;
            case "E":
                m_redoStack.push("W");
                break;
            case "W":
                m_redoStack.push("E");
                break;
            default:
                System.out.println("OH NO!");
                break;
        }
    }
}
