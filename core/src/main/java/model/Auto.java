package model;

import exception.NemJoDatum;
import exception.NemJoGyEv;
import exception.NemJoKobcenti;
import exception.UresMezo;

import java.time.LocalDate;

public class Auto {
    private String marka;
    private String model;
    private String rendszam;
    private int hengerurtartalom;
    private String tulajdonos;
    private int gyartasEve;
    private LocalDate muszakiVizsgaIdo;
    private Uzemanyag uzemanyag;
    private boolean ervenyesOkmanyok;

    public Auto() {
    }

    public Auto(String marka, String model, String rendszam, int hengerurtartalom, String tulajdonos, int gyartasEve,
                LocalDate muszakiVizsgaIdo, Uzemanyag uzemanyag,
                boolean ervenyesOkmanyok) throws UresMezo, NemJoKobcenti, NemJoGyEv, NemJoDatum {
        setMarka(marka);
        setModel(model);
        setRendszam(rendszam);
        setHengerurtartalom(hengerurtartalom);
        setTulajdonos(tulajdonos);
        setGyartasEve(gyartasEve);
        setMuszakiVizsgaIdo(muszakiVizsgaIdo);
        this.uzemanyag = uzemanyag;
        this.ervenyesOkmanyok = ervenyesOkmanyok;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) throws UresMezo {
        if (marka.isEmpty()) {
            throw new UresMezo();
        }
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) throws UresMezo {
        if (model.isEmpty()) {
            throw new UresMezo();
        }
        this.model = model;
    }

    public int getHengerurtartalom() {
        return hengerurtartalom;
    }

    public void setHengerurtartalom(int hengerurtartalom) throws NemJoKobcenti {
        if (hengerurtartalom < 800 || hengerurtartalom > 5000) {
            throw new NemJoKobcenti();
        }
        this.hengerurtartalom = hengerurtartalom;
    }

    public String getRendszam() {
        return rendszam;
    }

    public void setRendszam(String rendszam) throws UresMezo {
        if (rendszam.isEmpty()) {
            throw new UresMezo();
        }
        this.rendszam = rendszam;
    }

    public String getTulajdonos() {
        return tulajdonos;
    }

    public void setTulajdonos(String tulajdonos) throws UresMezo {
        if (tulajdonos.isEmpty()) {
            throw new UresMezo();
        }
        this.tulajdonos = tulajdonos;
    }

    public int getGyartasEve() {
        return gyartasEve;
    }

    // TODO ezt még nézd meg !!!
    public void setGyartasEve(int gyartasEve) throws NemJoGyEv {
        int date = LocalDate.now().getYear();
        System.out.println("DÁTUM" + date);
        if (gyartasEve < 1900) {
            throw new NemJoGyEv();
        }
        this.gyartasEve = gyartasEve;
    }

    public LocalDate getMuszakiVizsgaIdo() {
        return muszakiVizsgaIdo;
    }

    public void setMuszakiVizsgaIdo(LocalDate muszakiVizsgaIdo) throws NemJoDatum {
        if (muszakiVizsgaIdo.isBefore(LocalDate.now()) || muszakiVizsgaIdo.isAfter(LocalDate.now())) {
            throw new NemJoDatum();
        }
        this.muszakiVizsgaIdo = muszakiVizsgaIdo;
    }

    public Uzemanyag getUzemanyag() {
        return uzemanyag;
    }

    public void setUzemanyag(Uzemanyag uzemanyag) {
        this.uzemanyag = uzemanyag;
    }

    public boolean isErvenyesOkmanyok() {
        return ervenyesOkmanyok;
    }

    public void setErvenyesOkmanyok(boolean ervenyesOkmanyok) {
        this.ervenyesOkmanyok = ervenyesOkmanyok;
    }
}
