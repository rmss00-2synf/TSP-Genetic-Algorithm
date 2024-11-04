package org.example.algorithmegenetique.classapps;

public class Main {
    public static void main(String[] args) {
        GestionnaireCircuit gc = new GestionnaireCircuit();

        // Création des villes
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

        // Initialisation de la population avec 50 circuits
        Population pop = new Population(gc, 50, true);
        System.out.println("Distance initiale : " + pop.getFittest().getDistance());

        // Evolution de la population sur 100 générations
        GA ga = new GA(gc);
        pop = ga.evoluerPopulation(pop);
        for (int i = 0; i < 1000; i++) {
            pop = ga.evoluerPopulation(pop);
        }

        System.out.println("Distance finale : " + pop.getFittest().getDistance());
        Circuit meilleurePopulation = pop.getFittest();

        // Affichage des résultats (coordonnées des villes dans l'ordre optimal trouvé)
        for (Ville ville : meilleurePopulation.getCircuit()) {
            System.out.println("Ville: " + ville.getNom() + " [Lon: " + ville.getLon() + ", Lat: " + ville.getLat() + "]");
        }
    }
}
