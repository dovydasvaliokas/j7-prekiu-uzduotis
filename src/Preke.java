import java.text.DecimalFormat;

public class Preke {
    private int id;
    private String pavadinimas;
    private String aprasymas;
    private double kaina;
    private int kiekis;
    private String kategorija;
    private String medziaga;

    public Preke() {
    }

    public Preke(int id, String pavadinimas, String aprasymas, double kaina, int kiekis, String kategorija, String medziaga) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.aprasymas = aprasymas;
        this.kaina = kaina;
        this.kiekis = kiekis;
        this.kategorija = kategorija;
        this.medziaga = medziaga;
    }

    /**
     * Patikrina ar prekė yra sandėlyje
     * @return true, jeigu yra, false jeigu nėra
     */
    public boolean arYraSandelyje() {
        return kiekis > 0;
    }

    /**
     * Sumažina prekės kaina tam tikru procentu.
     * @param nuolaidaProcentais Nuolaida procentine dalimi (%). Funkcija viduje konvertuoja į dešimtainę trupmeną.
     */
    public void pritaikytiNuolaida(double nuolaidaProcentais) {
        kaina = kaina - kaina * nuolaidaProcentais / 100;
    }

    /**
     * Grąžina "string" kainos reikšmę, bet suformatuotą iki 2 skaičių po kableliu.
     * Tai tikros reikšmės nepakeičia, todėl skaičiavimai išlieka "tikri", bet tai parodo normalesnę kainą, kuri turi tik du skaičius po kableliu.
     * Jums to nereikėjo daryti, tačiau čia kaip papildomas pavyzdys. Visur kur naudojamas šitas, galite tiesiog naudoti getKaina()
     * @return suformatuotą kainą, du skaičiai po kableliu
     */
    public String getSuformatuotaKaina() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return df.format(kaina);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public String getAprasymas() {
        return aprasymas;
    }

    public void setAprasymas(String aprasymas) {
        this.aprasymas = aprasymas;
    }

    public double getKaina() {
        return kaina;
    }

    public void setKaina(double kaina) {
        this.kaina = kaina;
    }

    public int getKiekis() {
        return kiekis;
    }

    public void setKiekis(int kiekis) {
        this.kiekis = kiekis;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getMedziaga() {
        return medziaga;
    }

    public void setMedziaga(String medziaga) {
        this.medziaga = medziaga;
    }

    @Override
    public String toString() {
        return "Preke{" +
                "id=" + id +
                ", pavadinimas='" + pavadinimas + '\'' +
                ", aprasymas='" + aprasymas + '\'' +
                ", kaina=" + kaina +
                ", kiekis=" + kiekis +
                ", kategorija='" + kategorija + '\'' +
                ", medziaga='" + medziaga + '\'' +
                '}';
    }
}
