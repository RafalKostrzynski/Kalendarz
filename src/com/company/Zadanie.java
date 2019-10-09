package com.company;
import java.time.LocalTime;

public class Zadanie implements KlasowyInterface{
    private LocalTime czasRozpoczecia;
    private LocalTime czasZakonczenia;
    private String tresc;
    private StanRealizacji stanRealizacji;
    private Priorytet priorytet;

    public Zadanie (LocalTime czasRozpoczecia,String tresc,LocalTime czasZakonczenia, Priorytet priorytet,StanRealizacji stanRealizacji )
    {
        this.czasRozpoczecia=czasRozpoczecia;
        this.tresc=tresc;
        this.czasZakonczenia=czasZakonczenia;
        this.priorytet=priorytet;
        this.stanRealizacji=stanRealizacji;
    }
    public enum StanRealizacji{
        Wolne,WToku,Wykonane
    }
    public enum Priorytet{
        Niski,Normalny,Wysoki
    }
    boolean priorytetChecker(Priorytet priorytet){
        return this.priorytet.equals(priorytet);
    }
    public String toString()
    {
        return "Treść zadania: "+tresc+"\nCzas rozpoczęcia: "+czasRozpoczecia+"\nCzas zakończenia: "+czasZakonczenia+"\nPriorytet: "+priorytet+"\nStan realizacji: "+stanRealizacji+"\n";
    }
    public LocalTime getCzasRozpoczecia() {
        return czasRozpoczecia;
    }

    public String getTresc()
    {
        return tresc;
    }

    public LocalTime getCzasZakonczenia()
    {
        return czasZakonczenia;
    }

}