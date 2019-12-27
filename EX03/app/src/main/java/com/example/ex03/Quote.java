package com.example.ex03;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quote {
    public String mQuote;
    public List<String> mQuotes = QuotesPull();

    public Quote(){
        int random = new Random().nextInt(mQuotes.size());
        this.mQuote = this.mQuotes.get(random);
    }

    public List<String> QuotesPull() {
        List<String> Quotes = new ArrayList<>();
        Quotes.add("You are beautiful just the way you are!");
        Quotes.add("Tell someone you love, that you love him");
        Quotes.add("Be yourself");
        Quotes.add("If you tell the truth, you don’t have to remember anything");
        Quotes.add("why worry?");
        Quotes.add("There is no better time than now");
        Quotes.add("go with the flow");
        Quotes.add("If you cannot do great things, do small things in a great way");
        Quotes.add("You must be the change you wish to see in the world");
        Quotes.add("Too many of us are not living our dreams because we are living our fears");
        Quotes.add("The only time you fail is when you fall down and stay down");
        Quotes.add("You are never too old to set another goal or dream a new dream");
        return Quotes;
    }
}
