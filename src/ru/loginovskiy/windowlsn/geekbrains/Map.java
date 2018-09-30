package ru.loginovskiy.windowlsn.geekbrains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel
{
    private int WIDTH;                                      //WIDTH - ШИРИНА ИГРОВОГО ПОЛЯ
    private int HEIGHT;                                     //HEIGHT - ВЫСОТА ИГРОВОГО ПОЛЯ
    private int widthCell;                                  //widthCell - КОЛЛИЧЕСТВО ГОРИЗОНТАЛЬНЫХ ЯЧЕЕК
    private int heightCell;                                 //heightCell - КОЛЛИЧЕСТВО ВЕРТИКАЛЬНЫХ ЯЧЕЕК
    private int[][] field;                                  //field - ДВУМЕРНЫЙ МАССИВ ДЛЯ ИГРОВОГО ПОЛЯ
    private GameLogical logical;
    private int vinLen;
    Random rnd = new Random();
    private enum GameState {UNINICIALASED, PLAYING, WIN_PLAYER1, WIN_PLAYER2, DRAW };
    private GameState gameState =  GameState.UNINICIALASED;
    private final static int EMPTY = 0;
    private final static int USER1 = 1;
    private final static int USER2 = 2;

    Map(int CELL_W, int CELL_H, int vinLen)
    {
        setBackground(Color.DARK_GRAY);
        widthCell = CELL_W;
        heightCell = CELL_H;
        this.vinLen = vinLen;
        field = new int[heightCell][widthCell];
        logical = new GameLogical(heightCell, widthCell, vinLen, USER1, USER2, EMPTY, field);
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
        float cellWSize = WIDTH / (float)widthCell;
        float cellHSize = HEIGHT / (float)heightCell;
        g.setColor(Color.GREEN);
        for (int i = 1; i < widthCell; i++)
        {
            g.drawLine((int)(cellWSize*i), 0 , (int)(cellWSize*i), HEIGHT);
        }
        for (int i = 1; i < heightCell ; i++)
        {
            g.drawLine(0, (int)(cellHSize*i), WIDTH,(int)(cellHSize*i));
        }
    }

    protected void startNewGame(int WIDTH, int HEIGHT)
    {
        gameState=GameState.PLAYING;
        clearField();
        this.WIDTH = WIDTH-5;
        this.HEIGHT = HEIGHT - 50;
        repaint();
    }

    void mouseRelease(MouseEvent e)
    {
        if(gameState != GameState.PLAYING) return;
        int x,y;
        x = (int)(e.getX()/(WIDTH / (float)widthCell));
        y = (int)(e.getY()/(HEIGHT / (float)heightCell));
        //System.out.println(x+"  "+y);
        if(!isEmptyCell(y,x))return;
        field[y][x] = USER1;
        repaint();
        if(logical.checkWin(USER1, x, y))
        {
            gameState = GameState.WIN_PLAYER1;
            System.out.println("Выйгрыш");
            return;
        }
        if(logical.isMapFull())
        {
            gameState = GameState.DRAW;
            System.out.println("Ничья");
            return;
        }
        logical.aiTern(USER2, USER1, 0, 0);
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
                g.fillOval(j * (int)(WIDTH / (float)widthCell),i*(int)(HEIGHT / (float)heightCell),
                        (int)(WIDTH/ (float)(widthCell)), (int)(HEIGHT / (float)(heightCell)));
            }
        }
    }
    protected boolean isEmptyCell(int m, int n)
    {
        return field[m][n] == EMPTY;
    }

    private void clearField()
    {
        for (int i = 0; i < heightCell ; i++)
        {
            for (int j = 0; j < widthCell ; j++)
            {
                field[i][j] = EMPTY;
            }
        }
    }
}
