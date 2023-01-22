package net.codejava.swing.jbutton;

import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;

public class FrameAddRep {
    Connection c;
    FrameAddRep(Connection c){
        this.c = c;
    }
    public void dodajReprezentację() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArrayList<AddField> list = new ArrayList<>();
                list.add(new AddField("nazwa_rep", 's'));
                list.add(new AddField("kwota_bazowa", 'i'));
                FrameAddToBase frameAddToBase = new FrameAddToBase(c);
                frameAddToBase.add("Dodaj reprezentacje", "reprezentacja", list);
            }
        });
    }
}
