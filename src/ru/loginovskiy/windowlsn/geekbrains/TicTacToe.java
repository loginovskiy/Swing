package ru.loginovskiy.windowlsn.geekbrains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener
{
    private final int WIDTH = 400;
    private final int HEIGHT = 420;
    private final int CELL_W = 3;
    private final int CELL_H = 3;
    private final int WINLENGTH = 3;
    private JButton startBtn = new JButton("Start");
    private JButton exitBtn = new JButton("Exit");
    private JPanel panel = new JPanel(new GridLayout(1,2));
    Map map = new Map(CELL_W,CELL_H, WINLENGTH);
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new TicTacToe();
            }
        });
    }
    protected TicTacToe()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Крестики-нолики");
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        panel.add(startBtn);
        panel.add(exitBtn);
        startBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        add(panel, BorderLayout.SOUTH);
        add(map, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object src = e.getSource();
        if(src == startBtn)
        {
            map.startNewGame(WIDTH,HEIGHT);
        }
        else if(src == exitBtn)
        {
            System.exit(0);
        }
    }
}
