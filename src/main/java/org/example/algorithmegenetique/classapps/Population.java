package org.example.algorithmegenetique.classapps;

import java.util.ArrayList;
import java.util.List;

public class Population {
    private List<Circuit> circuits = new ArrayList<>();

    public Population(GestionnaireCircuit gestionnaireCircuit, int taillePopulation, boolean init) {
        for (int i = 0; i < taillePopulation; i++) {
            circuits.add(null);
        }
        if (init) {
            for (int i = 0; i < taillePopulation; i++) {
                Circuit nouveauCircuit = new Circuit(gestionnaireCircuit);
                nouveauCircuit.genererIndividu();
                sauvegarderCircuit(i, nouveauCircuit);
            }
        }
    }

    public void sauvegarderCircuit(int index, Circuit circuit) {
        circuits.set(index, circuit);
    }

    public Circuit getCircuit(int index) {
        return circuits.get(index);
    }

    public Circuit getFittest() {
        Circuit fittest = circuits.get(0);
        for (int i = 0; i < taillePopulation(); i++) {
            if (fittest.getFitness() <= getCircuit(i).getFitness()) {
                fittest = getCircuit(i);
            }
        }
        return fittest;
    }

    public int taillePopulation() {
        return circuits.size();
    }
}
