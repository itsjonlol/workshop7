package sg.edu.nus.iss.workshop7.model;

public class Currency {

    private String currencyId;
    private String id;
    private String currencyName;
    private String currencySymbol;

    public Currency() {

    }
    
    

    public Currency(String currencyId, String id, String currencyName, String currencySymbol) {
        this.currencyId = currencyId;
        this.id = id;
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCurrencyName() {
        return currencyName;
    }
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
    public String getCurrencySymbol() {
        return currencySymbol;
    }
    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    

    @Override
    public String toString() {
        return currencyId + "," + id + "," + currencyName
                + "," + currencySymbol ;
    }



    public String getCurrencyId() {
        return currencyId;
    }



    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    
    
    
}
