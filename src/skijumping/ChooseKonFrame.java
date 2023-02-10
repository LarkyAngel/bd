package skijumping;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.Statement;

import static skijumping.SkiJumping.addTextField;
//import static net.codejava.swing.jbutton.SkiJumping.stan;

public class ChooseKonFrame {
    Connection c;
    ChooseKonFrame(Connection c){
        this.c = c;
    }
    public void runFrame(){
        JFrame frame1 = new JFrame("Wybierz konkurs");
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        JPanel panel1 = new JPanel();
        panel1.setLayout((LayoutManager) new BoxLayout(panel1, BoxLayout.Y_AXIS));

        JTextField id_kon = addTextField(frame1, panel1, "id_kon");

        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new FlowLayout());
        JButton button = new JButton("OK");
        inputpanel.add(button);
        panel1.add(inputpanel);
        frame1.getContentPane().add(BorderLayout.WEST, panel1);
        frame1.pack();
        frame1.setLocationByPlatform(true);
        frame1.setVisible(true);
        frame1.setLocationRelativeTo(null);
        button.addActionListener(e1 -> {//wydaje sie, ze stan nie zadziala, mysle, ze trzeba trzymac taka informacje w bazie danych w kolejnej kolumnie??
            if (!stan.containsKey(Integer.parseInt(id_kon.getText()))) {// konkursu nie ma w bazie
                FrameAddKon frameAddKon = new FrameAddKon(c);
                frameAddKon.dodajKonkurs();

                stan.put(Integer.parseInt(id_kon.getText()), 0);
            } else if (stan.get(Integer.parseInt(id_kon.getText())) == 0) {//konkurs jest, dodajemy zgloszenia
                AddEndZglFrame addEndZglFrame = new AddEndZglFrame(c, id_kon);
                addEndZglFrame.runFrame();

            } else if (stan.get(Integer.parseInt(id_kon.getText())) == 1) {//rejestracja zamknieta, dodajemy wyniki
                AddWynFrame addWynFrame = new AddWynFrame(c, id_kon);
                addWynFrame.runFrame();

            } else {//po konkursie, czytamy wyniki, nie analizowalem tej czesci kodu, trzeba zrobic jakas specjalna klase na to
                JFrame frame1111 = new JFrame("Odczytaj wynik");
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e11) {
                    e11.printStackTrace();
                }
                JPanel panel111 = new JPanel();
                panel111.setLayout((LayoutManager) new BoxLayout(panel111, BoxLayout.Y_AXIS));

                JTextField nr_serii = addTextField(frame1111, panel111, "nr_serii");
                JTextField id_kon11 = addTextField(frame1111, panel111, "id_kon");
                JTextField id_zaw = addTextField(frame1111, panel111, "id_zaw");

                JPanel inputpanel111 = new JPanel();
                inputpanel111.setLayout(new FlowLayout());
                JButton button111 = new JButton("OK");
                inputpanel111.add(button111);
                panel111.add(inputpanel111);
                frame1111.getContentPane().add(BorderLayout.WEST, panel111);
                frame1111.pack();
                frame1111.setLocationByPlatform(true);
                frame1111.setVisible(true);
                frame1111.setLocationRelativeTo(null);
                button111.addActionListener(e1111 -> {
                    try {
                        Statement stmt = c.createStatement();
                        String sql = "SELECT * FROM Wynik WHERE nr_serii = " + nr_serii.getText() +
                                " AND id_kon = " + id_kon11.getText() + " AND id_zaw = " + id_zaw.getText();
                        stmt.executeUpdate(sql);
                        stmt.close();
                        c.close();
                    } catch (Exception e11) {
                        e11.printStackTrace();
                        System.err.println(e11.getClass().getName()+": "+e11.getMessage());

                    }
                });
                frame1111.setVisible(true);
                frame1111.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1111.setLocationRelativeTo(null);
            }
        });
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLocationRelativeTo(null);
    }
}
