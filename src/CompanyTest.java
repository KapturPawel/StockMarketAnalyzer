import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class CompanyTest {
    public static void main(String[] args) throws FileNotFoundException {

        AbstractFactory warsawExchangeFactory = new FactoryProducer().getFactory("warsaw");
        AbstractFactory londonExchangeFactory = new FactoryProducer().getFactory("london");

        ArrayList<String> warsawCompaniesNames = new ArrayList<>();
        warsawCompaniesNames.add("cdprojekt");
        warsawCompaniesNames.add("comperia");
        warsawCompaniesNames.add("imcompany");
        warsawCompaniesNames.add("airway");

        Scanner in;

        System.out.println("Wskazniki dla firm z gieldy warszawskiej: ");
        for (int i = 0; i < warsawCompaniesNames.size(); i++) {
            WarsawExchangeCompany warsawExchangeCompany = warsawExchangeFactory.getWarsawCompany(warsawCompaniesNames.get(i));
            in = new Scanner(warsawExchangeCompany.getWarsawExchangeFileName());
            calculateRSI(in);
            in = new Scanner(warsawExchangeCompany.getWarsawExchangeFileName());
            calculateEMA(in);
        }

        ArrayList<String> londonCompaniesNames = new ArrayList<>();
        londonCompaniesNames.add("onico");
        londonCompaniesNames.add("provecta");
        londonCompaniesNames.add("qumak");

        System.out.println("Wskazniki dla firm z gieldy londynskiej: ");
        for (int i = 0; i < londonCompaniesNames.size(); i++){
            LondonExchangeCompany londonExchangeCompany = londonExchangeFactory.getLondonCompany(londonCompaniesNames.get(i));
            in = new Scanner(londonExchangeCompany.getLondonExchangeFileName());
            calculateRSI(in);
            in = new Scanner(londonExchangeCompany.getLondonExchangeFileName());
            calculateEMA(in);
        }
    }

    public static void calculateRSI(Scanner in) {

        String line = "";

        double close;
        double previousClose = 0;
        double difference;

        String[] list;

        int count = 0;

        ArrayList<Double> gain = new ArrayList<>();
        ArrayList<Double> loss = new ArrayList<>();
        ArrayList<Double> averageGain = new ArrayList<>();
        ArrayList<Double> averageLoss = new ArrayList<>();
        ArrayList<Double> RS = new ArrayList<>();
        ArrayList<Double> RSI = new ArrayList<>();

        String companyName = "";

        ArrayList<String> date = new ArrayList<>();

        while (in.hasNext()) {

            line = in.nextLine();

            list = line.split(",");

            if (count > 0) {

                //dodawanie do listy daty w czytelniejszym formacie
                date.add(list[1].substring(list[1].length() - 2) + "/" + list[1].substring(4, 6) + "/" + list[1].substring(0, 4));

                //pobieranie z kolumny close danych potrzebnych do obliczenia wskaznika
                close = Double.parseDouble(list[5]);

                if (count > 1) {

                    difference = close - previousClose;

                    if (difference < 0) {
                        loss.add(Math.abs(difference));
                        gain.add(0.0);
                    } else if (difference > 0) {
                        gain.add(difference);
                        loss.add(0.0);
                    } else {
                        gain.add(0.0);
                        loss.add(0.0);
                    }

                    if (count == 15) {

                        //sposob obliczania RSI
                        double gainSum = 0;
                        for (int i = 0; i < gain.size(); i++) {
                            gainSum += gain.get(i);
                        }
                        averageGain.add(gainSum / 14);

                        double lossSum = 0;
                        for (int i = 0; i < loss.size(); i++) {
                            lossSum += loss.get(i);
                        }
                        averageLoss.add(lossSum / 14);

                        if (averageLoss.get(0) != 0) {
                            RS.add(averageGain.get(0) / averageLoss.get(0));
                            RSI.add(100 - (100 / (1 + RS.get(0))));
                        } else {
                            RS.add(0.0);
                            RSI.add(100.0);
                        }
                    }

                    //tutaj dla wiekszych niz 15, ponieważ RSI liczy sie inaczej gdy ma sie RSI z dnia wczesniejszego
                    if (count > 15) {
                        double avgGain = ((averageGain.get(averageGain.size() - 1)) * 13 + gain.get(gain.size() - 1)) / 14;
                        averageGain.add(avgGain);
                        double avgLoss = ((averageLoss.get(averageLoss.size() - 1)) * 13 + loss.get(loss.size() - 1)) / 14;
                        averageLoss.add(avgLoss);

                        if (averageLoss.get(averageLoss.size() - 1) != 0) {
                            RS.add(averageGain.get(averageGain.size() - 1) / averageLoss.get(averageLoss.size() - 1));
                            RSI.add(100 - (100 / (1 + RS.get(RS.size() - 1))));
                        } else {
                            RS.add(0.0);
                            RSI.add(100.0);
                        }
                    }
                }

                previousClose = close;
            }

            companyName = list[0].toLowerCase();

            count++;
        }

        System.out.println("Wskaznik RSI dla firmy " + companyName + " z ostatnich 7 dni:");
        for (int i = 7; i > 0; i--) {
            System.out.print(date.get(date.size() - i) + " - ");
            System.out.printf("%.2f%n", RSI.get(RSI.size() - i));
        }
        System.out.println();
    }

    public static void calculateEMA(Scanner in) {

        final double smoothingConstant = 2.0 / 11.0;
        String line = "";

        double close;
        double average = 0;

        String[] list;

        int count = 0;

        ArrayList<Double> EMA = new ArrayList<>();
        ArrayList<Double> price = new ArrayList<>();

        String companyName = "";

        ArrayList<String> date = new ArrayList<>();
        while (in.hasNext()) {

            line = in.nextLine();

            list = line.split(",");

            if (count > 0) {

                date.add(list[1].substring(list[1].length() - 2) + "/" + list[1].substring(4, 6) + "/" + list[1].substring(0, 4));

                close = Double.parseDouble(list[5]);

                price.add(close);

                if (count == 10) {

                    for (int i = count - 10; i < count; i++) {
                        average += price.get(i);
                    }

                    average /= 10;
                    EMA.add(average);
                }

                if (count > 10) {
                    double newEma = smoothingConstant * ((price.get(price.size() - 1) - EMA.get(EMA.size() - 1)) + EMA.get(EMA.size() - 1));
                    EMA.add(newEma);
                }
            }

            companyName = list[0].toLowerCase();

            count++;
        }

        System.out.println("Wskaznik EMA dla firmy " + companyName + " z ostatnich 7 dni:");
        for (int i = 7; i > 0; i--) {
            System.out.print(date.get(date.size() - i) + " - ");
            System.out.printf("%.2f%n", EMA.get(EMA.size() - i));
        }
        System.out.println();
    }
}
