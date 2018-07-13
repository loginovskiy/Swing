package ru.loginovskiy.windowlsn.binaryconvert;

import java.util.Arrays;

import static java.lang.Math.pow;

public class Convert
{
    static int num = 2147;
    static byte[] arr = new byte[32];
    static String[] hexArr = new String[8];
    static String[] hexSymbols = {"A","B","C","D","E","F"};

    public static void main(String[] args)
    {
        long shift = 1;
        int count = 0;
        shift<<=31;
        System.out.println(shift);
        System.out.print("BIN = ");
        for (int i = 0; i < 32 ; i++)
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
        count = 0;
        for (int i = 0; i < 8; i++)
        {
            if(count == 2)
            {
                System.out.print(" ");
                count = 0;
            }
            System.out.print(hexArr[i]);
            count++;
        }

    }
}
