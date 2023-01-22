package net.codejava.swing.jbutton;

import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;

public class FrameAddZaw {
    Connection c;
    FrameAddZaw(Connection c){
        this.c = c;
    }
    public void dodajZawodnika() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArrayList<AddField> list = new ArrayList<>();
                list.add(new AddField("id_zaw", 'i'));
                list.add(new AddField("nazwa_rep", 's'));
                list.add(new AddField("imie", 's'));
                list.add(new AddField("nazwisko", 's'));
                FrameAddToBase frameAddToBase = new FrameAddToBase(c);
                frameAddToBase.add("Dodaj zawodnika", "zawodnik", list);
            }
        });
    }
}
