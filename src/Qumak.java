import java.io.File;
import java.io.FileNotFoundException;

public class Qumak implements LondonExchangeCompany {
    @Override
    public File getLondonExchangeFileName() throws FileNotFoundException {
        File file = new File("QUMAK.mst");
        return file;
    }
}
