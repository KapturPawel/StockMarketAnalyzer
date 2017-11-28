public class FactoryProducer {
    public static AbstractFactory getFactory(String exchange) {
        switch (exchange.toLowerCase()) {
            case "london":
                return new LondonExchangeCompanyFactory();
            case "warsaw":
                return new WarsawExchangeCompanyFactory();
            default:
                return null;
        }
    }
}
