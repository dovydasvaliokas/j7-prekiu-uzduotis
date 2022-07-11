import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
1. Susikurkite Java projektą, susikurkite Programa.java failą (class), tame faile susikurkite main funkciją.
2. Susikurkite Preke.java papildomą klasę.
3. Susikurkite Koordinate klasei kintamuosius id, pavadinimas, aprasymas, kaina, kiekis, kategorija, medziaga
4. Susikurkite (generate...) tai klasei constructor, getter-setter, toString()
5. Susikurkite tai klasei boolean funkciją, kuri grąžina true, jeigu prekė yra sandėlyje ir false, jeigu jos nėra.
6. Programa.java faile susikurkite funkciją, kuri nuskaito prekių ArrayList iš failo
7. Programa.java faile sukurkite papildomą funkciją, kuri surastų kiek iš viso prekių yra sandėlyje (susumuotų visų prekių kiekius)
8. Programa.java faile sukurkite papildomą funkciją, kuri surastų KIEK kainuoja brangiausia prekė.
9. Patobulinkite 8. punkto užduotį, jog grąžintų ne KIEK kainuoja brangiausia prekė, o pačios brangiausios prekės Preke objektą
10. Preke.java faile sukurkite papildomą funkciją pritaikytiNuolaidą(), kuri bus void duomenų tipo ir per parametrus gaus kiek procentų nuolaidą padaryti prekei. Galite sakyti, jog parametras bus int procentai, jeigu darysite, kad per skliaustus gautų procentus arba iš karto galite daryti double procentai, jeigu per skliaustus norėsite, jog iš karto paduotų dešimtainę dalį. Pvz.: 15% yra tas pats kaip 0.15. Funkcija turėtų sumažinti kainą gautu procentų skaičių. Jeigu prekė prieš tai kainavo 5.99, tai atlikus funkciją ir jai padavus 20%, prekės kaina turėtų likti: 4.79
11. Programa.java sumažinkite visų prekių, kurių kaina yra didesnė nei 600, kainas per 10%.
12. Išveskite prekių list'o informaciją.
13. Programa.java sumažinkite visų prekių, kurių pavadinimas yra Stalas kainas per 42.5%
14. Išveskite prekių list'o informaciją.
15. Programa.java faile sukurkite papildomą funkciją, kuri per parametrus gauna List<Preke> visosPrekes bei String ieskomaKategorija ir išfiltruoja iš visų prekių tik tas prekes, kurių kategorija sutampa su ieskomaKategorija. Funkcija returnina List<Preke> isfiltruotosPrekes
16. Programa.java faile sukurkite papildomą funkciją, kuri per parametrus gauna List<Preke> prekes bei String pirmaMedziaga ir String antraMedziaga. Jūsų funkcija turėtų surasti kurios medžiagos prekių yra daugiau sistemoje. Grąžinti -1 jeigu pirmos medžiagos, 1 jeigu antros ir 0 jeigu vienodai.
17. Patobulinkite 16. punktą, jog palygintų ne kiek skirtingų modelių yra daugiausa kažkurios medžiagos, bet pažiūrėtų kurios medžiagos prekių yra daugiausia sandėliuose (įvertinti ir jų kiekį)
 */
public class Programa {
    public static final String FAILO_PAVADINIMAS = "prekes.csv";
    public static void main(String[] args) {

        ArrayList<Preke> prekes = new ArrayList<>();    // sukuriu naują arraylistą, nes negaliu iš karto saugoti į jį funkciją - nes jį saugosiu "try" bloke. O jeigu bandysiu tik try bloke, tai tada vėliau sakys, jeigu už "try" bloko bandysiu pasiekti šį arraylistą, jog negalima, nes scope difference


        // Exception gaudau čia. Vienas dar papildomass pavyzdys, kuris yra gana dažnas
        // Kai exception gaudome ne funkcijoje, o pasakome, kad ji "throwina" tam tikrą exception ir gaudome tik toje vietoje, kur naudojame tą funkciją
        try {
            prekes = nuskaitytiLista(FAILO_PAVADINIMAS);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nerastas failas");
            return;
        }
        isvestiListaEilutemis(prekes);
        double kiekIsVisoYra = kiekIsVisoSandelyje(prekes);
        System.out.println("kiekIsVisoYra = " + kiekIsVisoYra);
        double brangiausiosPrekesKaina = brangiausiosPrekesKaina(prekes);
        System.out.println("brangiausiosPrekesKaina = " + brangiausiosPrekesKaina);
        Preke brangiausiaPreke = brangiausiosPrekesRadimas(prekes);
        System.out.println("brangiausiaPreke = " + brangiausiaPreke);

    }

    /**
     * Nuskaito prekių listą iš failo
     * @param failas failo pavadinimas
     * @return listą
     * @throws FileNotFoundException jeigu tokio failo nėra - išmeta exception, kurią reikės gaudyti
     */
    public static ArrayList<Preke> nuskaitytiLista(String failas) throws FileNotFoundException {
        ArrayList<Preke> list = new ArrayList<>();
        File failoObjektas = new File(failas);
        Scanner sk = new Scanner(failoObjektas);
        sk.nextLine();
        while (sk.hasNextLine()) {
            String eilute = sk.nextLine();
            String[] stulpeliai = eilute.split(",");
            Preke laikinaPreke = new Preke(Integer.parseInt(stulpeliai[0]), stulpeliai[1], stulpeliai[2], Double.parseDouble(stulpeliai[3]), Integer.parseInt(stulpeliai[4]), stulpeliai[5], stulpeliai[6]);
            list.add(laikinaPreke);
        }
        return list;
    }

    /**
     * Išveda listą kiekvieną elementą į atskirą eilutę
     * @param prekes listas
     */
    public static void isvestiListaEilutemis(List<Preke> prekes) {
        for (Preke preke : prekes) {
            System.out.println(preke);
        }
    }

    /**
     * Suskaičiuoja kiek iš viso vienetų visų prekių yra sandėliuose
     * @param prekes prekių sąrašas
     * @return sveiką skaičių - kiekį
     */
    public static int kiekIsVisoSandelyje(List<Preke> prekes) {
        int suma = 0;
        for (Preke preke : prekes) {
            suma += preke.getKiekis();
        }
        return suma;
    }

    /**
     * Suranda brangiausios prekės kainą
     * @param prekes prekių sąrašas
     * @return brangiausią kainą (double)
     */
    public static double brangiausiosPrekesKaina(List<Preke> prekes) {
        double max = prekes.get(0).getKaina();
        for (Preke preke : prekes) {
            if (preke.getKaina() > max) {
                max = preke.getKaina();
            }
        }
        return max;
    }

    /**
     * Suranda brangiausią prekę (objektą)
     * @param prekes prekių sąrašas
     * @return brangiausios prekės objektą
     */
    public static Preke brangiausiosPrekesRadimas(List<Preke> prekes) {
        Preke max = prekes.get(0);
        for (Preke preke : prekes) {
            if (preke.getKaina() > max.getKaina()) {
                max = preke;
            }
        }
        return max;
    }
}
