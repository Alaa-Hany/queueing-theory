package com.alaa.queueing_theory.app;

import com.alaa.queueing_theory.utility.Env;
import com.alaa.queueing_theory.utility.Globals;
import com.alaa.queueing_theory.utility.Style;
import com.alaa.queueing_theory.utility.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jfree.chart.fx.ChartViewer;

import java.io.IOException;


// -Djdk.gtk.version=2
public class QueueingTheoryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Globals.setMainScene(new Scene((new FXMLLoader(View.getViewURL("main"))).load(), Env.width, Env.height));
        Globals.getMainScene().getStylesheets().add(Style.getStylePath("main"));
        Globals.loadMainNodes();
        QueueingTheoryApplication.initStage(stage);
        QueueingTheoryApplication.initChartStage();
    }

    public static void main(String[] args) {
        launch();
    }


    private static void initStage(Stage mainStage) {
        mainStage.setTitle(Globals.getAppName());
        mainStage.setScene(Globals.getMainScene());
        mainStage.setResizable(false);
        mainStage.centerOnScreen();
        mainStage.show();
    }


    private static void initChartStage() {
        Globals.setChartStage(new Stage());
        Globals.getChartStage().setScene(new Scene(new ChartViewer()));

    }
}