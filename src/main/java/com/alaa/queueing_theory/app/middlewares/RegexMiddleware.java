package com.alaa.queueing_theory.app.middlewares;

import java.util.regex.Pattern;

public class RegexMiddleware extends   Middleware<Boolean> {

    private   String regex ;
    public RegexMiddleware(String regex){
        super("Your Input is not valid wit this regex" + regex);
        this.regex = regex ;
    }

    public  RegexMiddleware( String regex , String input  ){
        super( input , "Your Input" + input + " is not valid wit this regex" + regex);
        this.regex = regex ;
    }

    public  RegexMiddleware( String regex , String input  , String errorMessage){
        super(errorMessage);
        this.regex = regex ;
        this.input = input ;
    }

    @Override
    public Boolean execute() {
           boolean isMatch =  Pattern.matches(this.regex , this.input);
            if(!isMatch){
                this.onFail();
            }
           return isMatch;
    }



    public  RegexMiddleware setRegex(String regex){
        this.regex = regex ;
        return this;
    }

}
