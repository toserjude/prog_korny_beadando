package dao;


import exception.AutoNotFound;
import exception.EzARendszamLetezik;
import model.Auto;

import java.io.IOException;
import java.util.Collection;

public interface IAutoDAO {
    Collection<Auto> readAllAuto() throws IOException;

    void addNewAuto(Auto auto) throws IOException, EzARendszamLetezik;

    Auto selectAutoByRendszam(String rendszam) throws AutoNotFound;
}
