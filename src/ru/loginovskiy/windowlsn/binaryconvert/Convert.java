package ru.loginovskiy.windowlsn.binaryconvert;

import java.util.Arrays;

import static java.lang.Math.pow;

public class Convert
{
    static int num = 15;
    static byte[] arr = new byte[8];
    static String[] hexArr = new String[2];
    static String[] hexSymbols = {"A","B","C","D","E","F"};

    public static void main(String[] args)
    {
        long shift = 1;
        int count = 0;
        shift<<=7;
        System.out.println(shift);
        System.out.print("BIN = ");
        for (int i = 0; i < 8 ; i++)
        {
            if(count == 8)
            {
                System.out.print(" ");
                count = 0;
            }
            if((shift & num)!=0)
            {
                System.out.print(1);
                arr[i]= (byte)1;
            }
            else
            {
                System.out.print(0);
                arr[i]= (byte)0;
            }
            shift>>=1;
            count++;
        }
//        System.out.println("\n"+Arrays.toString(arr));
        int arrLength = arr.length;
        count = 4;
        int summ = 0;
        int k=0;
        for (int i = 0; i < arrLength  ; i++)
        {
            summ += arr[i] * (int)pow(2,count-1);
            count--;
            if(count<=0)
            {
                if(summ>9)
                {
                    hexArr[k] = hexSymbols[summ-10];
                    k++;
                    count = 4;
                    summ = 0;
                }
                else
                {
                    hexArr[k] = Integer.toString(summ);
                    k++;
                    count = 4;
                    summ = 0;
                }
            }
        }
        System.out.println();
        System.out.print("HEX = ");
        for (int i = 0; i < 2; i++)
        {
            System.out.print(hexArr[i]);
        }

    }
}
