public class LondonExchangeCompanyFactory extends AbstractFactory {
    public LondonExchangeCompany getLondonCompany(String londonCompany){
        if(londonCompany == null){
            return null;
        }
        if(londonCompany.equalsIgnoreCase("onico")){
            return new Onico();
        }

        else if(londonCompany.equalsIgnoreCase("provecta")){
            return new Provecta();
        }

        else if(londonCompany.equalsIgnoreCase("Qumak")){
            return new Qumak();
        }
        return null;
    }

    @Override
    WarsawExchangeCompany getWarsawCompany(String warsawCompany) {
        return null;
    }
}
