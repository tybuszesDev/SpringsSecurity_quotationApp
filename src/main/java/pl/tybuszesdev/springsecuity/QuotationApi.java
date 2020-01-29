package pl.tybuszesdev.springsecuity;

import org.springframework.web.bind.annotation.*;                                           //SECURED API

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuotationApi {

    private List<Quotation> quotations;

    public QuotationApi(){
        this.quotations = new ArrayList<>();
        quotations.add(new Quotation("DUPA DUPA DUPA", "Łukasz"));
        quotations.add(new Quotation("BLA BLA BLA","Tomek"));
    }

    @GetMapping("/api")                                                                 //GET QUOTE (FOR EVERYBODY)
    public List<Quotation> getQuotation(){

        return quotations;
    }

    @PostMapping("/api")                                                                    //POST QUOTE (FOR MODERATOR AND ADMIN)
    public boolean addQuotation(@RequestBody Quotation quotation){

        return quotations.add(quotation);
    }

    @DeleteMapping("/api")                                                                    //Delete using index (FOR ADMIN)
    public void deleteQuotation(@RequestParam int index){

        quotations.remove(index);
    }
}
