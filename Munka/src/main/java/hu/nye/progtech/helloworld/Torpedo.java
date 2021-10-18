package hu.nye.progtech.helloworld;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Torpedo {

    public static void main(String[] args) {
        int TablaHossza = 6;
        char viz = '-';
        char hajo = 'H';
        char talalat = '+';
        char melle = 'x';
        int hajokSzama = 3;


        char [][] Tabla = createTabla(TablaHossza, viz, hajo, hajokSzama);
        printTabla(Tabla, viz, hajo);

        //Játék logika
        int undetectedhajokSzama = hajokSzama;
        while (undetectedhajokSzama > 0) {
            int [] loveskoordinata = getJatekosKoordinata(TablaHossza);
            char frissHely = evaluateLovesesTalalat(loveskoordinata, Tabla, hajo, viz, talalat, melle);
            if (frissHely == talalat) {
                undetectedhajokSzama--;
            }
            frissTabla(Tabla, loveskoordinata, frissHely);
            printTabla(Tabla, viz, hajo);
        }
        System.out.println("Nyertel!");

    }

    private static char[][] frissTabla(char[][] tabla, int[] loveskoordinata, char frissHely) {
        int sor = loveskoordinata[0];
        int oszlop = loveskoordinata[1];
        tabla[sor][oszlop] = frissHely;
        return tabla;
    }

    private static char evaluateLovesesTalalat(int[] loveskoordinata, char[][] tabla, char hajo, char viz, char talalat, char melle) {
        String uzenet;
        int sor = loveskoordinata[0];
        int oszlop = loveskoordinata[1];
        char celpont = tabla[sor][oszlop];
        if (celpont ==hajo) {
            uzenet = "Talalat!";
            celpont = talalat;
        }else if (celpont == viz) {
            uzenet = "Nem talalt";
            celpont = melle;
        }else {
            uzenet = "Mar megvolt!";
        }
        System.out.println(uzenet);
        return celpont;
    }

    private static int[] getJatekosKoordinata(int TablaHossza) {
        int sor;
        int oszlop;

            do {
                System.out.println("Sor: ");
                sor = new Scanner(System.in).nextInt();
            } while (sor < 0 || sor > TablaHossza + 1);
            do {
                System.out.println("Oszlop: ");
                oszlop = new Scanner(System.in).nextInt();
            } while (oszlop < 0 || oszlop > TablaHossza + 1);
            return new int[]{sor - 1, oszlop - 1};

    }
        //Játék logika vége

    private static void printTabla(char[][] Tabla, char viz, char hajo) {
        int TablaHossza = Tabla.length;
        System.out.print("  ");
        for (int i = 0; i < TablaHossza; i++){
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for(int sor = 0; sor < TablaHossza; sor++){
            System.out.print(sor + 1 + " ");
            for (int oszlop = 0; oszlop < TablaHossza; oszlop++){
                char pozicio = Tabla[sor][oszlop];
                if (pozicio == hajo) {
                    System.out.print(viz + " ");
                }else {
                    System.out.print(pozicio + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static char[][] createTabla(int TablaHossza, char viz, char hajo, int hajokSzama) {
        char[][] Tabla = new char[TablaHossza][TablaHossza];
        for (char[] sor : Tabla) {
            Arrays.fill(sor, viz);
        }
        return hajokHelye(Tabla, hajokSzama, viz, hajo);
    }

    private static char[][] hajokHelye(char[][] Tabla, int hajokSzama, char viz, char hajo) {
        int meglevoHajok = 0;
        int TablaHossza = Tabla.length;
        while (meglevoHajok < hajokSzama) {
            int[] hely = generateHajoKoordinata(TablaHossza);
            char lehetsegesHely = Tabla[hely[0]][hely[1]];
            if (lehetsegesHely == viz) {
                Tabla[hely[0]][hely[1]] = hajo;
                meglevoHajok++;
            }
        }
        return Tabla;
    }
        //
    private static int[] generateHajoKoordinata(int TablaHossza) {
        int[] koordinata = new int [2];
        for (int i = 0; i < koordinata.length; i++){
            koordinata[i] = new Random().nextInt(TablaHossza);
        }
        return koordinata;
    }

}
