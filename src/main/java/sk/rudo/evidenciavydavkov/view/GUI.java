package sk.rudo.evidenciavydavkov.view;

import com.toedter.calendar.JDateChooser;
import sk.rudo.evidenciavydavkov.model.Kategoria;
import sk.rudo.evidenciavydavkov.model.Vydavok;
import sk.rudo.evidenciavydavkov.service.NakladService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;


public class GUI {
    private Vydavok vybranyVydavok;
    private DefaultListModel<Vydavok> modelZoznamu;
    private JList<Vydavok> jlist;
    private ArrayList<Vydavok> celkoveNaklady;

    public void vytvor() {

        JFrame frame = new JFrame("Evidencia nákladov"); // vytvorime okno

        frame.setMinimumSize(new Dimension(465, 340));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ked kliknem na X na okne (cize vo frame) tak sa zatvori standardne
        frame.setLocationRelativeTo(null); // vycentrovanie okna

        // JFrame by mal obsahovat panel, teda container JPanel
        JPanel panel = new JPanel();
        panel.setLayout(null); // layout pre panel bude null, cize prazdny
        frame.setContentPane(panel); // my dany panel pridame do frame

        // Vytvorenie menu baru (menu bar)
        JMenuBar menuBar = new JMenuBar();
        // Vytvorenie menu
        JMenu menu = new JMenu("Menu");

        // Vytvorenie položky v menu
        JMenuItem item1 = new JMenuItem("Otvor");
        JMenuItem item2 = new JMenuItem("Ulož");
        JMenuItem item3 = new JMenuItem("Zatvor");

        // Pridanie položiek do menu
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);

        // Pridanie menu do menu baru
        menuBar.add(menu);

        // Pridanie menu baru do rámca
        frame.setJMenuBar(menuBar);

        // ActionListener pre položku 1
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Musime vytvorit service pre naklad
                NakladService nakladService = new NakladService();
                try {
                    celkoveNaklady = nakladService.otvorZoSuboru("naklady.ser");
                    modelZoznamu.clear(); // Vymazeme doterajsi model a nacitame don nove udaje
                    for (Vydavok konkretnyVydavok : celkoveNaklady) {
                        // Naplnenie modelu zoznamu objektmi z ArrayListu
                        System.out.println(konkretnyVydavok);
                        modelZoznamu.addElement(konkretnyVydavok);
                    }
                    jlist.updateUI(); // Aktualizacia jList
                } catch (IOException e1) {
                    throw new RuntimeException(e1);
                } catch (ClassNotFoundException e2) {
                    throw new RuntimeException(e2);
                }
            }
        });


        // ActionListener pre položku 2
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tu môžete definovať, čo sa má stať po kliknutí na položku 1
                // Musime vytvorit service pre naklad
                NakladService nakladService = new NakladService();

                // Serializacia
                try {
                    nakladService.ulozDoSuboru(celkoveNaklady, "naklady.ser");
                } catch (IOException e1) {
                    throw new RuntimeException(e1);
                }
            }
        });

        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });


        // JText
        JTextField txfNazov = new JTextField();
        txfNazov.setBounds(10, 20, 100, 30); // x, y, sirka, vyska
        txfNazov.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(txfNazov);

        JTextField txfCena = new JTextField();
        txfCena.setBounds(120, 20, 100, 30); // x, y, sirka, vyska
        txfCena.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(txfCena);

        // JText
//        String[] kategoria = {"POTRAVINY", "DROGERIA", "PHM", "ZABAVA" , "SERVIS", "INE"};
        JComboBox txfKategoria = new JComboBox(Kategoria.values());
//        JTextField txfKategoria = new JTextField();
        txfKategoria.setBounds(230, 20, 100, 30); // x, y, sirka, vyska
