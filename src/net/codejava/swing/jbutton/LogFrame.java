package net.codejava.swing.jbutton;

import javax.swing.*;

import static net.codejava.swing.jbutton.SkiJumping.addTextField;

public class LogFrame extends AppJFrame{
    @Override
    void prepareToShow() {
        setSize(500, 500);
        setTitle("Logowanie");

        JPanel jPanel = new JPanel();
        JTextField jTextField = addTextField(null, jPanel, "Haslo: ");
        jPanel.add(jTextField);

        JButton BCheck = new JButton("Ok");
        BCheck.addActionListener(e -> { //jak na razie dopuszcza kazde haslo
            OrganizerActionFrame t2 = new OrganizerActionFrame();
            this.startSubframe(t2, e1 -> {
                System.out.println("organizer action");
            });
        });

        jPanel.add(BCheck);
        add(jPanel);
    }
}
