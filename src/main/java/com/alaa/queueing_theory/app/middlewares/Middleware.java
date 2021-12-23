package com.alaa.queueing_theory.app.middlewares;

import javafx.scene.control.Alert;

public abstract class Middleware<T> {
    protected String errorMessage ;

    protected String input ;

    public  Middleware( String errorMessage){
        this.errorMessage = errorMessage;
    }


    public  Middleware(  String input  , String errorMessage){
        this.errorMessage = errorMessage;

        this.input = input ;
    }



    public  abstract  T execute();



    protected  void onFail(){
        (new Alert(Alert.AlertType.ERROR, this.errorMessage)).show();
    }


    public Middleware<T> setInput(String input) {
        this.input = input;
        return this;
    }



    public  Middleware<T> setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage ;
        return this;
    }
}
