import java.io.File;
import java.io.FileNotFoundException;


public class Comperia implements WarsawExchangeCompany {
    @Override
    public File getWarsawExchangeFileName() throws FileNotFoundException {
        File file = new File("COMPERIA.mst");
        return file;
    }
}
