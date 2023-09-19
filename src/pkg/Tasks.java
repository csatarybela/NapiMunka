package pkg;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tasks {
    static List<NapiMunka> napiMunkak = new ArrayList<>();

    public static void t3() throws IOException {
        FileReader fr = new FileReader("diszek.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            NapiMunka nm = new NapiMunka(line);
            napiMunkak.add(nm);
        }
        br.close();
        fr.close();
    }

    public static void t4() {
        System.out.println("4. feladat: Összesen " + NapiMunka.getKeszultDb() + " darab dísz készült.");
    }

    public static void t5() {
        System.out.print("\n5. feladat: ");
        boolean volt = false;
        for (NapiMunka nm : napiMunkak) {
            if (nm.getAngyalkaKesz() + nm.getHarangKesz() + nm.getFenyofaKesz() == 0) {
                volt = true;
                break;
            }
        }
        if (volt) {
            System.out.println("Volt olyan nap, amikor egyetlen dísz sem készült.");
        } else {
            System.out.println("Nem volt olyan nap, amikor egyetlen dísz sem készült.");
        }
    }

    public static void t6() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n6. feladat: ");
        int nap;
        do {
            System.out.print("Adja meg a keresett napot [1 ... 40]: ");
            nap = sc.nextInt();
        } while (nap < 1 || nap > 40);
        sc.close();

        int osszHarang = 0;
        int osszAngyalka = 0;
        int osszFenyofa = 0;
        for (int i = 0; i < nap; i++) {
            osszHarang += napiMunkak.get(i).getHarangKesz() + napiMunkak.get(i).getHarangEladott();
            osszAngyalka += napiMunkak.get(i).getAngyalkaKesz() + napiMunkak.get(i).getAngyalkaEladott();
            osszFenyofa += napiMunkak.get(i).getFenyofaKesz() + napiMunkak.get(i).getFenyofaEladott();
        }
        System.out.printf("\tA(z) %d. nap végén %d harang, %d angyalka és %d fenyőfa maradt készleten.\n",
                nap, osszHarang, osszAngyalka, osszFenyofa);
    }

    public static void t7() {
        System.out.print("\n7. feladat:");
        int osszHarang = napiMunkak.stream().
                mapToInt(NapiMunka::getHarangEladott).sum();
        int osszAngyalka = napiMunkak.stream().
                mapToInt(NapiMunka::getAngyalkaEladott).sum();
        int osszFenyofa = napiMunkak.stream().
                mapToInt(NapiMunka::getFenyofaEladott).sum();

        int max = Math.max(Math.max(-osszAngyalka, -osszFenyofa), -osszHarang);
        System.out.println(" Legtöbbet eladott dísz: " + max + " darab.");
        if (max == -osszAngyalka){
            System.out.println("\tAngyalka");
        }
        if (max == -osszFenyofa){
            System.out.println("\tFenyőfa");
        }
        if (max == -osszHarang){
            System.out.println("\tHarang");
        }
    }

    public static void t8() throws IOException {
        FileWriter fw = new FileWriter("bevetel.txt");
        PrintWriter pw = new PrintWriter(fw);
        int darab = 0;
        for (NapiMunka nm:napiMunkak) {
            if (nm.napiBevetel() > 10000){
                pw.println(nm.getNap() + ":" + nm.napiBevetel());
                darab++;
            }
        }
        pw.println(darab + " napon volt legalább 10000 Ft a bevétel.");
        pw.close();
        fw.close();
    }
}
