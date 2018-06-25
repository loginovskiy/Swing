package ru.loginovskiy.windowlsn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame implements ActionListener
{
    protected final JButton startBtn = new JButton("Старт");
    protected final JButton exitBtn = new JButton("Выход");
    protected final int winWidth = 500;
    protected final int winHeight = 535;
    protected final int winTable = 3;
    JPanel panel = new JPanel(new GridLayout(1,2));
    Field field = new Field(winTable);
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new GameWindow();
            }
        });
    }
    GameWindow()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Крестики нолики");
        setSize(winWidth,winHeight);
        setResizable(false);
        setLocationRelativeTo(null);
        panel.add(startBtn);
        panel.add(exitBtn);
        add(panel, BorderLayout.SOUTH);
        startBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        add(field, BorderLayout.CENTER);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object src = e.getSource();
        if(src==startBtn)
        {
            field.startNewGame();
        }
        else if(src==exitBtn)
        {
            System.exit(0);
        }
    }
}
