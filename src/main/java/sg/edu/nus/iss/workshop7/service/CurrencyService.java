package sg.edu.nus.iss.workshop7.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.workshop7.constant.Url;
import sg.edu.nus.iss.workshop7.model.Currency;

@Service
public class CurrencyService {

    RestTemplate restTemplate = new RestTemplate();
    // public static final String apiKey = "e19d793fde867e2c6dd5"; // when undeployed, this is what i will use

    @Value("${currency.api.key}")
    private String apiKey;

    public List<Currency> getCurrencies() {
        //method 1 of replacing placeholders in url
        String currencyUrl = Url.currencyUrl.replaceAll("\\{apiKey\\}", apiKey);
        
        System.out.println(currencyUrl);
        ResponseEntity<String> currencyEntity = restTemplate.getForEntity(currencyUrl, String.class);

        // ResponseEntity<String> currencyEntity = restTemplate.getForEntity(Url.currencyUrl+"?apiKey="+apiKey, String.class);

        String currencyJsonString = currencyEntity.getBody();

        InputStream is = new ByteArrayInputStream(currencyJsonString.getBytes());

        JsonReader reader = Json.createReader(is);
        JsonObject currencyJson = reader.readObject();

        JsonObject resultJson = currencyJson.getJsonObject("results");

        List<Currency> currencies = new ArrayList<>();

        Set<String> keys = resultJson.keySet();


        for (String k : keys) {
            JsonObject individualJson = resultJson.getJsonObject(k);
            Currency currency = new Currency();
            String currencyId = individualJson.getString("currencyId");
            String id = individualJson.getString("id");
            String currencyName = individualJson.getString("currencyName");
            String currencySymbol = individualJson.getString("currencySymbol");
            
            currency.setCurrencyId(currencyId);
            currency.setId(id);
            currency.setCurrencyName(currencyName);
            currency.setCurrencySymbol(currencySymbol);
            currencies.add(currency);
        }
        
        return currencies;
        
        
    }
    //assume api key is hidden, and you gave it at the requestparam
    public List<Currency> getCurrencies2(String apiKey2) {
        //method 1 of replacing placeholders in url
        String currencyUrl = Url.currencyUrl.replaceAll("\\{apiKey\\}", apiKey2);
        
        System.out.println(currencyUrl);
        ResponseEntity<String> currencyEntity = restTemplate.getForEntity(currencyUrl, String.class);

        // ResponseEntity<String> currencyEntity = restTemplate.getForEntity(Url.currencyUrl+"?apiKey="+apiKey, String.class);

        String currencyJsonString = currencyEntity.getBody();

        InputStream is = new ByteArrayInputStream(currencyJsonString.getBytes());

        JsonReader reader = Json.createReader(is);
        JsonObject currencyJson = reader.readObject();

        JsonObject resultJson = currencyJson.getJsonObject("results");

        List<Currency> currencies = new ArrayList<>();

        Set<String> keys = resultJson.keySet();


        for (String k : keys) {
            JsonObject individualJson = resultJson.getJsonObject(k);
            Currency currency = new Currency();
            String currencyId = individualJson.getString("currencyId");
            String id = individualJson.getString("id");
            String currencyName = individualJson.getString("currencyName");
            String currencySymbol = individualJson.getString("currencySymbol");
            
            currency.setCurrencyId(currencyId);
            currency.setId(id);
            currency.setCurrencyName(currencyName);
            currency.setCurrencySymbol(currencySymbol);
            currencies.add(currency);
        }
        
        return currencies;
        
        
    }

    public Double getCurrencyRate(String fromCurrency, String toCurrency) {
        // "https://free.currconv.com/api/v7/convert?q={fromcurrency}_{tocurrency}&compact=ultra&apiKey={apiKey}";
        // String currencyUrl = Url.conversionUrl.replaceAll("\\{apiKey\\}", apiKey)
        //                                       .replaceAll("\\{fromcurrency\\}",fromCurrency)
        //                                       .replaceAll("\\{tocurrency\\}",toCurrency);
        // System.out.println(currencyUrl);

        //method 2 of replacing placeholders in url
        String currencyUrl = String.format(Url.conversionUrl,fromCurrency,toCurrency,apiKey);
        System.out.println(currencyUrl);
        ResponseEntity<String> currencyEntity = restTemplate.getForEntity(currencyUrl, String.class);
        String currencyJsonString = currencyEntity.getBody();
        InputStream is = new ByteArrayInputStream(currencyJsonString.getBytes());
        
        JsonReader reader = Json.createReader(is);
        JsonObject currencyJson = reader.readObject();
        JsonNumber conversionRate = currencyJson.getJsonNumber(fromCurrency + "_" + toCurrency);
        Double conversion = conversionRate.doubleValue();

        // System.out.println("As double: " + jsonNumber.doubleValue()); // 111.919785
        // System.out.println("As BigDecimal: " + jsonNumber.bigDecimalValue()); // 111.919785
        // System.out.println("Is integral: " + jsonNumber.isIntegral()); // false

        //  float rateAsFloat = (float) jsonNumber.doubleValue(); // Explicit casting
        // BigDecimal rateAsBigDecimal = jsonNumber.bigDecimalValue(); // High-precision conversion
        // double rateAsDouble = jsonNumber.doubleValue(); // Direct conversion
        
        return conversion;
    }
    public Currency getCurrency(String currencyId) {
        List<Currency> currencies = this.getCurrencies();
        for (Currency currency : currencies) {
            if(currency.getCurrencyId().equals(currencyId)) {
                return currency;
            }
        }
        return null;

    }
}
