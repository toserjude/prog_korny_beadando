import exception.UresMezo;
import model.Auto;
import org.junit.Test;

import java.io.IOException;

public class AutoDAOTest {
    @Test
    public void testDB() throws IOException, UresMezo {
        AutoDAO dao = new AutoDAO("auto.json");
        Auto auto = new Auto();
        auto.setRendszam("HJS-564");
        System.out.println(dao.readAllAuto());
    }

    @Test
    public void testDB2() throws IOException, UresMezo {
        AutoDAO dao = new AutoDAO("auto.json");
        Auto c = new Auto();
        c.setRendszam("HJS-564");
        System.out.println(dao.readAllAuto());
    }
}
