import exception.NemJoDatum;
import exception.NemJoGyEv;
import exception.NemJoKobcenti;
import exception.UresMezo;
import model.Auto;
import org.junit.Test;

import java.time.LocalDate;

public class AutoTest {

    @Test(expected = UresMezo.class)
    public void setMarkaToEmpty() throws UresMezo {
        String marka = "";
        Auto a = new Auto();
        a.setMarka(marka);
    }

    @Test
    public void setMarkaValid() throws UresMezo {
        String marka = "Honda";
        Auto a = new Auto();
        a.setMarka(marka);
    }

    @Test(expected = UresMezo.class)
    public void setModelToEmpty() throws UresMezo {
        String model = "";
        Auto a = new Auto();
        a.setModel(model);
    }

    @Test
    public void setModelValid() throws UresMezo {
        String model = "Civic";
        Auto a = new Auto();
        a.setModel(model);
    }

    @Test(expected = UresMezo.class)
    public void setRendszamToEmpty() throws UresMezo {
        String rendszam = "";
        Auto a = new Auto();
        a.setRendszam(rendszam);
    }

    @Test
    public void setRendszamValid() throws UresMezo {
        String rendszam = "ABC-123";
        Auto a = new Auto();
        a.setRendszam(rendszam);
    }

    @Test(expected = NemJoKobcenti.class)
    public void setHengerurtartalomToLow() throws NemJoKobcenti {
        int cm3 = 100;
        Auto a = new Auto();
        a.setHengerurtartalom(cm3);
    }

    @Test(expected = NemJoKobcenti.class)
    public void setHengerurtartalomToHigh() throws NemJoKobcenti {
        int cm3 = 5001;
        Auto a = new Auto();
        a.setHengerurtartalom(cm3);
    }

    @Test
    public void setKobcentiValid() throws NemJoKobcenti {
        int cm3 = 3000;
        Auto a = new Auto();
        a.setHengerurtartalom(cm3);
    }

    @Test(expected = NemJoGyEv.class)
    public void setGyEvToLow() throws NemJoGyEv {
        int year = 1500;
        Auto a = new Auto();
        a.setGyartasEve(year);
    }

    @Test(expected = NemJoGyEv.class)
    public void setGyEvToHigh() throws NemJoGyEv {
        int year = 2021;
        Auto a = new Auto();
        a.setGyartasEve(year);
    }

    @Test
    public void setGyEvValid() throws NemJoGyEv {
        int year = 2010;
        Auto a = new Auto();
        a.setGyartasEve(year);
    }

    @Test(expected = NemJoDatum.class)
    public void setMuszakiVizsgaIdoToHigh() throws NemJoDatum {
        LocalDate date = LocalDate.of(2022, 10, 5);
        Auto a = new Auto();
        a.setMuszakiVizsgaIdo(date);
    }

    @Test
    public void setMuszakiVizsgaIdoValid() throws NemJoDatum {
        LocalDate date = LocalDate.of(2019, 10, 5);
        Auto a = new Auto();
        a.setMuszakiVizsgaIdo(date);
    }
}
