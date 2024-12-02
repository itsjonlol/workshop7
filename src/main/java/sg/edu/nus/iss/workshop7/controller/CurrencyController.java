package sg.edu.nus.iss.workshop7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.nus.iss.workshop7.model.Currency;
import sg.edu.nus.iss.workshop7.service.CurrencyService;

@Controller
@RequestMapping("/currencies")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @GetMapping("")
    @ResponseBody
    public List<Currency> getCurrencies() {
        List<Currency> currencies = currencyService.getCurrencies();
        return currencies;

    }
    @GetMapping("/convert")
    public String getIndexPage(Model model) {
        List<Currency> currencies = currencyService.getCurrencies();
        model.addAttribute("currencies",currencies);
        
        return "index";
    }

    @PostMapping("/result")
    public String getConversion(@RequestBody MultiValueMap<String,String> form, Model model) {

        String fromCurrencyId = form.getFirst("fromselectedcurrency"); // name must be at the select portion
        
        String toCurrencyId = form.getFirst("toselectedcurrency");
        Double amount = Double.parseDouble(form.getFirst("amounttoconvert"));

        

        Double currencyRate = currencyService.getCurrencyRate(fromCurrencyId,toCurrencyId);
        List<Currency> currencies = currencyService.getCurrencies();
        Currency fromCurrency = currencyService.getCurrency(fromCurrencyId);
        Currency toCurrency = currencyService.getCurrency(toCurrencyId);

        model.addAttribute("currencyRate",currencyRate);
        model.addAttribute("currencies",currencies);
        model.addAttribute("amount",amount);
        model.addAttribute("fromCurrency",fromCurrency);
        model.addAttribute("toCurrency",toCurrency);
        model.addAttribute("convertedamount",amount*currencyRate);


        // System.out.println(currencyRate);
        return "index";
    }
}
