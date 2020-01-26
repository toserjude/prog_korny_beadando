import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.type.TypeReference;

import dao.IAutoDAO;
import exception.AutoNotFound;
import model.Auto;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class AutoDAO implements IAutoDAO {
    private File jsonfile;
    ObjectMapper mapper;
    Logger logger = Logger.getLogger(this.getClass());
    Collection<Auto> autoCollection;

    public AutoDAO(String jsonfile) throws IOException {
        this.jsonfile = new File(jsonfile);
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        if (!this.jsonfile.exists()) {
            logger.warn("Fajl letrehozva " + jsonfile);
            this.jsonfile.createNewFile();
            FileWriter writer = new FileWriter(this.jsonfile);
            writer.write("[]");
            writer.close();
        }
        autoCollection = readAllAuto();
        logger.info("Az adatbazis inicializalva lett a " + jsonfile + " fajllal.");
    }

    public Collection<Auto> readAllAuto() throws IOException {
        Collection<Auto> result = new ArrayList<>();
        TypeReference reference = new TypeReference<ArrayList<Auto>>() {
        };
        result = mapper.readValue(jsonfile, reference);
        return result;
    }

    public void addAuto(Auto newAuto) throws IOException {
        try {
            selectAutoByRendszam(newAuto.getRendszam());
            logger.warn("A rendszam mar szerepel " + newAuto);
            throw new AutoNotFound();
        } catch (AutoNotFound autoNotFound) {
            this.autoCollection.add(newAuto);
            mapper.writeValue(jsonfile, this.autoCollection);
        }
    }

    public Auto selectAutoByRendszam(String id) throws AutoNotFound {
        System.out.println(this.autoCollection);
        for (Auto auto : this.autoCollection) {
            if (auto.getRendszam().equalsIgnoreCase(id)) {
                return auto;
            }
        }
        throw new AutoNotFound();
    }

    public void updateAuto(Auto auto) throws IOException, AutoNotFound {
        Auto autoToUpdate = selectAutoByRendszam(auto.getRendszam());
        this.autoCollection.remove(autoToUpdate);
        this.autoCollection.add(auto);
        mapper.writeValue(jsonfile, this.autoCollection);
    }

    public void deleteAuto(Auto auto) throws IOException, AutoNotFound {
        Auto autoToDelete = selectAutoByRendszam(auto.getRendszam());
        this.autoCollection.remove(autoToDelete);
        mapper.writeValue(jsonfile, this.autoCollection);
    }
}

