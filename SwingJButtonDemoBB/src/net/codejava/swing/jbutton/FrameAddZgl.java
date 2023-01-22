package net.codejava.swing.jbutton;

import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;

public class FrameAddZgl {
    Connection c;
    FrameAddZgl(Connection c){
        this.c = c;
    }
    public void dodajZgloszenie() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArrayList<AddField> list = new ArrayList<>();
                list.add(new AddField("id_zaw", 'i'));
                list.add(new AddField("id_kon", 's'));
                FrameAddToBase frameAddToBase = new FrameAddToBase(c);
                frameAddToBase.add("Dodaj zgloszenie", "zgloszenie", list);
            }
        });
    }
}
