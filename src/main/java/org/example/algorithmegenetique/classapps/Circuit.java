package org.example.algorithmegenetique.classapps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Circuit {
    private List<Ville> circuit = new ArrayList<>();
    private double fitness = 0;
    private double distance = 0;
    private GestionnaireCircuit gestionnaireCircuit;

    public Circuit(GestionnaireCircuit gestionnaireCircuit) {
        this.gestionnaireCircuit = gestionnaireCircuit;
        for (int i = 0; i < gestionnaireCircuit.nombreVilles(); i++) {
            circuit.add(null);
        }
    }

    public void genererIndividu() {
        for (int i = 0; i < gestionnaireCircuit.nombreVilles(); i++) {
            setVille(i, gestionnaireCircuit.getVille(i));
        }
        Collections.shuffle(circuit);
    }

    public Ville getVille(int circuitPosition) {
        return circuit.get(circuitPosition);
    }

    public void setVille(int circuitPosition, Ville ville) {
        circuit.set(circuitPosition, ville);
        fitness = 0;
        distance = 0;
    }

    public double getFitness() {
        if (fitness == 0) {
            fitness = 1 / getDistance();
        }
        return fitness;
    }

    public double getDistance() {
        if (distance == 0) {
            double circuitDistance = 0;
            for (int i = 0; i < tailleCircuit(); i++) {
                Ville villeOrigine = getVille(i);
                Ville villeArrivee = (i + 1 < tailleCircuit()) ? getVille(i + 1) : getVille(0);
                circuitDistance += villeOrigine.distance(villeArrivee);
            }
            distance = circuitDistance;
        }
        return distance;
    }

    public int tailleCircuit() {
        return circuit.size();
    }

    public boolean contientVille(Ville ville) {
        return circuit.contains(ville);
    }

    public List<Ville> getCircuit() {
        return circuit;
    }
}
