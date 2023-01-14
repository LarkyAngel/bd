package net.codejava.swing.jbutton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

public class SkiJumping extends JFrame implements ActionListener {
	static Connection c = null;
	static Map<Integer, Integer> stan = new HashMap<>();

	public SkiJumping() {
		super("Konkurs skoków narciarskich w ramach Pucharu Świata");
		setLayout(new FlowLayout());
        
        JLayeredPane layers = new JLayeredPane();
        layers.setLayout(new FlowLayout());
        
        JPanel layerOne = new JPanel();
        layerOne.add(new JLabel("Zaloguj jako:"));
        layers.add(layerOne, Integer.valueOf(2));
        
        JPanel layerTwo = new JPanel();
        JButton button1 = new JButton("Organizator");
        layerTwo.add(button1);
        JButton button2 = new JButton("Kibic");
        layerTwo.add(button2);
        layers.add(layerTwo, Integer.valueOf(1));
        
        button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				boolean ok = loginPanel();
				if (ok) organizer();
				else buttonActionPerformed(evt);
			}
		});
        
        button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
        
        add(layers);
        
        setSize(300, 100);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private String[] frameGetText(int n, String nazwa) {
		return new String[]{"aaa"};
	}
	
	String passwd = "aaa";
	private boolean loginPanel() {
     	String[] s = frameGetText(1, "Zaloguj");
     	return s[0].equals(passwd);
    }
	
	public static void dodajReprezentację() {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	JFrame frame = new JFrame("Dodaj reprezentację");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JPanel panel = new JPanel();
                panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.Y_AXIS));
            	
                JTextField id_zaw = addTextField(frame, panel, "nazwa_rep");
                JTextField nazwa_rep = addTextField(frame, panel, "kwota_bazowa");
                JTextField id_kon = addTextField(frame, panel, "id_kon");
            	
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
                        String sql = "INSERT INTO Zawodnik " +
                           "VALUES (" + id_zaw.getText() + ", '" + nazwa_rep.getText() + "', " 
                        		+ id_kon.getText() + ");";
                        stmt.executeUpdate(sql);
                        stmt.close();
                        c.close();
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
	
	public static void dodajZawodnika() {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	JFrame frame = new JFrame("Dodaj zawodnika");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JPanel panel = new JPanel();
                panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.Y_AXIS));
            	
                JTextField id_zaw = addTextField(frame, panel, "id_zaw");
                JTextField nazwa_rep = addTextField(frame, panel, "nazwa_rep");
                JTextField imie = addTextField(frame, panel, "imie");
                JTextField nazwisko = addTextField(frame, panel, "nazwisko");
            	
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
                        String sql = "INSERT INTO Zawodnik " +
                           "VALUES (" + id_zaw.getText() + ", '" + nazwa_rep.getText() + "', '" 
                        		+ imie.getText() + "', '" + nazwisko.getText() + "');";
                        stmt.executeUpdate(sql);
                        stmt.close();
                        c.close();
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
	
	public static void organizer() {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	JFrame frame = new JFrame("Organizator");
            	frame.setLayout(new FlowLayout());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                JPanel panel = new JPanel();
            	JButton button1 = new JButton("Dodaj reprezentację");
            	frame.add(button1);
            	button1.addActionListener(new ActionListener() {
            		@Override
        			public void actionPerformed(ActionEvent e) {
        				dodajReprezentację();
        			}
            	});
            	
            	JButton button2 = new JButton("Dodaj zawodnika");
            	frame.add(button2);
            	button2.addActionListener(new ActionListener() {
            		@Override
        			public void actionPerformed(ActionEvent e) {
        				dodajZawodnika();
        			}
            	});

            	JButton button3 = new JButton("Wybierz konkurs");
            	frame.add(button3);
            	button3.addActionListener(new ActionListener() {
            		@Override
        			public void actionPerformed(ActionEvent e) {
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
                        button.addActionListener(e1 -> {
                        	if (!stan.containsKey(Integer.parseInt(id_kon.getText()))) {
                        		JFrame frame11 = new JFrame("Utwórz konkurs");
                                frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                try {
                                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                                } catch (Exception e11) {
                                    e11.printStackTrace();
                                }
                                JPanel panel11 = new JPanel();
                                panel11.setLayout((LayoutManager) new BoxLayout(panel11, BoxLayout.Y_AXIS));
                            	
                                JTextField id_kon1 = addTextField(frame11, panel11, "id_kon");
                                JTextField data = addTextField(frame11, panel11, "data");
                                JTextField termin_zgloszen = addTextField(frame11, panel11, "termin_zgloszen");
                                JTextField nazwa_org = addTextField(frame11, panel11, "nazwa_org");
                                JTextField miejsce = addTextField(frame11, panel11, "miejsce");
                            	
                            	JPanel inputpanel1 = new JPanel();
                                inputpanel1.setLayout(new FlowLayout());
                                JButton button1 = new JButton("OK");
                                inputpanel1.add(button1);
                                panel11.add(inputpanel1);
                                frame11.getContentPane().add(BorderLayout.WEST, panel11);
                                frame11.pack();
                                frame11.setLocationByPlatform(true);
                                frame11.setVisible(true);
                                frame11.setLocationRelativeTo(null);
                                button1.addActionListener(e111 -> {
                                	try {       
                                        Statement stmt = c.createStatement();
                                        String sql = "INSERT INTO Konkurs " +
                                           "VALUES (" + id_kon1.getText() + ", '" + data.getText() + "', '" 
                                        		+ termin_zgloszen.getText() + "', '" + nazwa_org.getText() + "', '" +
                                           miejsce.getText() + "');";
                                        stmt.executeUpdate(sql);
                                        stmt.close();
                                        c.close();
                                        stmt = c.createStatement();
                                        sql = "INSERT INTO Seria " +
                                           "VALUES (1, " + id_kon1.getText() + ");";
                                        stmt.executeUpdate(sql);
                                        stmt.close();
                                        c.close();
                                        stmt = c.createStatement();
                                        sql = "INSERT INTO Seria " +
                                                "VALUES (2, " + id_kon1.getText() + ");";
                                        stmt.executeUpdate(sql);
                                        stmt.close();
                                        c.close();
                                    } catch (Exception e11) {
                                        e11.printStackTrace();
                                        System.err.println(e11.getClass().getName()+": "+e11.getMessage());
                                        System.exit(0);
                                    }
                                    frame11.dispose();
                                });	
                            	frame11.setVisible(true);
                        		frame11.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        		frame11.setLocationRelativeTo(null);
                        		stan.put(Integer.parseInt(id_kon.getText()), 0);
                        	} else if (stan.get(Integer.parseInt(id_kon.getText())) == 0) {
                        		JFrame frame11 = new JFrame("Utwórz konkurs");
                        		frame11.setLayout(new FlowLayout());
                                frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                JButton button11 = new JButton("Dodaj zgłoszenie");
                                frame11.add(button11);
                                JButton button21 = new JButton("Zakończ zgłaszanie");
                                frame11.add(button21);
                                button11.addActionListener(e111 -> {
                                	JFrame frame1111 = new JFrame("Dodaj zgłoszenie");
                                	try {
                                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                                    } catch (Exception e11) {
                                        e11.printStackTrace();
                                    }
                                    JPanel panel111 = new JPanel();
                                    panel111.setLayout((LayoutManager) new BoxLayout(panel111, BoxLayout.Y_AXIS));
                                	
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
                                            String sql = "INSERT INTO Zgloszenie " +
                                               "VALUES (" + id_zaw.getText() + ", " + id_kon11.getText() + ")";
                                            stmt.executeUpdate(sql);
                                            stmt.close();
                                            c.close();
                                        } catch (Exception e11) {
                                            e11.printStackTrace();
                                            System.err.println(e11.getClass().getName()+": "+e11.getMessage());
                                            System.exit(0);
                                        }
                                        frame1111.dispose();
                                    });	
                                	frame1111.setVisible(true);
                            		frame1111.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            		frame1111.setLocationRelativeTo(null);
                                });	
                                button21.addActionListener(e111 -> {
                                	try {       
                                        stan.put(Integer.parseInt(id_kon.getText()), 1);
                                    } catch (Exception e11) {
                                        e11.printStackTrace();
                                        System.err.println(e11.getClass().getName()+": "+e11.getMessage());
                                        System.exit(0);
                                    }
                                    frame11.dispose();
                                });
                                frame11.setSize(150, 100);
                            	frame11.setVisible(true);
                        		frame11.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        		frame11.setLocationRelativeTo(null);
                        	} else if (stan.get(Integer.parseInt(id_kon.getText())) == 1) {
                        		JFrame frame11 = new JFrame("Utwórz konkurs");
                        		frame11.setLayout(new FlowLayout());
                                frame11.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                JButton button11 = new JButton("Dodaj wynik");
                                frame11.add(button11);
                                JButton button21 = new JButton("Zakończ ustawianie");
                                frame11.add(button21);
                                button11.addActionListener(e111 -> {
                                	JFrame frame1111 = new JFrame("Dodaj wynik");
                                	try {
                                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                                    } catch (Exception e11) {
                                        e11.printStackTrace();
                                    }
                                    JPanel panel111 = new JPanel();
                                    panel111.setLayout((LayoutManager) new BoxLayout(panel111, BoxLayout.Y_AXIS));
                                	
                                    JTextField id_serii = addTextField(frame1111, panel111, "id_serii");
                                    JTextField id_kon11 = addTextField(frame1111, panel111, "id_kon");
                                    JTextField id_zaw = addTextField(frame1111, panel111, "id_zaw");
                                    JTextField punkty = addTextField(frame1111, panel111, "punkty");
                                    JTextField odleglosc = addTextField(frame1111, panel111, "odleglosc");
                                	
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
                                            String sql = "INSERT INTO Wynik " +
                                            	"VALUES (" + id_serii.getText() + ", " + id_kon11.getText() + ", " + 
                                            		id_zaw.getText() + ", " + punkty.getText()
                                            	 + ", " + odleglosc.getText() + ")";
                                            stmt.executeUpdate(sql);
                                            stmt.close();
                                            c.close();
                                        } catch (Exception e11) {
                                            e11.printStackTrace();
                                            System.err.println(e11.getClass().getName()+": "+e11.getMessage());
                                            System.exit(0);
                                        }
                                        frame1111.dispose();
                                    });	
                                	frame1111.setVisible(true);
                            		frame1111.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            		frame1111.setLocationRelativeTo(null);
                                });	
                                button21.addActionListener(e111 -> {
                                	try {       
                                        stan.put(Integer.parseInt(id_kon.getText()), 2);
                                    } catch (Exception e11) {
                                        e11.printStackTrace();
                                        System.err.println(e11.getClass().getName()+": "+e11.getMessage());
                                        System.exit(0);
                                    }
                                    frame11.dispose();
                                });
                                frame11.setSize(150, 100);
                            	frame11.setVisible(true);
                        		frame11.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        		frame11.setLocationRelativeTo(null);
                        	} else {
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
                                        System.exit(0);
                                    }
                                    frame1111.dispose();
                                });	
                            	frame1111.setVisible(true);
                        		frame1111.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        		frame1111.setLocationRelativeTo(null);
                        	}
                        	frame1.dispose();
                        });
                    	frame1.setVisible(true);
                		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                		frame1.setLocationRelativeTo(null);
            		}
            	});
            	frame.setSize(300, 100);	
            	frame.setVisible(true);
        		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		frame.setLocationRelativeTo(null);
            }
        });
	}
	
	public static JTextField addTextField(JFrame frame, JPanel panel, String s) {
        panel.add(new JLabel(s), BorderLayout.EAST);
        panel.setOpaque(true);
        JTextArea textArea = new JTextArea(15, 50);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(Font.getFont(Font.SANS_SERIF));
        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new FlowLayout());
        JTextField input = new JTextField(20);
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        inputpanel.add(input);
        panel.add(inputpanel);
        input.requestFocus();
		return input;
	}
	
	private void buttonActionPerformed(ActionEvent evt) {
		JOptionPane.showMessageDialog(SkiJumping.this, "Niepoprawne hasło!");		
	}
	
	public static void main(String[] args) throws SQLException, IOException {
        try {
			c = DriverManager
			   .getConnection("jdbc:postgresql://localhost/postgres",
			   "postgres", "iks");
			File f = new File("/src/tabele.sql");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SkiJumping().setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}