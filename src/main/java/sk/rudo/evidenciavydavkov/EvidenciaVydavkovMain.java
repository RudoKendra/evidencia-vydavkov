package sk.rudo.evidenciavydavkov;

import sk.rudo.evidenciavydavkov.model.Kategoria;
import sk.rudo.evidenciavydavkov.model.Vydavok;
import sk.rudo.evidenciavydavkov.service.NakladService;
import sk.rudo.evidenciavydavkov.view.GUI;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EvidenciaVydavkovMain {
    public static void main(String[] args) {
        Vydavok vydavok1 = new Vydavok("Benzin", 45.0, Kategoria.PHM, LocalDate.now());

        ArrayList<Vydavok> naklady = new ArrayList<>();
        naklady.add(vydavok1);

        NakladService nakladService = new NakladService();

//            try {
//                nakladService.ulozDoSuboru(naklady, "naklady.ser");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

//        try {
//            ArrayList<Vydavok> nakladyZoSuboru = nakladService.otvorZoSuboru("naklady.ser");
//            for (Vydavok konkretnyVydavok:nakladyZoSuboru) {
//                System.out.println(konkretnyVydavok);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
        GUI gui = new GUI();
        gui.vytvor();
    }
}
