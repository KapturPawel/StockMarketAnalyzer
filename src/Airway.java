import java.io.File;
import java.io.FileNotFoundException;

public class Airway implements WarsawExchangeCompany {
    @Override
    public File getWarsawExchangeFileName() throws FileNotFoundException {
        File file = new File("AIRWAY.mst");
        return file;
    }
}
