package dao;

import java.io.IOException;
import java.util.Collection;

// TODO hogy a picsába importálunk másik module-ból???
public interface IAutoDAO {
    public Collection<Auto> readAllCica() throws IOException;

    public void addNewAuto(Auto auto) throws IOException, EzARendszamLetezik;

    public Auto selectAutoByRendszam(String rendszam) throws AutoNotFound;
}
