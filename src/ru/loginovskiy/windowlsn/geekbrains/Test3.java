package ru.loginovskiy.windowlsn.geekbrains;

public class Test3
{
    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private static final int WINLEN = 3;
    private static int lastTernX = 2;
    private static int lastTernY = 2;
    private static final int USER_DOT = 1;
    private static final int AI_DOT = 2;
    private static final int EMPTY = 0;
    public static int[][] map = new int[HEIGHT][WIDTH];

    Test3()
    {
        for (int i = 0; i < HEIGHT ; i++)
        {
            for (int j = 0; j < WIDTH ; j++)
            {
                map[i][j] = EMPTY;
            }
        }
    }

    public static void main(String[] args)
    {
        map[2][2] = USER_DOT;
        map[1][3] = USER_DOT;
        map[0][4] = USER_DOT;
        System.out.println(checkWin(USER_DOT));
        showMap();
    }

    public static void showMap()
    {
        for (int i = 0; i < HEIGHT ; i++)
        {
            System.out.println();
            for (int j = 0; j < WIDTH ; j++)
            {
                System.out.print(map[i][j]+" ");
            }
        }
    }

    public static boolean checkWin(int targetDot)
    {
        System.out.println();
        if(checkLine(lastTernX,lastTernY, 1, 0, WINLEN, targetDot))return true;
        if(checkLine(lastTernX,lastTernY, 0, 1, WINLEN, targetDot))return true;
        if(checkDiagonal(lastTernX,lastTernY, 1, 1, WINLEN, targetDot))return true;
        if(checkDiagonal(lastTernX,lastTernY, -1, 1, WINLEN, targetDot))return true;
        return false;
    }

    private static boolean checkLine(int x, int y, int vx, int vy, int len, int dot)
    {
        int counter = 0;
        int cellX = x * vy;
        int cellY = y * vx;
        int length = vy==0?HEIGHT:WIDTH;
        for (int i = 0; i < length  ; i++)
        {
            if(map[cellX+i*vx][cellY+i*vy] == dot) counter++;
            else counter = 0;
            if(counter == len) return true;
        }
        return false;
    }

    private static boolean checkDiagonal(int x, int y, int vx, int vy, int len, int dot)
    {
        int counter = 0;
        int cellX=vx>0?x-y:x+y;
        int cellY=0;
        int length = vx>0?WIDTH-cellX:HEIGHT-cellY;
        for (int i = 0; i < length - cellX ; i++)
        {
            if(map[cellY+i*vy][cellX+i*vx] == dot) counter++;
            else counter = 0;
            if(counter == len) return true;
        }
        return false;
    }
}
