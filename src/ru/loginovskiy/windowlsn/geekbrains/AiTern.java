package ru.loginovskiy.windowlsn.geekbrains;
import java.util.Random;
public class AiTern
{
    int[][] gameField;
    int fieldSizeY;
    int fieldSizeX;
    int ai_dot;
    int user_dot;
    int empty_dot;
    Random rnd = new Random();
    AiTern(int[][] gameField, int ai_dot, int empty_dot, int user_dot, int cellHeight, int cellWidth)
    {
        this.gameField = gameField;
        fieldSizeY = cellHeight;
        fieldSizeX = cellWidth;
        this.user_dot = user_dot;
        this.ai_dot = ai_dot;
        this.empty_dot = empty_dot;
    }

    protected void tryToWin()
    {

    }

    protected void usrBlock()
    {

    }
    protected void rndTern()
    {
        int x,y;
        do
        {
            y = rnd.nextInt(fieldSizeY);
            x = rnd.nextInt(fieldSizeX);
        }while(gameField[y][x]!=empty_dot);
        gameField[y][x]=ai_dot;
        return;
    }
    public void Tern()
    {
        tryToWin();
        usrBlock();
        rndTern();
    }

    protected void checkHVLine(int ai_dot, int empty_dot)
    {
        int y;
        int x;
        int count1,count2;
        for (int i = 0; i <fieldSizeY ; i++)
        {
            count1=count2=0;
            for (int j = 0; j <fieldSizeX ; j++)
            {
                if(field[i][j]==USR_DOT)
                {
                    count1++;
                    if(count1>1) for (int k = 0; k <fieldSizeX ; k++)
                    {
                        if(field[i][k]==EMPTY_DOT)
                        {
                            field[i][k]=AI_DOT;
                            return;
                        }
                    }
                }
                if(field[j][i]==USR_DOT)
                {
                    count2++;
                    if(count2>1) for (int k = 0; k <fieldSizeY ; k++)
                    {
                        if(field[k][i]==EMPTY_DOT)
                        {
                            field[k][i]=AI_DOT;
                            return;
                        }
                    }
                }
            }
        }
    }
}
