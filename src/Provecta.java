import java.io.File;
import java.io.FileNotFoundException;

public class Provecta implements LondonExchangeCompany {
    @Override
    public File getLondonExchangeFileName() throws FileNotFoundException {
        File file = new File("PROVECTA.mst");
        return file;
    }
}
