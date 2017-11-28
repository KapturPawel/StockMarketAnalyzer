import java.io.File;
import java.io.FileNotFoundException;


public class Cdprojekt implements WarsawExchangeCompany {
    @Override
    public File getWarsawExchangeFileName() throws FileNotFoundException {
        File file = new File("CDPROJEKT.mst");
        return file;
    }
}
