package sk.rudo.evidenciavydavkov.service;

import sk.rudo.evidenciavydavkov.model.Vydavok;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class NakladService implements INakladService{
    @Override
    public void uloz(Vydavok vydavok) {

    }

    @Override
    public void uprav(Vydavok vydavok) {

    }

    @Override
    public void zmaz(Vydavok vydavok) {

    }

    @Override
    public void zobraz(Vydavok vydavok) {

    }

    @Override
    public void zobrazVsetko(ArrayList<Vydavok> zoznamVydavkov) {

    }

    @Override
    public double spocitajVydavkyPodlaOdDo(LocalDate datumOd, LocalDate datumDo, ArrayList<Vydavok> zoznamVydavkov) {
        return 0;
    }

    @Override
    public void ulozDoSuboru(ArrayList<Vydavok> zoznamVydavkov, String nazovSuboru) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(nazovSuboru); // vytvorime subor
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream); // vytvorime Object stream pre ukladanie objektov
        objectOutputStream.writeObject(zoznamVydavkov); // zapiseme objekt
        objectOutputStream.flush(); // realne uskutocnime operaciu zapisu
        objectOutputStream.close(); // zatvorime object output stream
        fileOutputStream.close(); // zatvorime file output stream, cize subor
    }

    @Override
    public ArrayList<Vydavok> otvorZoSuboru(String nazovSuboru) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(nazovSuboru);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<Vydavok> zoznamVydavkov = (ArrayList) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return zoznamVydavkov;
    }
}
