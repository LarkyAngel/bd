package net.codejava.swing.jbutton;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import static net.codejava.swing.jbutton.SkiJumping.addTextField;

public class FrameAddToBase {
    Connection c;
    ArrayList<JTextField> fields;
    FrameAddToBase(Connection c){
        this.c = c;
        fields = new ArrayList<>();
    }
    public void add(String FrameName, String tableName, ArrayList<AddField> fieldNames) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame(FrameName);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JPanel panel = new JPanel();

                panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.Y_AXIS));
                for (AddField af : fieldNames){
                    fields.add(addTextField(frame, panel, af.getFieldName()));
                }

                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                JButton button = new JButton("OK");
                inputpanel.add(button);
                panel.add(inputpanel);
                frame.getContentPane().add(BorderLayout.WEST, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);

                button.addActionListener(e -> {
                    try {
                        Statement stmt = c.createStatement();
                        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " VALUES (");

                        for (int i = 0 ; i < fieldNames.size(); i++){
                            StringBuilder pom = new StringBuilder();
                            if (fieldNames.get(i).getType() == 's' || fieldNames.get(i).getType() == 'e'){
                                pom.append("'" + fields.get(i).getText() + "'");
                            }
                            else{
                                pom.append(fields.get(i).getText());
                            }
                            if (i != fieldNames.size() - 1){
                                pom.append(", ");
                            }
                            else {
                                pom.append(");");
                            }
                            sql.append(pom);
                        }

                        System.out.println(sql);
                        stmt.executeUpdate(sql.toString());
                        stmt.close();
                        //c.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        System.err.println(e1.getClass().getName()+": "+e1.getMessage());
                        System.exit(0);
                    }
                    frame.dispose();
                });
            }
        });
    }
}
