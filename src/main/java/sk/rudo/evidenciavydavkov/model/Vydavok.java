package sk.rudo.evidenciavydavkov.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Vydavok implements Serializable {
    private String nazov;
    private double cena;
    private Kategoria kategoria;
    private LocalDate datumZaznamu;

    public Vydavok() {
    }

    public Vydavok(String nazov, double cena, Kategoria kategoria, LocalDate datumZaznamu) {
        this.nazov = nazov;
        this.cena = cena;
        this.kategoria = kategoria;
        this.datumZaznamu = datumZaznamu;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    public LocalDate getDatumZaznamu() {
        return datumZaznamu;
    }

    public void setDatumZaznamu(LocalDate datumZaznamu) {
        this.datumZaznamu = datumZaznamu;
    }

    @Override
    public String toString() {
        return "Vydavok{" +
                "nazov='" + nazov + '\'' +
                ", cena=" + cena +
                ", kategoria=" + kategoria +
                ", datumZaznamu=" + datumZaznamu +
                '}';
    }
}
