package com.company;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

class Kalendarz {

    private ArrayList<KlasowyInterface>[] kalendarz = new ArrayList[7];

    public Kalendarz()
    {
        for(int i=0;i<=6;i++)
        {
            kalendarz[i]=new ArrayList<>();
        }
    }

    public void dodaj(int dzien,LocalTime czasRozpoczecia,LocalTime czasZakonczenia,String notatka,Zadanie.Priorytet priorytet,Zadanie.StanRealizacji stanRealizacji) {
        kalendarz[dzien].add(new Zadanie(czasRozpoczecia,notatka,czasZakonczenia,priorytet,stanRealizacji));
    }
    public void dodaj(int dzien,LocalTime czasRozpoczecia,LocalTime czasZakonczenia,String notatka,Spotkanie.Status status) {
        kalendarz[dzien].add(new Spotkanie(czasRozpoczecia,notatka,czasZakonczenia,status));
    }
    public ArrayList<KlasowyInterface> wypisanie(int dzien) {
        ArrayList<KlasowyInterface>wypisanie  = kalendarz[dzien];
        Collections.sort(wypisanie, ( s1,  s2)->s1.getCzasRozpoczecia().compareTo(s2.getCzasRozpoczecia()));
        return kalendarz[dzien];
    }
}

