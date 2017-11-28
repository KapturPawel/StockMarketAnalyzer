import java.io.File;
import java.io.FileNotFoundException;

public class Onico implements LondonExchangeCompany {
    @Override
    public File getLondonExchangeFileName() throws FileNotFoundException {
        File file = new File("ONICO.mst");
        return file;
    }
}
