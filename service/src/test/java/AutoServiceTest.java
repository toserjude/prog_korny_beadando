import dao.IAutoDAO;
import exception.*;
import model.Auto;
import model.Uzemanyag;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import service.AutoService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class AutoServiceTest {

    AutoService service;
    Collection<Auto> autoCollection;

    @Before
    public void setUp() throws AutoAlreadyExist, AutoNotFound, NemJoDatum, NemJoGyEv, NemJoKobcenti, UresMezo, IOException {
        IAutoDAO dao = Mockito.mock(IAutoDAO.class);
        Auto auto1 = new Auto("Honda", "Civic", "ABC-123", 1400, "Norbi", 2000, LocalDate.of(2017, 5, 14), Uzemanyag.Diesel, true);
        Auto auto2 = new Auto("Ford", "Fiesta", "JDT-666", 1400, "Judit", 2005, LocalDate.of(2019, 10, 14), Uzemanyag.Benzin, true);
        Auto auto3 = new Auto("Tesla", "Model-S", "DRM-999", 4999, "Elon", 2019, LocalDate.of(2020, 1, 1), Uzemanyag.Elektromos, false);

        autoCollection = new ArrayList<>();
        autoCollection.add(auto1);
        autoCollection.add(auto2);
        autoCollection.add(auto3);
        Mockito.when(dao.readAllAuto()).thenReturn(autoCollection);
        Mockito.doThrow(AutoAlreadyExist.class).when(dao).addAuto(auto1);
        Mockito.doThrow(AutoAlreadyExist.class).when(dao).addAuto(auto2);
        Mockito.doThrow(AutoAlreadyExist.class).when(dao).addAuto(auto3);
        Mockito.doThrow(AutoNotFound.class).when(dao).selectAutoByRendszam(Mockito.anyString());
        Mockito.doReturn(auto1).when(dao).selectAutoByRendszam(auto1.getRendszam());
        service = new AutoService(dao);

    }

    @Test()
    public void addAuto() throws AutoAlreadyExist, NemJoDatum, NemJoGyEv, NemJoKobcenti, UresMezo, IOException {
        Auto auto = new Auto("Ford", "Fiesta", "JDT-666", 1400, "Judit", 2005, LocalDate.of(2019, 10, 14), Uzemanyag.Benzin, true);
        service.addAuto(auto);
    }

    @Test
    public void autoCollection() throws UresMezo, NemJoKobcenti, NemJoGyEv, NemJoDatum, IOException {
        assertEquals(3, service.autoCollection().size());
        Auto auto = new Auto("Ford", "Fiesta", "JDT-666", 1400, "Judit", 2005, LocalDate.of(2019, 10, 14), Uzemanyag.Benzin, true);
        // assertThat(service.autoCollection(), Matchers.hasItem(auto));
        assertThat(service.autoCollection(), Matchers.containsInAnyOrder(autoCollection.toArray()));
    }

    @Test(expected = AutoNotFound.class)
    public void selectAutoByRendszam() throws AutoNotFound, IOException {
        service.selectAutoByRendszam("HKI-123");
    }

    @Test
    public void getAutoByMarka() throws IOException {
        assertEquals(1, service.getAutoByMarka("Honda").size());
    }

    @Test
    public void getAutoByTulajdonos() throws IOException {
        assertEquals(1, service.getAutoByTulajdonos("Judit").size());
    }

    @Test
    public void getAutoDueDateToMuszakiVizsga() throws UresMezo, NemJoKobcenti, NemJoGyEv, NemJoDatum, IOException {
        Auto auto = new Auto("Ford", "Fiesta", "JDT-432", 1400, "Judit", 2005, LocalDate.of(2019, 10, 14), Uzemanyag.Benzin, true);
        int gyakorisag = 4;
        System.out.println(service.getAutoDueDateToMuszakiVizsga(gyakorisag));
        assertEquals(0, service.getAutoDueDateToMuszakiVizsga(gyakorisag).size());
        for (Auto a : service.getAutoDueDateToMuszakiVizsga(gyakorisag)
        ) {
            assertThat(auto, Matchers.samePropertyValuesAs(a));
        }
    }
}
