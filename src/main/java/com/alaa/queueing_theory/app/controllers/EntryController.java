package com.alaa.queueing_theory.app.controllers;

import com.alaa.queueing_theory.utility.Globals;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EntryController  implements Initializable {


    @FXML
    private VBox entry;

    @FXML
    private Button btnDisplayDeterministic ;

    @FXML
    private Button btnDisplayStochastic ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.btnDisplayStochastic.setText("Stochastic");
        this.btnDisplayDeterministic.setText("Deterministic");
      //  this.btnDisplayStochastic.setDisable(true);
    }



    @FXML
    public  void showDeterministicForm() throws IOException {
        ((Label) this.entry.getParent().lookup("#labelAppTitle")).setText("Deterministic Queue");
        ((BorderPane) this.entry.getParent()).setCenter( Globals.getNode("deterministic"));
    }

    @FXML
    public  void showStochasticForm() throws IOException {
        ((Label) this.entry.getParent().lookup("#labelAppTitle")).setText("Stochastic Queue");
        ((BorderPane) this.entry.getParent()).setCenter( Globals.getNode("stochastic"));    }

}