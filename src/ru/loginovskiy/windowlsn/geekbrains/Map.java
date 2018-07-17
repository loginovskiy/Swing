package ru.loginovskiy.windowlsn.geekbrains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel
{
    private int winWidth;
    private int winHeight;
    private int widthCell;
    private int heightCell;
    private int[][] field;
    Random rnd = new Random();
//    private static final int PLAYENG = 1;
//    private static final int MENU = 0;
//    private int gameState;
    private enum GameState {UNINICIALASED, PLAYING};
    private GameState gameState =  GameState.UNINICIALASED;
    private final static int EMPTY = 0;
    private final static int USER1 = 1;
    private final static int USER2 = 2;
    Map(int CELL_W, int CELL_H)
    {
        setBackground(Color.DARK_GRAY);
        widthCell = CELL_W;
        heightCell = CELL_H;
        field = new int[heightCell][widthCell];
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                mouseRelease(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(gameState == GameState.UNINICIALASED) return;
        paintMap(g);
        drawField(g);
    }

    protected void paintMap(Graphics g)
    {
        float cellWSize = winWidth / (float)widthCell;
        float cellHSize = winHeight / (float)heightCell;
        g.setColor(Color.GREEN);
        for (int i = 1; i < widthCell; i++)
        {
            g.drawLine((int)(cellWSize*i), 0 , (int)(cellWSize*i), winHeight);
        }
        for (int i = 1; i < heightCell ; i++)
        {
            g.drawLine(0, (int)(cellHSize*i), winWidth,(int)(cellHSize*i));
        }
    }

    protected void startNewGame(int WIDTH, int HEIGHT)
    {
        gameState=GameState.PLAYING;
        winWidth = WIDTH-5;
        winHeight = HEIGHT - 50;
        repaint();
    }

    void mouseRelease(MouseEvent e)
    {
        int x,y;
        x = (int)(e.getX()/(winWidth / (float)widthCell));
        y = (int)(e.getY()/(winHeight / (float)heightCell));
        System.out.println(x+"  "+y);
        if(!isEmptyCell(y,x))return;
        field[y][x] = USER1;
        repaint();
    }

    private void drawField(Graphics g)
    {
        for (int i = 0; i < heightCell; i++)
        {
            for (int j = 0; j < widthCell ; j++)
            {
                if(isEmptyCell(i,j))continue;
                if(field[i][j]==USER1)
                {
                    g.setColor(Color.BLUE);
                }
                else if(field[i][j]==USER2)
                {
                    g.setColor(Color.RED);
                }
                else throw new RuntimeException("Неизвестное значение");
                g.fillOval(j * (int)(winWidth / (float)widthCell),i*(int)(winHeight / (float)heightCell),
                        (int)(winWidth/ (float)(widthCell)), (int)(winHeight / (float)(heightCell)));
            }
        }
    }
    private boolean isEmptyCell(int m, int n)
    {
        return field[m][n] == EMPTY;
    }

    private void aiTern()
    {
        int y;
        int x;
        int count1,count2;
        int diagonale = heightCell>widthCell?widthCell:heightCell;
        for (int i = 0; i <heightCell ; i++)
        {
            count1=count2=0;
            for (int j = 0; j <widthCell ; j++)
            {
                if(field[i][j]==EMPTY)count1=0;
                if(field[i][j]==USER1)
                {
                    count1++;
                    if(count1>1) for (int k = 0; k <widthCell ; k++)
                    {
                        if(field[i][k]==EMPTY)
                        {
                            field[i][k]=USER2;
                            return;
                        }
                    }
                }
                if(field[j][i]==USER1)
                {
                    count2++;
                    if(count2>1) for (int k = 0; k <heightCell ; k++)
                    {
                        if(field[k][i]==EMPTY)
                        {
                            field[k][i]=USER2;
                            return;
                        }
                    }
                }
            }
        }
        count1=count2=0;
        for (int i = 0; i < diagonale ; i++)
        {
            if(field[i][i]==EMPTY) count1=count2=0;
            if(field[i][i]==USER1)
            {
                count1++;
                if(count1>1) for (int j = 0; j < diagonale ; j++)
                {
                    if(field[j][j]==EMPTY)
                    {
                        field[j][j]=USER2;
                        return;
                    }
                }
            }
            if(field[i][((i-(diagonale-1))*(-1))]==USER1)
            {
                count2++;
                if(count2>1) for (int j = 0; j <diagonale ; j++)
                {
                    if(field[j][((j-(diagonale-1))*(-1))]==EMPTY)
                    {
                        field[j][((j-(diagonale-1))*(-1))]=USER2;
                        return;
                    }
                }
            }
        }
        do
        {
            y = rnd.nextInt(heightCell);
            x = rnd.nextInt(widthCell);
        }while(field[y][x]!=EMPTY);
        field[y][x]=USER2;
        return;
    }
}
