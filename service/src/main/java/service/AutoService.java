package service;

import dao.IAutoDAO;
import exception.AutoAlreadyExist;
import exception.AutoNotFound;
import model.Auto;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class AutoService {
    IAutoDAO dao;
    Logger logger = Logger.getLogger(this.getClass());

    public AutoService(IAutoDAO dao) {
        this.dao = dao;
    }

    public void addAuto(Auto auto) throws IOException, AutoAlreadyExist {
        dao.addAuto(auto);
    }

    public void updateAuto(Auto auto) throws IOException, AutoNotFound {
        dao.updateAuto(auto);
    }

    public void deleteAuto(Auto auto) throws IOException, AutoNotFound {
        dao.deleteAuto(auto);
    }
    public Collection<Auto> autoCollection() throws IOException {
        return dao.readAllAuto();
    }

    public Auto selectAutoByRendszam(String rendszam) throws IOException, AutoNotFound {
        return dao.selectAutoByRendszam(rendszam);
    }

    public Collection<Auto> getAutoByMarka(String marka) throws IOException {
        Collection<Auto> autok = autoCollection();
        Collection<Auto> result = new ArrayList<>();
        for (Auto auto : autok) {
            if (auto.getMarka().toLowerCase().equals(marka.toLowerCase())) {
                result.add(auto);
            }
        }
        logger.debug(marka + " márkájú autó " + result.size() + " db van.");
        return result;
    }

    public Collection<Auto> getAutoByTulajdonos(String nev) throws IOException {
        Collection<Auto> autok = autoCollection();
        Collection<Auto> result = new ArrayList<>();
        for (Auto auto : autok) {
            if (auto.getTulajdonos().toLowerCase().equals(nev.toLowerCase())) {
                result.add(auto);
            }
        }
        return result;

    }

    public Collection<Auto> getAutoDueDateToMuszakiVizsga(int gyakorisagEv) throws IOException {
        Collection<Auto> autok = autoCollection();
        Collection<Auto> result = new ArrayList<>();
        for (Auto auto : autok) {
            if (auto.getMuszakiVizsgaIdo().plusYears(gyakorisagEv).isBefore(LocalDate.now())) {
                result.add(auto);
            }
        }
        return result;
    }
}
