package ru.loginovskiy.windowlsn.geekbrains;

public class Test3
{
    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private static final int WINLEN = 3;
    private static int lastTernX = 2;
    private static int lastTernY = 0;
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
        //map[4][0] = USER_DOT;
        map[0][2] = USER_DOT;
        //map[1][3] = USER_DOT;
        map[2][4] = USER_DOT;
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
        if(checkDiagonal(lastTernX,lastTernY, 1, -1, WINLEN, targetDot))return true;
        return false;
    }

    private static boolean checkLine(int x, int y, int vx, int vy, int len, int dot)
    {
        int counter = 0;
        int cellX = x;
        int cellY = y;
        int bCell = vy>0?1:0;
        if (bCell==0)
        {
            for (int i = 0; cellX > 0; i++)
            {
                cellX = x + i * (-1);
                cellY = y + i * 0;
            }
        }
        else if (bCell==1)
        {
            for (int i = 0; cellY > 0 ; i++)
            {
                cellX = x+i*0;
                cellY = y+i*(-1);
            }

        }

        for (int i = 0; i < HEIGHT & i < WIDTH ; i++)
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
        int cellX=x;
        int cellY=y;
        int bCell = vy>0?1:-1;
        if(bCell==1)
        {
            for (int i = 0; cellX >0 & cellY>0 ; i++)
            {
                cellX = x+i*(-1);
                cellY = y+i*(-1);
            }
        }
        else if(bCell==(-1))
        {
            for (int i = 0; cellX>0 & cellY<HEIGHT; i++)
            {
                cellX = x+i*-1;
                cellY = y+i*1;
            }
        }
        for (int i = 0; cellX+i*vx < WIDTH & cellY+i*vy < HEIGHT; i++)
        {
            if(map[cellY+i*vy][cellX+i*vx] == dot) counter++;
            else counter = 0;
            if(counter == len) return true;
        }
        return false;
    }
}
