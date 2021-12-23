package com.alaa.queueing_theory.app.controllers;

import com.alaa.queueing_theory.app.middlewares.ExpressionEvaluatorMiddleware;
import com.alaa.queueing_theory.app.middlewares.RegexMiddleware;
import com.alaa.queueing_theory.app.queues.Stochastic;
import com.alaa.queueing_theory.utility.Globals;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StochasticController implements Initializable {


    private Stochastic stochastic;

    @FXML
    private AnchorPane stochasticView;


    // btns
    @FXML
    private Button btnBack;
    @FXML
    private Button btnCalcL;
    @FXML
    private Button btnCalcLq;
    @FXML
    private Button btnCalcW;
    @FXML
    private Button btnCalcWq;


    @FXML
    private TextField mu;
    @FXML
    private TextField lamda;
    @FXML
    private TextField k;
    @FXML
    private TextField c;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.stochastic = new Stochastic();
    }


    @FXML
    public void showEntry() {
        ((Label) this.stochasticView.getParent().lookup("#labelAppTitle")).setText(Globals.getAppName());
        ((BorderPane) this.stochasticView.getParent()).setCenter(Globals.getNode("entry"));
    }


    private ExpressionEvaluatorMiddleware setCommonParameters() {
        ExpressionEvaluatorMiddleware evaluatorMiddleware = new ExpressionEvaluatorMiddleware();
        this.stochastic.setLamda(evaluatorMiddleware.setInput(this.lamda.getText()).execute());
        this.stochastic.setMu(evaluatorMiddleware.setInput(this.mu.getText()).execute());
        this.stochastic.setC(evaluatorMiddleware.setInput(this.c.getText()).execute().intValue());
        this.stochastic.setK(evaluatorMiddleware.setInput(this.k.getText()).execute().intValue());
        return evaluatorMiddleware;
    }

    private boolean validateCommonInput() {
        RegexMiddleware regexMiddleware = this.getRegexMiddleware();
        return (regexMiddleware.setErrorMessage("lamda is not valid").setInput(this.lamda.getText()).execute() &&
                regexMiddleware.setErrorMessage("mu is not valid").setInput(this.mu.getText()).execute() &&
                ((this.k.getText().trim().length() == 0) || regexMiddleware.setErrorMessage("K is not valid").setInput(this.k.getText()).execute()) &&
                ((this.c.getText().trim().length() == 0) || regexMiddleware.setErrorMessage("c is not valid").setInput(this.c.getText()).execute())
        );
    }

    private RegexMiddleware getRegexMiddleware() {
        return new RegexMiddleware("^\\s*[0-9]+\\.?[0-9]*\\s*([\\+-/\\*%\\^]\\s*[0-9]+\\.?[0-9]*)*\\s*$");
    }


    @FXML
    public void calcL() {
        if (!this.beforeCalc()) return;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Calculate L");
        alert.setHeaderText("L : " + this.stochastic.calcL());
        alert.show();
    }

    @FXML
    public void calcLq() {
        if (!this.beforeCalc()) return;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Calculate Lq");
        alert.setHeaderText("Lq : " + this.stochastic.calcLq());
        alert.show();
    }

    @FXML
    public void calcW() {
        if (!this.beforeCalc()) return;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Calculate W");
        alert.setHeaderText("W : " + this.stochastic.calcW());
        alert.show();

    }

    @FXML
    public void calcWq() {
        if (!this.beforeCalc()) return;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Calculate Wq");
        alert.setHeaderText("Wq : " + this.stochastic.calcWq());
        alert.show();
    }


    private boolean beforeCalc() {
        if (!validateCommonInput()) return false;
        this.setCommonParameters();
        return true;
    }
}
