package com.company;

import java.time.LocalTime;

public class Spotkanie implements KlasowyInterface{
    private LocalTime czasRozpoczecia;
    private LocalTime czasZakonczenia;
    private String tresc;
    private Status status;

    public Spotkanie(LocalTime czasRozpoczecia,String tresc,LocalTime czasZakonczenia,Status status)
    {
        this.czasRozpoczecia=czasRozpoczecia;
        this.tresc=tresc;
        this.czasZakonczenia=czasZakonczenia;
        this.status=status;
    }
    public enum Status {
        Planowane,
        Potwierdzone
    }
    boolean statusChecker(Status status){
        return this.status.equals(status);
    }
    public String toString()
    {
        return "Treść spotkania: "+tresc+"\nCzas rozpoczęcia: "+czasRozpoczecia+"\nCzas zakończenia: "+czasZakonczenia+"\nStatus: "+status+"\n";
    }
    public LocalTime getCzasRozpoczecia()
    {
        return czasRozpoczecia;
    }
    public LocalTime getCzasZakonczenia()
    {
        return czasZakonczenia;
    }
    public String getTresc()
    {
        return tresc;
    }

}
