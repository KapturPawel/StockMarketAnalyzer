import java.io.File;
import java.io.FileNotFoundException;


public class Imcompany implements WarsawExchangeCompany {
    @Override
    public File getWarsawExchangeFileName() throws FileNotFoundException {
        File file = new File("IMCOMPANY.mst");
        return file;
    }
}
