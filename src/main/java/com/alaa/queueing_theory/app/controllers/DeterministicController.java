package com.alaa.queueing_theory.app.controllers;

import com.alaa.queueing_theory.app.middlewares.ExpressionEvaluatorMiddleware;
import com.alaa.queueing_theory.app.middlewares.RegexMiddleware;
import com.alaa.queueing_theory.app.queues.DD1K;
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

public class DeterministicController implements Initializable {


    // Queue

    private DD1K DQueue;

    @FXML
    private AnchorPane deterministicView;


    // btns
    @FXML
    private Button btnBack;

    @FXML
    private Button btnPlot;

    @FXML
    private Button btnCalcNt;

    @FXML
    private Button btnCalcWq;

    // textFields

    @FXML
    private TextField mu;

    @FXML
    private TextField lamda;

    @FXML
    private TextField time;
    @FXML
    private TextField k;
    @FXML
    private TextField customerNumber;
    @FXML
    private TextField initNoOfCustomer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.DQueue = new DD1K();
    }


    // execute middlewares
    private ExpressionEvaluatorMiddleware setCommonParameters() {
        ExpressionEvaluatorMiddleware evaluatorMiddleware = new ExpressionEvaluatorMiddleware();
        this.DQueue.setLamda(evaluatorMiddleware.setInput(this.lamda.getText()).execute());
        this.DQueue.setMu(evaluatorMiddleware.setInput(this.mu.getText()).execute());
        this.DQueue.setInitNoOfCustomers(evaluatorMiddleware.setInput(this.initNoOfCustomer.getText()).execute().intValue());
        this.DQueue.setK(evaluatorMiddleware.setInput(this.k.getText()).execute().intValue());
        return evaluatorMiddleware;
    }


    @FXML
    public void showEntry() {
        ((Label) this.deterministicView.getParent().lookup("#labelAppTitle")).setText(Globals.getAppName());
        ((BorderPane) this.deterministicView.getParent()).setCenter(Globals.getNode("entry"));
    }


    // calculate n(t) collection methods
    @FXML
    public void calculateNoOfCustomerAtTime() {
        if (!this.validateForCalculateNoOfCustomerAtTime()) return;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("n(" + time.getText() + ")");
        alert.setHeaderText("Number Of Customers At Time " + time.getText() + " : " + this.getNoOfCustomersAtT());
        alert.show();
    }

    private boolean validateForCalculateNoOfCustomerAtTime() {
        RegexMiddleware regexMiddleware = this.getRegexMiddleware();
        return (this.validateCommonInput() &&
                regexMiddleware.setErrorMessage("Time is not valid").setInput(this.time.getText()).execute()
        );
    }

    private int getNoOfCustomersAtT() {

        return this.DQueue.getNoOfCustomersAtT(this.setCommonParameters().setInput(this.time.getText()).execute().intValue());
    }

    // calculate Wq(n) collection methods
    @FXML
    public void calculateWaitingTimeForCustomer() {
        if (!this.validateForCalculateWaitingTimeForCustomer()) return;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wq(" + customerNumber.getText() + ")");
        alert.setHeaderText(" waiting time to customer number " + customerNumber.getText() + "  :  " + this.getWaitingTimeByCustomerNo());
        alert.show();

    }

    private RegexMiddleware getRegexMiddleware() {
        return new RegexMiddleware("^\\s*[0-9]+\\.?[0-9]*\\s*([\\+-/\\*%\\^]\\s*[0-9]+\\.?[0-9]*)*\\s*$");
    }

    private boolean validateCommonInput() {
        RegexMiddleware regexMiddleware = this.getRegexMiddleware();
        return (regexMiddleware.setErrorMessage("lamda is not valid").setInput(this.lamda.getText()).execute() &&
                regexMiddleware.setErrorMessage("mu is not valid").setInput(this.mu.getText()).execute() &&
                ((this.k.getText().trim().length() == 0) || regexMiddleware.setErrorMessage("K is not valid").setInput(this.k.getText()).execute()) &&
                ((this.initNoOfCustomer.getText().trim().length() == 0) || regexMiddleware.setErrorMessage("initial number of customer  is not valid").setInput(this.initNoOfCustomer.getText()).execute())
        );
    }

    private boolean validateForCalculateWaitingTimeForCustomer() {
        RegexMiddleware regexMiddleware = this.getRegexMiddleware();
        return (this.validateCommonInput() &&
                regexMiddleware.setErrorMessage("Number Of Customer  is not valid").setInput(this.customerNumber.getText()).execute());
    }

    private int getWaitingTimeByCustomerNo() {

        return this.DQueue.getWaitingTimeForCustomerNo(this.setCommonParameters().setInput(this.customerNumber.getText()).execute().intValue());
    }


    // plot collection method


    @FXML
    public void plot() {
        if (!validateCommonInput()) return;
        this.setCommonParameters();
        (new PlotChart(this.DQueue)).show();
    }


}
