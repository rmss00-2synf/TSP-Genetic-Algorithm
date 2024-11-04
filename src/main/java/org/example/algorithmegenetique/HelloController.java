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
    private static final int WIDTH = 459;
    private static final int HEIGHT = 507;
    @FXML
    public void eventen(ActionEvent actionEvent)  {
//        stage.setFullScreen(false);
//        canvas = new Canvas(WIDTH, HEIGHT);
        initialize(gest);
        GestionnaireCircuit.getVillesDestinations().stream().forEach(System.out::println);
        population=new Population(gest,5,true);
        System.out.println("Distance initiale : " + population.getFittest().getDistance());

        GA ga =new GA(gest);
        new Thread(() -> {
            for (int i = 0; i < 20; i++) { // 1000 générations
                population = ga.evoluerPopulation(population);
                drawBestTour();
                try {
                    Thread.sleep(1000); // Pause pour observer l'évolution
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Distance finale : " + population.getFittest().getDistance());
            Circuit meilleurePopulation = population.getFittest();
            for (Ville ville : meilleurePopulation.getCircuit()) {
                System.out.println("Ville: " + ville.getNom() + " [Lon: " + ville.getLon() + ", Lat: " + ville.getLat() + "]");
            }
        }).start();



    }

    private void drawBestTour() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Efface le canvas avant de redessiner

        // Dessiner les villes avec leurs noms
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLACK);
        for (Ville city : GestionnaireCircuit.getVillesDestinations()) {
            double x = Math.abs(city.getLat());
            double y = Math.abs(city.getLon());
            gc.fillOval(x, y, 10, 10); // Dessine un cercle pour la ville
            gc.strokeText(city.getNom(), x + 5, y - 5); // Dessine le nom de la ville près du cercle
        }

        // Dessiner le meilleur chemin avec les distances
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        List<Ville> bestTour = population.getFittest().getCircuit();
        for (int i = 0; i < bestTour.size() - 1; i++) {
            Ville city1 = bestTour.get(i);
            Ville city2 = bestTour.get(i + 1);
            double distance = city1.distanceTo(city2); // Calcul de la distance entre les deux villes

            // Tracer la ligne entre les villes
            gc.strokeLine(city1.getLat(), city1.getLon(), city2.getLat(), city2.getLon());

            // Afficher la distance au milieu de la ligne
            double midX = (city1.getLat() + city2.getLat()) / 2;
            double midY = (city1.getLon() + city2.getLon()) / 2;
            gc.strokeText(String.format("%.2f km", distance), midX, midY);
        }

        // Relier le dernier point au premier avec la distance
        Ville lastCity = bestTour.get(bestTour.size() - 1);
        Ville firstCity = bestTour.get(0);
        gc.strokeLine(Math.abs(lastCity.getLat()), Math.abs(lastCity.getLon()), Math.abs(firstCity.getLat()), Math.abs(firstCity.getLon()));
        double closingDistance = lastCity.distanceTo(firstCity);
        double midX = (lastCity.getLat() + firstCity.getLat()) / 2;
        double midY = (lastCity.getLon() + firstCity.getLon()) / 2;
        gc.strokeText(String.format("%.2f km", closingDistance), midX, midY);
    }

    public void initialize(GestionnaireCircuit gc) {
        List<String> moroccanCities = new ArrayList<>();

        // Ajouter des noms de villes du Maroc
        moroccanCities.add("Casablanca");
        moroccanCities.add("Rabat");
        moroccanCities.add("Marrakech");
        moroccanCities.add("Fès");
        moroccanCities.add("Tanger");
        moroccanCities.add("Agadir");
        moroccanCities.add("Meknès");
        moroccanCities.add("Oujda");
        moroccanCities.add("Kenitra");
        moroccanCities.add("Tetouan");
        gc = new GestionnaireCircuit();
        for (int i = 0; i < 10; i++) {
            gc.ajouterVille(new Ville(Math.random() * WIDTH, Math.random() * HEIGHT, moroccanCities.get(i)));
//            cities.add(new City(Math.random() * WIDTH, Math.random() * HEIGHT));
        }

//        gc.ajouterVille(new Ville(-6.8498, 33.9716, "Rabat"));
//        gc.ajouterVille(new Ville(-7.5898, 33.5731, "Casablanca"));
//        gc.ajouterVille(new Ville(-9.5526, 30.4215, "Agadir"));
//        gc.ajouterVille(new Ville(-8.0089, 31.6315, "Marrakech"));
//        gc.ajouterVille(new Ville(-5.8007, 35.7595, "Tanger"));
//        gc.ajouterVille(new Ville(-6.3063, 34.261, "Kénitra"));
//        gc.ajouterVille(new Ville(-5.0278, 34.0331, "Fès"));
//        gc.ajouterVille(new Ville(-4.9998, 32.8866, "Errachidia"));
//        gc.ajouterVille(new Ville(-5.9357, 35.168, "Tétouan"));
//        gc.ajouterVille(new Ville(-4.4274, 34.0574, "Meknès"));
//        gc.ajouterVille(new Ville(-9.227, 32.2939, "El Jadida"));
//        gc.ajouterVille(new Ville(-7.0935, 32.8872, "Béni Mellal"));
//        gc.ajouterVille(new Ville(-6.3914, 35.0158, "Chefchaouen"));
//        gc.ajouterVille(new Ville(-6.9277, 33.5419, "Salé"));
//        gc.ajouterVille(new Ville(-5.7128, 34.7855, "Taza"));
//        gc.ajouterVille(new Ville(-5.1945, 32.3204, "Midelt"));
//        gc.ajouterVille(new Ville(-6.8516, 31.6837, "Ouarzazate"));
//        gc.ajouterVille(new Ville(-9.7402, 29.2363, "Tiznit"));
//        gc.ajouterVille(new Ville(-6.3424, 31.9391, "Taroudant"));
//        gc.ajouterVille(new Ville(-5.0661, 33.9299, "Ifrane"));
//        gc.ajouterVille(new Ville(-7.9174, 34.0374, "Settat"));
//        gc.ajouterVille(new Ville(-4.9734, 34.8579, "Taounate"));
    }


}