//        txfKategoria.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(txfKategoria);

        JTextField txfDatum = new JTextField();
        txfDatum.setBounds(340, 20, 100, 30); // x, y, sirka, vyska
        txfDatum.setHorizontalAlignment(JTextField.RIGHT);
        panel.add(txfDatum);


        JLabel labelText = new JLabel(""); // popisok
        labelText.setBounds(100, 52, 230, 20); // x, y, sirka, vyska
        panel.add(labelText);


        // Vytvorenie modelu zoznamu
        modelZoznamu = new DefaultListModel<>();

        // Vytvorenie JList s modelom zoznamu
        jlist = new JList<>(modelZoznamu);
        jlist.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    vybranyVydavok = jlist.getSelectedValue();
                    txfNazov.setText(vybranyVydavok.getNazov());
                    txfCena.setText(String.valueOf(vybranyVydavok.getCena()));
                    txfKategoria.setSelectedItem((Kategoria) vybranyVydavok.getKategoria());
                    txfDatum.setText(vybranyVydavok.getDatumZaznamu().toString());
                    System.out.println(vybranyVydavok);
                }
            }
        });

        JScrollPane jScrollPane = new JScrollPane(jlist);
        jScrollPane.setBounds(70, 80, 300, 150);
        panel.add(jScrollPane);


        // VYTVORENIE NOVEHO VYDAVKU, NOVY DIALOG
        JButton btnNovy = new JButton("Nový");
        btnNovy.setBounds(50, 240, 80, 20);
        panel.add(btnNovy);
        btnNovy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame();
                JDialog dialog = new JDialog(frame1,"Zadaj nový výdavok",true);
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                panel.setBorder(BorderFactory.createTitledBorder("Nový výdavok"));
                JLabel labelText = new JLabel("Názov");
                panel.add(labelText);
                JTextField textfield = new JTextField(10);
//                textfield.setText(txfNazov.getText()); --- na ziskanie do textfieldu, pre upravu
                panel.add(textfield);
                JLabel labelText1 = new JLabel("Cena");
                panel.add(labelText1);
                JTextField textfield1 = new JTextField(10);
                panel.add(textfield1);
                JLabel labelText2 = new JLabel("Kategória");
                panel.add(labelText2);
//                String[] kategoria = {"POTRAVINY", "DROGERIA", "PHM", "ZABAVA" , "SERVIS", "INE"};
                JComboBox combobox = new JComboBox(Kategoria.values());
                panel.add(combobox);
                JLabel labelText3 = new JLabel("Dátum");
                panel.add(labelText3);
                JDateChooser jDateChooser = new JDateChooser();
                jDateChooser.setPreferredSize(new Dimension(100,20));
                panel.add(jDateChooser);


                JButton btnUloz = new JButton("Ulož");
                panel.add(btnUloz);

                btnUloz.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String datum =  new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser.getDate());
                        Vydavok novyVydavok = new Vydavok(textfield.getText(),Double.parseDouble(textfield1.getText()),(Kategoria) combobox.getSelectedItem(),LocalDate.parse(datum));
                        modelZoznamu.addElement(novyVydavok);
                        celkoveNaklady.add(novyVydavok);
                        jlist.updateUI();
                        frame1.dispatchEvent(new WindowEvent(frame1, WindowEvent.WINDOW_CLOSING));
                    }
                });

                JButton btnCancel = new JButton("Cancel");
                panel.add(btnCancel);

                btnCancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame1.dispatchEvent(new WindowEvent(frame1, WindowEvent.WINDOW_CLOSING));
                    }
                });

                dialog.add(panel);
                dialog.setSize(700, 150);
                dialog.setLocationRelativeTo(null);
                dialog.setResizable(false);
                dialog.setLocationRelativeTo(frame1);
                dialog.setVisible(true);

                txfNazov.setText("");
                txfCena.setText("");
                txfKategoria.setSelectedItem("");
                txfDatum.setText("");
            }
        });

        JButton btnPotvrd = new JButton("Ulož zmenu"); // tlacidlo
        btnPotvrd.setBounds(175, 240, 100, 20); // x, y, sirka, vyska
        panel.add(btnPotvrd);

        btnPotvrd.addActionListener(new ActionListener() { // sluzi na "odchytenie" cize spracovanie zatlacenia tlacidla
            public void actionPerformed(ActionEvent e) {
                vybranyVydavok.setNazov(txfNazov.getText());
                vybranyVydavok.setCena(Double.parseDouble(txfCena.getText()));
                vybranyVydavok.setKategoria(Kategoria.valueOf(txfKategoria.getSelectedItem().toString()));
                vybranyVydavok.setDatumZaznamu(LocalDate.parse(txfDatum.getText()));
                jlist.updateUI();
            }
        });


        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(320, 240, 80, 20);
        panel.add(btnCancel);

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });


        // display it
        frame.pack();
        frame.setVisible(true);
    }
}