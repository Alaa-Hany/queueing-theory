package com.alaa.queueing_theory.app.controllers;

import com.alaa.queueing_theory.utility.Globals;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController  implements Initializable {
    // #7971ea

    // #eeeeee

    // #393e46

    // #222831

    @FXML
    private BorderPane main ;

    @FXML
    private Label labelAppTitle;

    @FXML
    private  Label  labelCodeBy;
    @FXML




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.labelAppTitle.setText(Globals.getAppName());
        this.labelCodeBy.setText("Prof : mohamed Rohaim && mohamed Atef ");
    }
}
