public class WarsawExchangeCompanyFactory extends AbstractFactory {
    public WarsawExchangeCompany getWarsawCompany(String warsawCompany){
        if(warsawCompany == null){
            return null;
        }
        if(warsawCompany.equalsIgnoreCase("cdprojekt")){
            return new Cdprojekt();
        }

        else if(warsawCompany.equalsIgnoreCase("comperia")){
            return new Comperia();
        }

        else if(warsawCompany.equalsIgnoreCase("imcompany")){
            return new Imcompany();
        }
        
        else if(warsawCompany.equalsIgnoreCase("airway")){
            return new Airway();
        }
        return null;
    }

    @Override
    LondonExchangeCompany getLondonCompany(String londonCompany) {
        return null;
    }

}
