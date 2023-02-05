package net.codejava.swing.jbutton;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;

public class CompetitionListFrame extends AppJFrame{
    @Override
    void prepareToShow() {
        setSize(500, 500);
        setTitle("Konkursy");

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("data");
        columnNames.add("miejsce");
        columnNames.add("termin zgloszen");
        columnNames.add("status");
        columnNames.add("id");
        String select = "select data, miejsce, termin_zgloszen, status, id_kon from konkurs;";
        AppJTableFromSelect table = new AppJTableFromSelect(select, columnNames, c);

        JButton BAdd = new JButton("Dodaj");
        BAdd.addActionListener(e -> {
            CompetitionAddFrame t2 = new CompetitionAddFrame();
            this.startSubframe(t2, e1 -> {
                System.out.println("organizer action");
                table.refreshData();
                SwingUtilities.updateComponentTreeUI(me);
                me.invalidate();
                me.validate();
                me.repaint();
            });
        });
        TableColumnModel tableColumnModel = table.getComponent().getColumnModel();
        tableColumnModel.removeColumn(tableColumnModel.getColumn(4));
        table.getComponent().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jPanel.add(table.getComponent().getTableHeader());
        jPanel.add(table.getComponent());
        jPanel.add(BAdd);


        JButton BAddNotStarted = new JButton("Zmien Konkurs");
        BAddNotStarted.addActionListener(e -> {
            if(table.getComponent().getSelectedRow() < 0){
                return;
            }
            String id = table.getValues().get(table.getComponent().getSelectedRow()).get(4);
            String place = table.getValues().get(table.getComponent().getSelectedRow()).get(1);
            ModifyCompetitionFrame t2 = new ModifyCompetitionFrame(id, place);
            this.startSubframe(t2, e1 -> {
                System.out.println("organizer action");
                table.refreshData();
                SwingUtilities.updateComponentTreeUI(me);
                me.invalidate();
                me.validate();
                me.repaint();
            });
        });
        jPanel.add(BAddNotStarted);


        JButton BEnterResults = new JButton("Dodaj wynik");
        BEnterResults.addActionListener(e -> {
            CompetitionAddFrame t2 = new CompetitionAddFrame();
            this.startSubframe(t2, e1 -> {
                System.out.println("organizer action");
            });
        });
        jPanel.add(BEnterResults);


        JButton BWatchResults = new JButton("Ogladaj wyniki");
        BWatchResults.addActionListener(e -> {
            WatchCompetitionResultsFrame t2 = new WatchCompetitionResultsFrame();
            this.startSubframe(t2, e1 -> {
                System.out.println("organizer action");
            });
        });
        jPanel.add(BWatchResults);


        add(jPanel);


    }
}
