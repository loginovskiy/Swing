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
    private static int lastAiTernY;
    private static int lastAiTernX;
    private static int lastTernY;
    private static int lastTernX;
    public static int[][] map;
    private int[] randomise = {0,1,2,0,1,2,0,1,2,2,1,0};
    private Random rnd = new Random();
    private int flag = 0;

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

    public void aiTern()
    {
        if(flag == 0)
        {
            rndAiTern(AI_DOT);
            flag = 1;
            return;
        }
        if(tryToWin()) return;
        if(userBlock()) return;
        rndAiTern(AI_DOT);
    }

    private boolean tryToWin()
    {
        if(oportunityToWin())
        {
            map[lastAiTernY][lastAiTernX] = AI_DOT;
            return true;
        }
        return false;
    }

    private boolean userBlock()
    {
        if(playerBlockLine(lastTernX, lastTernY, 1, 0, WINLEN, USER_DOT)) return true;
        if(playerBlockLine(lastTernX, lastTernY, 0, 1, WINLEN, USER_DOT)) return true;
        if(playerBlockDiagonale(lastTernX, lastTernY, 1, 1,WINLEN, USER_DOT)) return true;
        if(playerBlockDiagonale(lastTernX, lastTernY, 1, -1,WINLEN, USER_DOT)) return true;
        return false;
    }

    private boolean oportunityToWin()
    {
        if(checkAiLine(lastAiTernX, lastAiTernY, 1, 0, WINLEN, AI_DOT))return true;
        if(checkAiLine(lastAiTernX, lastAiTernY, 0, 1, WINLEN, AI_DOT))return true;
        if(checkAiDiaganale(lastAiTernX,lastAiTernY, 1, 1, WINLEN, AI_DOT)) return true;
        if(checkAiDiaganale(lastAiTernX,lastAiTernY, 1, -1, WINLEN, AI_DOT)) return true;
        return false;
    }

    private boolean checkAiLine(int x, int y, int vx, int vy, int len, int dot)
    {
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
            if(isEmptyCell(cellY+i*vy, cellX+i*vx)) map[cellY+i*vy][cellX+i*vx] = dot;
            else continue;
            if(checkWin(AI_DOT, lastAiTernX, lastAiTernY))
            {
                lastAiTernX = cellX+i*vx;
                lastAiTernY = cellY+i*vy;
                map[cellY+i*vy][cellX+i*vx] = EMPTY;
                return true;
            }
            else map[cellY+i*vy][cellX+i*vx] = EMPTY;
        }

        return false;
    }

    private boolean checkAiDiaganale(int x, int y, int vx, int vy, int len, int dot)
    {
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
            if(isEmptyCell(cellY+i*vy, cellX+i*vx)) map[cellY+i*vy][cellX+i*vx] = dot;
            else continue;
            if(checkWin(AI_DOT, lastAiTernX, lastAiTernY))
            {
                lastAiTernX = cellX + i * vx;
                lastAiTernY = cellY + i * vy;
                map[cellY + i * vy][cellX + i * vx] = EMPTY;
                return true;
            }
            else map[cellY+i*vy][cellX+i*vx] = EMPTY;
        }
        return false;
    }

    private boolean playerBlockLine(int x, int y, int vx, int vy, int len, int dot)
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
        for(int i=0; i<=HEIGHT & i<=WIDTH; i++)
        {
            if (isEmptyCell(cellY + i * vy,cellX + i * vx))
            {
                map[cellY + i * vy][cellX + i * vx] = dot;
                if (checkWin(dot, x, y))
                {
                    map[cellY + i * vy][cellX + i * vx] = AI_DOT;
                    return true;
                }
                map[cellY + i * vy][cellX + i * vx] = EMPTY;
            } else continue;
        }
        return false;
    }

    private boolean playerBlockDiagonale(int x, int y, int vx, int vy, int len, int dot)
    {
        int cellX=x;
        int cellY=y;
        int checkY, checkX;
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
        checkY = cellY;
        checkX = cellX;
        for(int i = 0; (checkY >= 0 & checkY <= HEIGHT) & (checkX>=0 & checkX<=WIDTH);
            i++, checkY = cellY+i*vy, checkX = cellX+i*vx)
        {
            if (map[cellY+i*vy][cellX+i*vx]==EMPTY)
            {
                map[cellY+i*vy][cellX+i*vx]=dot;
                if(checkWin(dot, x, y))
                {
                    map[cellY+i*vy][cellX+i*vx]=AI_DOT;
                    return true;
                }
                map[cellY+i*vy][cellX+i*vx]=EMPTY;
            }
            else continue;
        }
        return false;
    }

    public boolean isMapFull()
    {
        for (int i = 0; i <= HEIGHT ; i++)
        {
            for (int j = 0; j <= WIDTH ; j++)
            {
                if(map[i][j] == EMPTY)return false;
            }
        }
        return true;
    }

    protected void rndAiTern(int dot)
    {
        int size = randomise.length;
        int[] tern = new int[2];
        do
        {
            tern[0] = randomise[rnd.nextInt(size)];
            tern[1] = randomise[rnd.nextInt(size)];
        }while(!isEmptyCell(tern[0],tern[1]));
        lastAiTernY = tern[0];
        lastAiTernX = tern[1];
        map[tern[0]][tern[1]] = dot;
        return;
    }

    private boolean isEmptyCell(int y, int x)
    {
        return (map[y][x] == EMPTY);
    }
    public int getLastAiTernY()
    {
        return lastAiTernY;
    }
    public int getLastAiTernX()
    {
        return lastAiTernX;
    }
    public void setLastTernX(int lastTernX)
    {
        this.lastTernX = lastTernX;
    }
    public void setLastAiTernY(int lastTernY)
    {
        this.lastTernY = lastTernY;
    }
}