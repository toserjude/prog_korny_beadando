package dao;

import exception.AutoAlreadyExist;
import exception.AutoNotFound;

import model.Auto;

import java.io.IOException;
import java.util.Collection;

public interface IAutoDAO {

    Collection<Auto> readAllAuto() throws IOException;

    Auto selectAutoByRendszam(String id) throws IOException, AutoNotFound;

    void addAuto(Auto auto) throws IOException, AutoAlreadyExist;

    void updateAuto(Auto auto) throws IOException, AutoNotFound;

    void deleteAuto(Auto auto) throws IOException, AutoNotFound;
}
