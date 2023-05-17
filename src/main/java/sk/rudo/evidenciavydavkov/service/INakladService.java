package sk.rudo.evidenciavydavkov.service;

import sk.rudo.evidenciavydavkov.model.Vydavok;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface INakladService {
    void uloz (Vydavok vydavok);
    void uprav (Vydavok vydavok);
    void zmaz (Vydavok vydavok);
    void zobraz (Vydavok vydavok);
    void zobrazVsetko (ArrayList<Vydavok> zoznamVydavkov);
    double spocitajVydavkyPodlaOdDo (LocalDate datumOd, LocalDate datumDo, ArrayList<Vydavok> zoznamVydavkov);
    void ulozDoSuboru (ArrayList<Vydavok> zoznamVydavkov, String nazovSuboru) throws IOException;
    ArrayList<Vydavok> otvorZoSuboru (String nazovSuboru) throws IOException, ClassNotFoundException;

}
