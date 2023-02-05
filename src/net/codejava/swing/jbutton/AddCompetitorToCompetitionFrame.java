package net.codejava.swing.jbutton;

import java.util.ArrayList;

public class AddCompetitorToCompetitionFrame extends AppJFrameAddToBase{
    String id;
    String place;
    static ArrayList<AddField> makeList(String id){
        ArrayList<AddField> list = new ArrayList<>();
        //list.add(new AddField("Data", "data", 'e'));
        //list.add(new AddField("Termin zgloszen", "termin_zgloszen", 'e'));
        String select = "SELECT id_zaw, imie || ' ' || nazwisko as nazwa FROM zawodnik where id_rep = " + id + " ORDER BY nazwa;";
        list.add(new AddField("zawodnik", "id_zaw", select));
        list.add(new AddField("id_kon", id));
        //list.add(new AddField("Kwota bazowa", "kwota_bazowa", 'i'));

        //list.add(new AddField("kwota_bazowa", 'i'));
        return list;
    }
    AddCompetitorToCompetitionFrame(String id, String place){
        super(makeList(id), "zgloszenie");
        this.id = id;
        this.place = place;
    }

}
