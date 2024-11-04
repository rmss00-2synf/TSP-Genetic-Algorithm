package org.example.algorithmegenetique;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.algorithmegenetique.classapps.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Canvas canvas;

    private Circuit circuits;
    private GestionnaireCircuit gest=new GestionnaireCircuit();
    private Population population;

    @FXML
    public void eventen(ActionEvent actionEvent)  {
//        stage.setFullScreen(false);
//        canvas = new Canvas(WIDTH, HEIGHT);
        initialize(gest);
        System.out.println(gest);
        population=new Population(gest,50,true);
        GA ga =new GA(gest);
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) { // 1000 générations
                population = ga.evoluerPopulation(population);
                drawBestTour();
                try {
                    Thread.sleep(1000); // Pause pour observer l'évolution
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void drawBestTour() {
        System.out.println(canvas.getWidth());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Dessiner les villes
        gc.setFill(Color.BLUE);
//        List<Ville> gc = GestionnaireCircuit.getVillesDestinations();
        for (Ville city : GestionnaireCircuit.getVillesDestinations() ) {
            gc.fillOval(city.getLat(), city.getLon(), 10, 10);
        }

        // Dessiner le meilleur chemin
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        List<Ville> bestTour = population.getFittest().getCircuit();
        for (int i = 0; i < bestTour.size() - 1; i++) {
            Ville city1 = bestTour.get(i);
            Ville city2 = bestTour.get(i + 1);
            gc.strokeLine(city1.getLat(), city1.getLon(), city2.getLat(), city2.getLon());
        }

        // Relier le dernier point au premier
        Ville lastCity = bestTour.get(bestTour.size() - 1);
        Ville firstCity = bestTour.get(0);
        gc.strokeLine(lastCity.getLat(), lastCity.getLon(), firstCity.getLat(), firstCity.getLon());
    }

    public void initialize(GestionnaireCircuit gc) {
        gc = new GestionnaireCircuit();
        gc.ajouterVille(new Ville(-6.8498, 33.9716, "Rabat"));
        gc.ajouterVille(new Ville(-7.5898, 33.5731, "Casablanca"));
        gc.ajouterVille(new Ville(-9.5526, 30.4215, "Agadir"));
        gc.ajouterVille(new Ville(-8.0089, 31.6315, "Marrakech"));
        gc.ajouterVille(new Ville(-5.8007, 35.7595, "Tanger"));
        gc.ajouterVille(new Ville(-6.3063, 34.261, "Kénitra"));
        gc.ajouterVille(new Ville(-5.0278, 34.0331, "Fès"));
        gc.ajouterVille(new Ville(-4.9998, 32.8866, "Errachidia"));
        gc.ajouterVille(new Ville(-5.9357, 35.168, "Tétouan"));
        gc.ajouterVille(new Ville(-4.4274, 34.0574, "Meknès"));
        gc.ajouterVille(new Ville(-9.227, 32.2939, "El Jadida"));
        gc.ajouterVille(new Ville(-7.0935, 32.8872, "Béni Mellal"));
        gc.ajouterVille(new Ville(-6.3914, 35.0158, "Chefchaouen"));
        gc.ajouterVille(new Ville(-6.9277, 33.5419, "Salé"));
        gc.ajouterVille(new Ville(-5.7128, 34.7855, "Taza"));
        gc.ajouterVille(new Ville(-5.1945, 32.3204, "Midelt"));
        gc.ajouterVille(new Ville(-6.8516, 31.6837, "Ouarzazate"));
        gc.ajouterVille(new Ville(-9.7402, 29.2363, "Tiznit"));
        gc.ajouterVille(new Ville(-6.3424, 31.9391, "Taroudant"));
        gc.ajouterVille(new Ville(-5.0661, 33.9299, "Ifrane"));
        gc.ajouterVille(new Ville(-7.9174, 34.0374, "Settat"));
        gc.ajouterVille(new Ville(-4.9734, 34.8579, "Taounate"));
    }


}