package net.codejava.swing.jbutton;

import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;

public class FrameAddWyn {
    Connection c;
    FrameAddWyn(Connection c){
        this.c = c;
    }
    public void dodajWynik() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArrayList<AddField> list = new ArrayList<>();
                list.add(new AddField("id_kon", 'i'));
                list.add(new AddField("id_zaw", 'i'));
                list.add(new AddField("id_serii", 'i'));
                list.add(new AddField("punkty", 'd'));
                list.add(new AddField("odleglosc", 'd'));
                FrameAddToBase frameAddToBase = new FrameAddToBase(c);
                frameAddToBase.add("Dodaj wynik", "wynik", list);
            }
        });
    }
}
