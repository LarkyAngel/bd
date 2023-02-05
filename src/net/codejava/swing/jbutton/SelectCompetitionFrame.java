package net.codejava.swing.jbutton;

import javax.swing.*;

public class SelectCompetitionFrame extends AppJFrame{

    @Override
    void prepareToShow() {
        setSize(500, 500);
        setTitle("Wybierz wyniki");

        JPanel jPanel = new JPanel();

        //TODO JList
        //WatchCompetitionResultsFrame


        add(jPanel);
    }
}
