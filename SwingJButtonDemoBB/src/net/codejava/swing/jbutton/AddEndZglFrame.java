package net.codejava.swing.jbutton;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

import static net.codejava.swing.jbutton.SkiJumping.stan;

public class AddEndZglFrame {
    Connection c;
    JTextField id_kon;
    AddEndZglFrame(Connection c, JTextField id_kon){//drugi parametr pewnie do zmiany
        this.c = c;
        this.id_kon = id_kon;
    }
    public void runFrame(){
        JFrame frame11 = new JFrame("Utwórz konkurs");
        frame11.setLayout(new FlowLayout());
        frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JButton button11 = new JButton("Dodaj zgłoszenie");
        frame11.add(button11);
        JButton button21 = new JButton("Zakończ zgłaszanie");
        frame11.add(button21);
        button11.addActionListener(e111 -> { //nietestowane, nie wiem czy dziala
            FrameAddZgl frameAddZgl = new FrameAddZgl(c);
            frameAddZgl.dodajZgloszenie();

        });
        button21.addActionListener(e111 -> {//zamykamy zgloszenia
            try {
                stan.put(Integer.parseInt(id_kon.getText()), 1);
            } catch (Exception e11) {
                e11.printStackTrace();
                System.err.println(e11.getClass().getName()+": "+e11.getMessage());
                System.exit(0);
            }
            frame11.dispose();
        });
        frame11.setSize(150, 100);
        frame11.setVisible(true);
        //frame11.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame11.setLocationRelativeTo(null);
    }
}
