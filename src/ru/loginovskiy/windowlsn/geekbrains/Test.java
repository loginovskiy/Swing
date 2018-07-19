package ru.loginovskiy.windowlsn.geekbrains;

public class Test
{
    static int arr[][] = new int[5][10];
    public static void main(String[] args)
    {
        int lenY = arr.length;
        int lenX = arr[1].length;
        int winLen = 5;
        for (int i = 0; i < lenY ; i++)
        {
            for (int j = 0; j < lenX ; j++)
            {
                arr[i][j] = 0;
            }
        }
        int maxSide = lenY>lenX?lenY:lenX;
        if(winLen <= maxSide)
        {
            for (int i = lenY - winLen; i >= 0; i--)                           // Проверка диагоналей
            {
                for (int j = i, k = 0; j < lenY & k < arr[i].length; j++, k++)
                {
                    arr[j][k] = 1;
                }
            }
            for (int i = lenX - winLen; i > 0; i--)
            {
                for (int j = 0, k = i; j < arr.length & k < lenX; j++, k++)
                {
                    arr[j][k] = 1;
                }
            }                                                                   // Проверка диагоналей
        }
        showMatrix(arr, lenY, lenX);                                            // Вывод массива в консоль
    }

    public static void showMatrix (int[][] arr, int lenY, int lenX)
    {
        int count = 0;
        for (int i = 0; i < lenY ; i++)
        {
            if (count==lenX)
            {
                System.out.println();
                count = 0;
            }
            for (int j = 0; j < lenX ; j++)
            {
                System.out.print(arr[i][j]+"  ");
                count++;
            }
        }
    }
}
