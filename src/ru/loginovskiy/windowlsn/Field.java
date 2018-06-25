package ru.loginovskiy.windowlsn;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel
{
    protected float cellSize;
    protected int mapSize = 3;
    Field(int fieldTable)
    {
        repaint();
        setBackground(new Color(170,170,170));
    }

    protected void startNewGame()
    {

    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        cellSize=getWidth()/(float)mapSize;
        for (int i = 1; i < mapSize ; i++)
        {
            int x = (int)(i*cellSize);
            g.drawLine(x,0, x, height);
        }
        for (int i = 1; i < mapSize ; i++)
        {
            int y = (int)(i*cellSize);
            g.drawLine(0, y, width, y);
        }
    }
}
