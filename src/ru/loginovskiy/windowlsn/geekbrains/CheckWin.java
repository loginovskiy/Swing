package ru.loginovskiy.windowlsn.geekbrains;

public class CheckWin
{
//    private static final int WIDTH = 5;
//    private static final int HEIGHT = 5;
//    private static final int WINLEN = 3;
//    private static int lastTernX = 0;
//    private static int lastTernY = 0;
//    private static final int USER_DOT = 1;
//    private static final int AI_DOT = 2;
//    private static final int EMPTY = 0;
//    public static int[][] map = new int[HEIGHT][WIDTH];
//
//    CheckWin()
//    {
//        for (int i = 0; i < HEIGHT ; i++)
//        {
//            for (int j = 0; j < WIDTH ; j++)
//            {
//                map[i][j] = EMPTY;
//            }
//        }
//    }

//    public static void main(String[] args)
//    {
//        map[0][0] = USER_DOT;
//        map[2][2] = USER_DOT;
//        //System.out.println(checkWin(USER_DOT));
//        block(USER_DOT);
//        showMap();
//    }

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

    public static void block(int targetDot)
    {
        playerBlockLine(lastTernX, lastTernY, 1, 0, WINLEN, targetDot);
        playerBlockLine(lastTernX, lastTernY, 0, 1, WINLEN, targetDot);
        playerBlockDiagonale(lastTernX, lastTernY, 1, 1, WINLEN, targetDot);
        playerBlockDiagonale(lastTernX, lastTernY, 1, -1, WINLEN, targetDot);
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
            if(map[cellY+i*vy][cellX+i*vx] == dot) counter++;
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

        for (int i = 0; ((cellX+i*vx)+vx < WIDTH & (cellX+i*vx)+vx >= 0) && ((cellY+i*vy)+vy < HEIGHT & (cellY+i*vy)+vy >= 0); i++)
        {
            if(map[cellY+i*vy][cellX+i*vx] == dot) counter++;
            else counter = 0;
            if(counter == len) return true;
        }
        return false;
    }

    private static void playerBlockLine(int x, int y, int vx, int vy, int len, int dot)
    {
        int cellX=x;
        int cellY=y;
        if ((vy>0?1:0)==0)
        {
            for (int i = 0; cellX > 0; i++)
            {
                cellX = x + i * (-1);
                cellY = y + i * 0;
            }
        }
        else if ((vy>0?1:0)==1)
        {
            for (int i = 0; cellY > 0 ; i++)
            {
                cellX = x+i*0;
                cellY = y+i*(-1);
            }
        }
        for(int i=0; i<HEIGHT & i<WIDTH; i++)
        {
            if (map[cellY+i*vy][cellX+i*vx]==EMPTY)
            {
                map[cellY+i*vy][cellX+i*vx]=USER_DOT;
                if(checkWin(USER_DOT))
                {
                    map[cellY+i*vy][cellX+i*vx]=AI_DOT;
                    return;
                }
                map[cellY+i*vy][cellX+i*vx]=EMPTY;
            }
            else continue;
        }
    }
    private static void playerBlockDiagonale(int x, int y, int vx, int vy, int len, int dot)
    {
        int cellX=x;
        int cellY=y;
        if((vy>0?1:-1)==1)
        {
            for (int i = 0; cellX >0 & cellY>0 ; i++)
            {
                cellX = x+i*(-1);
                cellY = y+i*(-1);
            }
        }
        else if((vy>0?1:-1)==(-1))
        {
            for (int i = 0; cellX>0 & cellY<HEIGHT; i++)
            {
                cellX = x+i*-1;
                cellY = y+i*1;
            }
        }
        for(int i = 0; ((cellX+i*vx)+vx < WIDTH & (cellX+i*vx)+vx >= 0) && ((cellY+i*vy)+vy < HEIGHT & (cellY+i*vy)+vy >= 0); i++)
        {
            if (map[cellY+i*vy][cellX+i*vx]==EMPTY)
            {
                map[cellY+i*vy][cellX+i*vx]=USER_DOT;
                if(checkWin(USER_DOT))
                {
                    map[cellY+i*vy][cellX+i*vx]=AI_DOT;
                    return;
                }
                map[cellY+i*vy][cellX+i*vx]=EMPTY;
            }
            else continue;
        }
    }
}