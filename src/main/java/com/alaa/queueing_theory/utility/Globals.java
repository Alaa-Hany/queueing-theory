package com.alaa.queueing_theory.utility;


import com.alaa.queueing_theory.app.QueueingTheoryApplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;


public class Globals {
    private static String appName ;
    private static Scene mainScene ;
    private static Stage chartStage;
    private static HashMap<String , Node> mainNodes;
    static  {
        Globals.appName = "Queueing Theory" ;
        Globals.mainNodes = new HashMap<>();
    }

    public static Class<QueueingTheoryApplication> getApp() {

        return QueueingTheoryApplication.class;
    }


    public  static  String getAppName(){
        return Globals.appName;
    }

    public static void setMainScene(Scene scene){

        Globals.mainScene = scene ;
    }


    public static Scene getMainScene() {
        return Globals.mainScene;
    }




    public static Node getNode(String name ){
        if(Globals.mainNodes.containsKey(name)){
            return Globals.mainNodes.get(name);
        }
        return  (new Text("Not Found"));
    }

    public static void loadMainNodes() throws IOException {
        Globals.mainNodes.put("entry" , FXMLLoader.load(View.getViewURL("entry")));
        Globals.mainNodes.put("deterministic" , FXMLLoader.load(View.getViewURL("deterministic")));
        Globals.mainNodes.put("stochastic" , FXMLLoader.load(View.getViewURL("stochastic")));
    }

    public static Stage getChartStage() {
        return chartStage;
    }

    public static void setChartStage(Stage chartStage) {
        Globals.chartStage = chartStage;
    }
}
