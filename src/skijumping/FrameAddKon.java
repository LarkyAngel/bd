package skijumping;

import java.awt.*;
import java.sql.Connection;
import java.util.ArrayList;

public class FrameAddKon {
    Connection c;
    FrameAddKon(Connection c){
        this.c = c;
    }
    public void dodajKonkurs() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArrayList<AddField> list = new ArrayList<>();
                /*list.add(new AddField("id_kon", 'i'));
                list.add(new AddField("data", 'e'));
                list.add(new AddField("termin_zgloszen", 'e'));
                list.add(new AddField("nazwa_org", 's'));
                list.add(new AddField("miejsce", 's'));*/
                FrameAddToBase frameAddToBase = new FrameAddToBase(c);
                frameAddToBase.add("Dodaj konkurs", "konkurs", list);
            }
        });
    }
}
