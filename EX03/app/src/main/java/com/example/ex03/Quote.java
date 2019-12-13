package com.example.ex03;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quote {
    public String mQuote;
    public List<String> mQuotes = QuotesPull();

    public Quote(){
        Log.d("Q", "Quete");
        int random = new Random().nextInt(6);
        this.mQuote = this.mQuotes.get(random);
    }

    public List<String> QuotesPull() {
        List<String> Quotes = new ArrayList<>();
        Quotes.add("You are beautiful just the way you are!");
        Quotes.add("Tell someone you love, that you love him");
        Quotes.add("Be yourself");
        Quotes.add("why worry?");
        Quotes.add("There is no better time than now");
        Quotes.add("go with the flow");
        return Quotes;
    }
}
