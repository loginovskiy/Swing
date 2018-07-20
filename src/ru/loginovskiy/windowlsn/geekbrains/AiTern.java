package ru.loginovskiy.windowlsn.geekbrains;
import java.util.Random;
public class AiTern
{
    int[][] gameField;
    int fieldSizeY;
    int fieldSizeX;
    int vinLen;
    int ai_dot;
    int user_dot;
    int empty_dot;
    int coordinateY;
    int coordinateX;
    Random rnd = new Random();
    AiTern(int[][] gameField, int vinLen, int user_dot, int ai_dot, int empty_dot)
    {
        this.gameField = gameField;
        this.vinLen = vinLen;
        this.user_dot = user_dot;
        this.ai_dot = ai_dot;
        this.empty_dot = empty_dot;
        fieldSizeY = gameField.length;
        fieldSizeX = gameField[1].length;
    }

    protected void tryToWin()
    {
        if(checkHWLine(ai_dot))gameField[coordinateY][coordinateX] = ai_dot;

    }

    protected void usrBlock()
    {

    }
    protected void rndTern()
    {
        int m,n;
        do
        {
            m = rnd.nextInt(fieldSizeY);
            n = rnd.nextInt(fieldSizeX);
        }while(gameField[m][n]!=empty_dot);
        gameField[m][n]=ai_dot;
        return;
    }
    public void tern(int targetDot)
    {
        tryToWin();
        //usrBlock();
        rndTern();
    }

    protected boolean checkHWLine(int targetDot)
    {
        int counter, counter1;
        counter = counter1 = 0;
        for (int i = 0; i < fieldSizeY ; i++)
        {
            for (int j = 0; j < fieldSizeX ; j++)
            {
                if(gameField[i][j] != targetDot) counter = 0;
                else if(gameField[i][j] == targetDot) counter++;
                if((counter == (vinLen-1) & (j+1)<fieldSizeX) & isEmptyCell(i, j+1))
                {
                        coordinateY = i;
                        coordinateX = j + 1;
                        return true;
                }
            }
            for (int j = 0; j < fieldSizeX ; j++)
            {
                if(gameField[j][i] != targetDot) counter1 = 0;
                else if(gameField[j][i] == targetDot) counter1++;
                if((counter1 == (vinLen-1) & (i+1)<fieldSizeY) & isEmptyCell(j+1, i))
                {
                        coordinateY = j + 1;
                        coordinateX = i;
                        return true;
                }
            }
        }
        return false;
    }

//    protected boolean checkDLine(int targetDot)
//    {
//        int counter, counter1;
//        counter = counter1 = 0;
//        int maxSide = fieldSizeY>fieldSizeX?fieldSizeY:fieldSizeX;
//        if(winLen <= maxSide)
//        {
//            for (int i = fieldSizeY - winLen; i >= 0; i--)                           // Проверка диагоналей
//            {
//                for (int j = i, k = 0; j < fieldSizeY & k < gameField[i].length; j++, k++)
//                {
//                    if(gameField[j][k] == targetDot)counter++;
//                    if(counter > 1)
//                }
//            }
//            for (int i = lenX - winLen; i > 0; i--)
//            {
//                for (int j = 0, k = i; j < arr.length & k < lenX; j++, k++)
//                {
//                    arr[j][k] = 1;
//                }
//            }                                                                   // Проверка диагоналей
//        }
//        showMatrix(arr, lenY, lenX);                                            // Вывод массива в консоль
//    }
//        return false;
//    }

    protected boolean isEmptyCell(int m, int n)
    {
        return gameField[m][n] == empty_dot;
    }
}