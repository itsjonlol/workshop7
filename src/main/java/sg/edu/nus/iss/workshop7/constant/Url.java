package sg.edu.nus.iss.workshop7.constant;

public class Url {
    public static final String currencyUrl = "https://free.currconv.com/api/v7/countries?apiKey={apiKey}";
    // public static final String conversionUrl = "https://free.currconv.com/api/v7/convert?q={fromcurrency}_{tocurrency}&compact=ultra&apiKey={apiKey}";
    public static final String conversionUrl = "https://free.currconv.com/api/v7/convert?q=%s_%s&compact=ultra&apiKey=%s";
                
}
