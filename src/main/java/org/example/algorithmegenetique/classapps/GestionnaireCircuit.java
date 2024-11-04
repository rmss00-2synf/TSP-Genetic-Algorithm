package org.example.algorithmegenetique.classapps;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireCircuit {
    private static List<Ville> villesDestinations = new ArrayList<>();

    public static void ajouterVille(Ville ville) {
        villesDestinations.add(ville);
    }

    public static Ville getVille(int index) {
        return villesDestinations.get(index);
    }

    public static int nombreVilles() {
        return villesDestinations.size();
    }

    public static List<Ville> getVillesDestinations() {
        return villesDestinations;
    }
}
