package com.company;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    private static int zadanie,dzien;
    private static String notatka;
    private static Scanner scan = new Scanner(System.in);
    private static Kalendarz kalendarz=new Kalendarz();
    private static LocalTime czasRozpoczecia,czasZakonczenia;
    public static void main(String[] args) {
        do {
            rozpoczecieProgramu();
            try {
                switch (zadanie) {
                    case 1: {       //dodanie zadania
                        trescZadan();
                        kalendarz.dodaj(dzien, czasRozpoczecia, czasZakonczenia,notatka,jakiPriorytet(),jakiStanRealizacji());
                        zakonczenieProgramu();
                        break; }
                    case 2: {       //dodanie spotkania
                        trescSpotkan();
                        kalendarz.dodaj(dzien, czasRozpoczecia, czasZakonczenia,notatka, jakiStatus());
                        zakonczenieProgramu();
                        break; }
                    case 3:         //wyświetlanie wszystkiego
                    {   wypisanie(arrayListaZDnia(dzien),(KlasowyInterface wydarzenie)->true);
                        break; }
                    case 4:         //wyświetlanie zadań
                    {   wypisanie(arrayListaZDnia(dzien),(KlasowyInterface wydarzenie)->wydarzenie instanceof Zadanie);
                        break; }
                    case 5:         //wyświetlanie spotkań
                    {   wypisanie(arrayListaZDnia(dzien),(KlasowyInterface wydarzenie)->wydarzenie instanceof Spotkanie);
                        break;}
                    case 6:          //wyświetlanie zadań z wypisaniem priorytetu i stanu realizacji
                    {
                        wypisanie(arrayListaZDnia(dzien),(KlasowyInterface wydarzenie)-> wydarzenie instanceof Zadanie);
                        break;}
                    case 7:          //wyświetlanie spotkań po statusie
                    {   Spotkanie.Status status = jakiStatus();
                        wypisanie(arrayListaZDnia(dzien),(KlasowyInterface wydarzenie)-> wydarzenie instanceof Spotkanie&& ((Spotkanie) wydarzenie).statusChecker(status));
                        break;}
                    case 8: System.out.println("Miłego dnia!");
                }
            }catch (Exception e){System.out.println("Błędne dane spróbuj ponownie!");}
        } while (zadanie != 8);
    }
    private static void rozpoczecieProgramu() {
        System.out.println("Jaką operację chcesz wykonać?");
        System.out.println("Dla dodania zadania na dzień wpisz 1.");
        System.out.println("Dla dodania spotkania na dzień wpisz 2.");
        System.out.println("Dla wyświetlenia zadań i spotkań na wybrany dzień wpisz 3.");
        System.out.println("Dla wyświetlenia zadań na wybrany dzień wpisz 4.");
        System.out.println("Dla wyświetlenia spotkań na wybrany dzień wpisz 5.");
        System.out.println("Dla wyświetlenia zadań z wypisanym priorytetem i stanem realizacji na wybrany dzień wpisz 6.");
        System.out.println("Dla wyświetlenia spotkań z wybranym stanem na wybrany dzień wpisz 7.");
        System.out.println("Dla zakończenia programu wpisz 8.");
        Scanner scan2 = new Scanner(System.in);
        try {
            zadanie = scan2.nextInt();
            if (zadanie > 0 && zadanie < 8) {
                System.out.print("Jakim dniem chcesz się zajmować? Dla pon wpisz 0, ");
                System.out.println("dla wt 1, dla sr 2, dla cz 3, dla pt 4, dla sob 5, a dla nd 6 ");
                dzien = scan2.nextInt();
                if (dzien < 0 || dzien > 6) {
                    System.out.println("Błędne dane spróbuj ponownie!");
                    rozpoczecieProgramu();
                }
            } else if(zadanie>8){
                System.out.println("Błędne dane spróbuj ponownie!");
                rozpoczecieProgramu();
            }
        }catch (Exception e){
            System.out.println("Błędne dane spróbuj ponownie!");
            rozpoczecieProgramu();
        }
    }

    private static ArrayList<KlasowyInterface>arrayListaZDnia(int dzien)
    {
        ArrayList<KlasowyInterface> wypisanie=kalendarz.wypisanie(dzien);
        return wypisanie;
    }
    private static void wypisanie(ArrayList<KlasowyInterface> wypisanie,Predicate <KlasowyInterface>wartosci )
    {
        int licznik=1,sprawdzenie=0;
            for (KlasowyInterface wydarzenie : wypisanie) {
                if (wartosci.test(wydarzenie)) {
                    if(zadanie==7||zadanie==6) {
                        System.out.println(licznik++ + ".: " + wydarzenie);
                        sprawdzenie++;
                    }
                    else {
                        sprawdzenie++;
                        System.out.println(licznik++ + ".: " +"Treść spotkania: "+wydarzenie.getTresc()+"\nCzas rozpoczęcia: "+wydarzenie.getCzasRozpoczecia()+"\nCzas zakończenia: "+wydarzenie.getCzasZakonczenia());
                    }
                }
                sprawdzenie++;
                if(!wartosci.test(wydarzenie)&&sprawdzenie==wypisanie.size()&&licznik==1&&sprawdzenie!=1)System.out.println("Brak pasujących danych!");
            }
        if(licznik==1&&sprawdzenie==0||sprawdzenie==1)System.out.println("Brak pasujących danych!");
        zakonczenieProgramu();
    }
    private static void zakonczenieProgramu()
    {
        try {
            System.out.println("Czy chcesz zakończyć program? Dla TAK wpisz 1 dla NIE dowolą liczbę");
            int koniec = scan.nextInt();
            if (koniec == 1) {
                zadanie = 8;
                System.out.println("Miłego dnia!");
            }
        }catch (Exception e){
            System.out.println("Musisz wpisać liczbę!");
            zakonczenieProgramu();
        }
    }
    private static void trescZadan()
    {
        Scanner scan1 = new Scanner(System.in);
        boolean dziala;
        do {
            try {
                System.out.println("Kiedy chcesz zacząć zadanie na ten dzień?(HH:MM) ");
                czasRozpoczecia = LocalTime.parse(scan1.nextLine());
                System.out.println("Podaj nazwe zadania");
                notatka = scan1.nextLine();
                System.out.println("Kiedy skończysz to zadanie?(HH:MM)");
                czasZakonczenia = LocalTime.parse(scan1.nextLine());
                if (czasRozpoczecia.isAfter(czasZakonczenia)){
                    System.out.println("Błędne dane, nie da się zakończyć szybciej niż się rozpoczęło!\nSpróbuj ponownie!");
                    trescZadan();
                }
                dziala=false;
            } catch (Exception e) {
                System.out.println("Niepoprawne dane spróbuj ponownie!");
                dziala=true;
            }
        }while(dziala);
    }
    private static void trescSpotkan()
    {
        Scanner scan1 = new Scanner(System.in);
        boolean dziala;
        do {
            try {
                System.out.println("Kiedy chcesz zacząć spotkanie na ten dzień?(HH:MM) ");
                czasRozpoczecia = LocalTime.parse(scan1.nextLine());
                System.out.println("Podaj nazwe spotkania");
                notatka = scan1.nextLine();
                System.out.println("Kiedy skończysz to spotkanie?(HH:MM)");
                czasZakonczenia = LocalTime.parse(scan1.nextLine());
                if (czasRozpoczecia.isAfter(czasZakonczenia)){
                    System.out.println("Błędne dane, nie da się zakończyć szybciej niż się rozpoczęło!\nSpróbuj ponownie!");
                    trescSpotkan();
                }
                dziala=false;
            } catch (Exception e) {
                System.out.println("Niepoprawne dane spróbuj ponownie!");
                dziala=true;
            }
        }while(dziala);
    }
    private static Zadanie.StanRealizacji jakiStanRealizacji(){
        System.out.println("Wybierz stan realizacji (1 dla Wolny, 2 dla W toku, 3 dla Wykonane)");
        int stan= scan.nextInt();
        switch (stan){
            case 1:
                return Zadanie.StanRealizacji.Wolne;
            case 2:
                return Zadanie.StanRealizacji.WToku;
            case 3:
                return Zadanie.StanRealizacji.Wykonane;
            default:
                System.out.println("Błędne dane spróbuj ponownie!");
                jakiStanRealizacji();
        }return null;
    }
    private static Zadanie.Priorytet jakiPriorytet(){
        System.out.println("Wybierz priorytet (1 dla Niski, 2 dla Normalny, 3 dla Wysoki)");
        int stan= scan.nextInt();
            switch (stan) {
                case 1:
                    return Zadanie.Priorytet.Niski;
                case 2:
                    return Zadanie.Priorytet.Normalny;
                case 3:
                    return Zadanie.Priorytet.Wysoki;
                default:
                    System.out.println("Błędne dane spróbuj ponownie!");
                    jakiPriorytet();
            }
            return null;
    }
    private static Spotkanie.Status jakiStatus(){
        System.out.println("Wybierz status (1 dla planowane, 2 dla potwierdzone)");
        int stan= scan.nextInt();
        switch (stan){
            case 1:
                return Spotkanie.Status.Planowane;
            case 2:
                return Spotkanie.Status.Potwierdzone;
            default:
                System.out.println("Błędne dane spróbuj ponownie!");
                jakiStatus();
        }return null;
    }
}
