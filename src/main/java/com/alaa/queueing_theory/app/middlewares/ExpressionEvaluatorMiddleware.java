package com.alaa.queueing_theory.app.middlewares;

import net.objecthunter.exp4j.ExpressionBuilder;


public class ExpressionEvaluatorMiddleware extends Middleware<Double> {


    public ExpressionEvaluatorMiddleware() {
        super("Your Input is not valid expression");

    }
    public ExpressionEvaluatorMiddleware(String input) {
        super("Your Input is not valid expression");
        this.input = input;
    }

    public ExpressionEvaluatorMiddleware(String input, String errorMessage) {
        super(input, errorMessage);
    }

    @Override
    public Double execute() {
        return (new ExpressionBuilder(this.input.trim().length() == 0 ?  "0" : this.input).build()).evaluate();
    }
}
