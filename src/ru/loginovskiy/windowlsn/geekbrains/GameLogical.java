package ru.loginovskiy.windowlsn.geekbrains;

import java.util.Random;

public class GameLogical
{
    private int WIDTH;
    private int HEIGHT;
    private int WINLEN;
    private int USER_DOT;
    private int AI_DOT;
    private int EMPTY;
    private static int blockY;
    private static int blockX;
    public static int[][] map;
    private Random rnd = new Random();

    GameLogical(int width, int height, int winlen, int user_dot, int ai_dot, int empty, int [][] map)
    {
        this.WIDTH = width-1;
        this.HEIGHT = height-1;
        this.WINLEN = winlen;
        this.USER_DOT = user_dot;
        this.AI_DOT = ai_dot;
        this.EMPTY = empty;
        this.map = map;
    }

    public boolean checkWin(int targetDot, int lastTernX, int lastTernY)
    {
        if(checkLine(lastTernX,lastTernY, 1, 0, WINLEN, targetDot))return true;
        if(checkLine(lastTernX,lastTernY, 0, 1, WINLEN, targetDot))return true;
        if(checkDiagonal(lastTernX,lastTernY, 1, 1, WINLEN, targetDot))return true;
        if(checkDiagonal(lastTernX,lastTernY, 1, -1, WINLEN, targetDot))return true;
        return false;
    }

    private boolean checkLine(int x, int y, int vx, int vy, int len, int dot)
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
        for (int i = 0; i <= HEIGHT & i <= WIDTH ; i++)
        {
            if(map[cellY+i*vy][cellX+i*vx] == dot) counter++;
            else counter = 0;
            if(counter == len) return true;
        }
        return false;
    }

    private boolean checkDiagonal(int x, int y, int vx, int vy, int len, int dot)
    {
        int counter = 0;
        int cellX=x;
        int cellY=y;
        int bCell = vy>0?1:-1;
        int checkY, checkX;
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
        checkY = cellY;
        checkX = cellX;
        for (int i = 0; (checkY >= 0 & checkY <= HEIGHT) & (checkX>=0 & checkX<=WIDTH);
             i++, checkY = cellY+i*vy, checkX = cellX+i*vx)
        {
            if(map[cellY+i*vy][cellX+i*vx] == dot) counter++;
            else counter = 0;
            if(counter == len) return true;
        }
        return false;
    }

    private boolean opportunityToWin (int dot, int lastAiTernX, int lastAiTernY)
    {
        if(chekWinLine(lastAiTernX,lastAiTernY, 1, 0, WINLEN, dot))return true;
        if(chekWinLine(lastAiTernX,lastAiTernY, 0, 1, WINLEN, dot))return true;
//        if(chekWinDiagonale(lastTernX,lastTernY, 1, 1, WINLEN, dot))return true;
//        if(chekWinDiagonale(lastTernX,lastTernY, 1, -1, WINLEN, dot))return true;
        return false;
    }

    private boolean chekWinLine(int x, int y, int vx, int vy, int len, int dot)
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
        for (int i = 0; i <= HEIGHT & i <= WIDTH ; i++)
        {
            if(map[cellY+i*vy][cellX+i*vx] == EMPTY) map[cellY+i*vy][cellX+i*vx] = AI_DOT;
            if(checkLine(x, y, 1, 0, WINLEN, dot))
            {
                blockY=cellY+i*vy;
                blockX=cellX+i*vx;
                return true;
            }
            else map[cellY+i*vy][cellX+i*vx] = EMPTY;
        }
        return false;
    }

    public boolean isMapFull()
    {
        for (int i = 0; i < HEIGHT ; i++)
        {
            for (int j = 0; j < WIDTH ; j++)
            {
                if(map[i][j] == EMPTY)return false;
            }
        }
        return true;
    }

    private void rndAiTern(int dot)
    {
        int x,y;
        do
        {
            y = rnd.nextInt(2);
            x = rnd.nextInt(2);
        }while(!isEmptyCell(y,x));
        map[y][x] = dot;
    }

    private boolean isEmptyCell(int y, int x)
    {
        return map[y][x] == EMPTY;
    }

    public void aiTern(int dot, int edot, int lastAiTernX, int lastAiTernY)
    {
        if(opportunityToWin(dot, lastAiTernX, lastAiTernY))
        {
            map[blockY][blockX]=AI_DOT;
            return;
        }
        //if(block(edot, lastTernX, lastTernY))return;
        rndAiTern(dot);
    }
}