package ru.loginovskiy.windowlsn.jpanelLsn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class jPlsn extends JFrame implements ActionListener
{
    private JPanel panel1 = new JPanel(new GridLayout(1,2));
    private JButton startBtn = new JButton("Старт");
    private JButton exitBtn = new JButton("Выход");
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new jPlsn();
            }
        });
    }
    private jPlsn()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Крестики нолики");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        panel1.add(startBtn);
        panel1.add(exitBtn);
        startBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        add(panel1, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object btn = e.getSource();
        if(btn == startBtn) System.out.println("Старт");
        else if(btn == exitBtn) System.exit(0);
    }
}
