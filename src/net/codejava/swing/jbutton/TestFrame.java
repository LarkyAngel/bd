package net.codejava.swing.jbutton;

import java.awt.*;
import java.sql.Connection;

public class TestFrame extends AppJFrame{
    int id;
    TestFrame(int id){
        this.id = id;
    }
    @Override
    void prepareToShow() {
        setTitle("Ekran " + id);
        setSize(500, 500);
        Button b = new Button("OK");
        b.addActionListener(e -> {
            System.out.println("dupa");
            TestFrame t2 = new TestFrame(id+1);
            this.startSubframe(t2, e1 -> {
                System.out.println("dupa2");
            });
        });
        this.add(b);

    }
    public static void main(String[] args){
        Connection c = null;
        TestFrame testFrame = new TestFrame(1);
        testFrame.startMainframe(c);
    }
}
