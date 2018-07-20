package ru.loginovskiy.windowlsn.geekbrains;

public class Test2
{
    public static int WIDTH = 5;
    public static int HEIGHT = 5;
    public static int WINLEN = 3;
    public static int lastTernX = 2;
    public static int lastTernY = 2;
    public static int USER_DOT = 1;
    public static int AI_DOT = 2;
    public static int EMPTY = 0;
    public static int[][] map = new int[HEIGHT][WIDTH];

    Test2()
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
        map[2][2] = 1;
        map[3][1] = 1;
        map[4][0] = 1;
        for (int i = 0; i < HEIGHT ; i++)
        {
            System.out.println();

            for (int j = 0; j < WIDTH; j++)
            {
                System.out.print(map[i][j] + "  ");
            }
        }
        System.out.println(checkWin(USER_DOT));
    }
    public static boolean checkWin(int targetDot)
    {
        if(checkLine(lastTernX,lastTernY,1,0, WINLEN, targetDot))return true;
        if(checkLine(lastTernX,lastTernY,1,1, WINLEN, targetDot))return true;
        if(checkLine(lastTernX,lastTernY,0,1, WINLEN, targetDot))return true;
        if(checkLine(lastTernX,lastTernY,1,-1, WINLEN, targetDot))return true;
        if(checkLine(lastTernX,lastTernY,-1,1, WINLEN, targetDot))return true;
        if(checkLine(lastTernX,lastTernY,-1,-1, WINLEN, targetDot))return true;
        if(checkLine(lastTernX,lastTernY,-1,0, WINLEN, targetDot))return true;
        if(checkLine(lastTernX,lastTernY,0,-1, WINLEN, targetDot))return true;
        return false;
    }
    public static boolean checkLine(int x, int y, int vx, int vy, int vlen, int target_dot)
    {
        int farX = x + (vlen-1)*vx;
        int farY = y + (vlen-1)*vy;
        if(isValidCell(farY,farX)) return false;
        for (int i = 0; i < vlen ; i++)
        {
            if(map[y+i*vy][x+i*vx] != target_dot) return false;
        }
        return true;
    }
    public static boolean isValidCell(int y, int x)
    {
        return y > HEIGHT || x > WIDTH;
    }
}
